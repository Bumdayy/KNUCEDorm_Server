package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB_login2 {
    private static DB_login2 instance = new DB_login2();

    public static DB_login2 getInstance() {
        return instance;
    }
    public DB_login2() {  }

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
    String returns = "name return error";

    // 함수(인자값 아이디,비밀번호)
    public String connectionDB(String id) 
    {
        try {
            // [수정X]DB연결. 인자- <DB URL, 아이디, 패스워드>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT name FROM STUINFO WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	returns = rs.getString("name");
            }
            // [수정x]예외처리
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pstmt2 != null)try {pstmt2.close();    } catch (SQLException ex) {}
            if (pstmt != null)try {pstmt.close();} catch (SQLException ex) {}
            if (conn != null)try {conn.close();    } catch (SQLException ex) {    }
        }
        return returns; // 데이터베이스 오류
    }
}