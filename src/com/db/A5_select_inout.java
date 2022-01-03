package com.db;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class A5_select_inout {
    private static A5_select_inout instance = new A5_select_inout();

    public static A5_select_inout getInstance() {
        return instance;
    }
    public A5_select_inout() {  }

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
    String returns = "DB�о���µ� �����Ͽ����ϴ�.";

    // �Լ�(���ڰ� ���̵�,��й�ȣ)
    public String connectionDBJSON() 
    {
        try {
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT * FROM INOUT ORDER BY inout_num DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            JSONObject jsonobject = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonresult = new JSONObject();
            
            
            while(rs.next()) {
            		jsonobject.put("inout_num", rs.getString("inout_num"));
                	jsonobject.put("id", rs.getString("id"));
            		jsonobject.put("name", rs.getString("name"));
                	jsonobject.put("what", rs.getString("what"));
                	jsonobject.put("when", rs.getString("when"));
                	jsonarray.add(jsonobject);
                	jsonobject = new JSONObject();
            }
            
            jsonresult.put("inout",jsonarray);
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