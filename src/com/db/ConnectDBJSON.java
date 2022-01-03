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

    // [����X]oracle �������� 
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:oracl";
    String userId = "bumday";
    String userPw = "789513";

    // [����X]oracle ���� �����ʱ�ȭ
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;

    // [����X]oracle ���� �� ó������� ���� �����ʱ�ȭ
    String sql = "";
    String sql2 = "";
    String returns = "DB�о���µ� �����Ͽ����ϴ�.";

    // �Լ�(���ڰ� ���̵�,��й�ȣ)
    public String connectionDBJSON(String id, String name, String pwd, String address, 
    		String major, String telnum, String dorm, String roomnum) 
    {
        try {
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
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
            
            // [����x]����ó��
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