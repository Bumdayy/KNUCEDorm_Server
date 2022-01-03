package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class S3_sleepout {
    private static S3_sleepout instance = new S3_sleepout();

    public static S3_sleepout getInstance() {
        return instance;
    }
    public S3_sleepout() {  }

    // [수정X]oracle 계정진입 
    String jdbcUrl = "jdbc:oracle:thin:@192.168.1.32:1522:oracl";
    String userId = "bumday";
    String userPw = "789513";

    // [수정X]oracle 연동 변수초기화
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;

    // [수정X]oracle 연산 및 처리결과를 위한 변수초기화
    String sql = "";
    String sql2 = "";
    String returns = "수행오류!";

    Integer num = 0;
    
    // 함수(인자값 아이디,비밀번호)
    public String connectionDB(String id, String name, String why, String sdate, String edate) 
    {
        try {
        	num = 0;
        	
            // [수정X]DB연결. 인자- <DB URL, 아이디, 패스워드>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            
            //시퀀스 적용하기
            for(int i = 1; ;i++) {
            	sql = "SELECT * FROM SLOUT WHERE SLOUT_NUM = ?";
            	pstmt = conn.prepareStatement(sql);
            	num = i;
            	pstmt.setInt(1, num);
            	
            	rs = pstmt.executeQuery();
            	if (!rs.next()) break;
            }
            
            sql = "SELECT id FROM STUINFO WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            
            if (!(num==0)) {////////////////////////////// 테이블수정시, 이곳을 수정
            	//입관시
	                sql2 = "INSERT INTO SLOUT(SLOUT_NUM, ID, NAME, WHY, SDATE, EDATE) VALUES(?, ?, ?, ?, ?, ?)";
	                pstmt2 = conn.prepareStatement(sql2);
	                pstmt2.setInt(1, num);
	                pstmt2.setString(2, id);
	                pstmt2.setString(3, name);
	                pstmt2.setString(4, why);
	                pstmt2.setString(5, sdate);
	                pstmt2.setString(6, edate);
	                pstmt2.executeUpdate();
	                returns = "외박신청 되었습니다.";
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