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

public class A4_select_slout {
    private static A4_select_slout instance = new A4_select_slout();

    public static A4_select_slout getInstance() {
        return instance;
    }
    public A4_select_slout() {  }

    // [����X]oracle �������� 
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:oracl";
    String userId = "bumday";
    String userPw = "789513";

    // [����X]oracle ���� �����ʱ�ȭ
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;

    // [����X]oracle ���� �� ó������� ���� �����ʱ�ȭ
    String sql = "";
    String returns = "�������!";
    

    // �Լ�(���ڰ� ���̵�,��й�ȣ)
    public String connectionDB(String type) 
    {
        try {
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT slout_num, id, name, why, sdate, edate FROM SLOUT ORDER BY slout_num DESC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            JSONObject jsonobject = new JSONObject();
            JSONArray jsonarray = new JSONArray();
            JSONObject jsonresult = new JSONObject();
            
            
            while(rs.next()) {
            		jsonobject.put("slout_num", rs.getString("slout_num"));
                	jsonobject.put("id", rs.getString("id"));
                	jsonobject.put("name", rs.getString("name"));
                	jsonobject.put("why", rs.getString("why"));
                	jsonobject.put("sdate", rs.getString("sdate"));
                	jsonobject.put("edate", rs.getString("edate"));
                	jsonarray.add(jsonobject);
                	jsonobject = new JSONObject();
            }
            
            jsonresult.put("slout",jsonarray);
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