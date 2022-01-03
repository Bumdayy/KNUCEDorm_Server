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

    // [����X]oracle �������� 
    String jdbcUrl = "jdbc:oracle:thin:@192.168.1.32:1522:oracl";
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
    public String connectionDB(String id, String type) 
    {
        try {
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
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