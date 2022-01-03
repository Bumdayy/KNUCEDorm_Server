package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class S2_inout {
    private static S2_inout instance = new S2_inout();

    public static S2_inout getInstance() {
        return instance;
    }
    public S2_inout() {  }

    // [수정X]oracle 계정진입 
    String jdbcUrl = "jdbc:oracle:thin:@192.168.1.32:1522:oracl";
    String userId = "bumday";
    String userPw = "789513";

    // [수정X]oracle 연동 변수초기화
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;

    // [수정X]oracle 연산 및 처리결과를 위한 변수초기화
    String sql = "";
    String returns = "수행오류!";
    

    // 함수(인자값 아이디,비밀번호)
    public String connectionDB(String id, String type) 
    {
        try {
            // [수정X]DB연결. 인자- <DB URL, 아이디, 패스워드>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT inout_num, what, when FROM INOUT WHERE id = ? ORDER BY inout_num DESC";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            JSONObject jsonobject = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonresult = new JSONObject();
            
            
            while(rs.next()) {
            		jsonobject.put("inout_num", rs.getString("inout_num"));
                	jsonobject.put("what", rs.getString("what"));
                	jsonobject.put("when", rs.getString("when"));
                	jsonarray.add(jsonobject);
                	jsonobject = new JSONObject();
            }
            
            jsonresult.put("inout",jsonarray);
            returns = jsonresult.toString();
            
            
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