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

    // [����X]oracle �������� 
    String jdbcUrl = "jdbc:oracle:thin:@localhost:1522:oracl";
    String userId = "bumday";
    String userPw = "789513";

    // [����X]oracle ���� �����ʱ�ȭ
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    ResultSet rs = null;
    ResultSet rs3 = null;

    // [����X]oracle ���� �� ó������� ���� �����ʱ�ȭ
    String sql = "";
    String sql2 = "";
    String sql3 = "";
    String returns = "�������!";
    
    Integer num = 0;
    String sname ="";
    


    // �Լ�(���ڰ� ���̵�,��й�ȣ)
    public String connectionDB(String id, String type) 
    {
        try {
        	
        	num = 0;
        	
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);
            
            
            // ����ð� ���ϱ�
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String time1 = format1.format(time);
            
            
            //������ �����ϱ�
            for(int i = 1; ;i++) {
            	sql = "SELECT * FROM INOUT WHERE INOUT_NUM = ?";
            	pstmt = conn.prepareStatement(sql);
            	num = i;
            	pstmt.setInt(1, num);
            	
            	rs = pstmt.executeQuery();
            	if (!rs.next()) break;
            }
            
            // name ���ϱ�
            sql3 = "SELECT NAME FROM STUINFO WHERE ID =?";
            pstmt3 = conn.prepareStatement(sql3);
            pstmt3.setString(1, id);
            
            rs3 = pstmt3.executeQuery();
            
            if(rs3.next()) sname = rs3.getString("name");


            if (!(num==0)) {////////////////////////////// ���̺������, �̰��� ����
            	//�԰���
            	if (type.equals("in")) {
	                sql2 = "INSERT INTO INOUT(INOUT_NUM, NAME, ID, WHEN, WHAT) VALUES(?, ?, ?, ?, '�԰�')";
	                pstmt2 = conn.prepareStatement(sql2);
	                pstmt2.setInt(1, num);
	                pstmt2.setString(2, sname);
	                pstmt2.setString(3, id);
	                pstmt2.setString(4, time1);
	                pstmt2.executeUpdate();
	                returns = "(" + sname + ")�� �԰� ó���Ǿ����ϴ�.";
                //�����
            	}else if(type.equals("out")) {
	                sql2 = "INSERT INTO INOUT(INOUT_NUM, NAME, ID, WHEN, WHAT) VALUES(?, ?, ?, ?, '���')";
	                pstmt2 = conn.prepareStatement(sql2);
	                pstmt2.setInt(1, num);
	                pstmt2.setString(2, sname);
	                pstmt2.setString(3, id);
	                pstmt2.setString(4, time1);
	                pstmt2.executeUpdate();
	                returns = "(" + sname + ")�� ��� ó���Ǿ����ϴ�.";
            	}
            } else {
                returns = "INSERT���� ������� �ʾҽ��ϴ�.";
            }
            
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