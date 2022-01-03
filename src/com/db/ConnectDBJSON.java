package com.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConnectDBJSON {
    private static ConnectDBJSON instance = new ConnectDBJSON();

    public static ConnectDBJSON getInstance() {
        return instance;
    }
    public ConnectDBJSON() {  }

    // [수정X]oracle 계정진입 
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:oracl";
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
    String returns = "DB읽어오는데 실패하였습니다.";

    // 함수(인자값 아이디,비밀번호)
    public String connectionDBJSON(String id, String name, String pwd, String address, 
    		String major, String telnum, String dorm, String roomnum) 
    {
        try {
            // [수정X]DB연결. 인자- <DB URL, 아이디, 패스워드>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT * FROM STUINFO";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            JSONObject jsonobject = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonresult = new JSONObject();
            
            
            while(rs.next()) {
                	jsonobject.put("id", rs.getString("id"));
                	jsonobject.put("name", rs.getString("name"));
                	jsonobject.put("pwd", rs.getString("pwd"));
                	jsonobject.put("address", rs.getString("address"));
                	jsonobject.put("major", rs.getString("major"));
                	jsonobject.put("telnum", rs.getString("telnum"));
                	jsonobject.put("dorm", rs.getString("dorm"));
                	jsonobject.put("roomnum", rs.getString("roomnum"));
                	jsonarray.add(jsonobject);
                	jsonobject = new JSONObject();
            }
            
            jsonresult.put("students",jsonarray);
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