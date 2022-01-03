package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class A2_scanbarcord {
    private static A2_scanbarcord instance = new A2_scanbarcord();

    public static A2_scanbarcord getInstance() {
        return instance;
    }
    public A2_scanbarcord() {  }

    // [수정X]oracle 계정진입 
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:oracl";
    String userId = "bumday";
    String userPw = "789513";

    // [수정X]oracle 연동 변수초기화
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;

    // [수정X]oracle 연산 및 처리결과를 위한 변수초기화
    String sql = "";
    String sql2 = "";
    String sql3 = "";
    String returns = "수행오류!";
    
    Integer num = 0;
    String sname ="";
    


    // 함수(인자값 아이디,비밀번호)
    public String connectionDB(String id, String type) 
    {
        try {
        	
        	num = 0;
        	
            // [수정X]DB연결. 인자- <DB URL, 아이디, 패스워드>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            
            // 현재시각 구하기
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String time1 = format1.format(time);
            
            
            //시퀀스 적용하기
            for(int i = 1; ;i++) {
            	sql = "SELECT * FROM INOUT WHERE INOUT_NUM = ?";
            	pstmt = conn.prepareStatement(sql);
            	num = i;
            	pstmt.setInt(1, num);
            	
            	rs = pstmt.executeQuery();
            	if (!rs.next()) break;
            }
            
            // name 구하기
            sql3 = "SELECT NAME FROM STUINFO WHERE ID =?";
            pstmt3 = conn.prepareStatement(sql3);
            pstmt3.setString(1, id);
            
            rs3 = pstmt3.executeQuery();
            
            if(rs3.next()) sname = rs3.getString("name");


            if (!(num==0)) {////////////////////////////// 테이블수정시, 이곳을 수정
            	//입관시
            	if (type.equals("in")) {
	                sql2 = "INSERT INTO INOUT(INOUT_NUM, NAME, ID, WHEN, WHAT) VALUES(?, ?, ?, ?, '입관')";
	                pstmt2 = conn.prepareStatement(sql2);
	                pstmt2.setInt(1, num);
	                pstmt2.setString(2, sname);
	                pstmt2.setString(3, id);
	                pstmt2.setString(4, time1);
	                pstmt2.executeUpdate();
	                returns = "(" + sname + ")님 입관 처리되었습니다.";
                //퇴관시
            	}else if(type.equals("out")) {
	                sql2 = "INSERT INTO INOUT(INOUT_NUM, NAME, ID, WHEN, WHAT) VALUES(?, ?, ?, ?, '퇴관')";
	                pstmt2 = conn.prepareStatement(sql2);
	                pstmt2.setInt(1, num);
	                pstmt2.setString(2, sname);
	                pstmt2.setString(3, id);
	                pstmt2.setString(4, time1);
	                pstmt2.executeUpdate();
	                returns = "(" + sname + ")님 퇴관 처리되었습니다.";
            	}
            } else {
                returns = "INSERT문이 수행되지 않았습니다.";
            }
            
            // [수정x]예외처리
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns;
    }
}