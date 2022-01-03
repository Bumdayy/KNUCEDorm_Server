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

    // [����X]oracle �������� 
    String jdbcUrl = "jdbc:oracle:thin:@192.168.1.32:1522:oracl";
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
    String returns = "�������!";

    Integer num = 0;
    
    // �Լ�(���ڰ� ���̵�,��й�ȣ)
    public String connectionDB(String id, String name, String why, String sdate, String edate) 
    {
        try {
        	num = 0;
        	
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            
            //������ �����ϱ�
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
            
            
            if (!(num==0)) {////////////////////////////// ���̺������, �̰��� ����
            	//�԰���
	                sql2 = "INSERT INTO SLOUT(SLOUT_NUM, ID, NAME, WHY, SDATE, EDATE) VALUES(?, ?, ?, ?, ?, ?)";
	                pstmt2 = conn.prepareStatement(sql2);
	                pstmt2.setInt(1, num);
	                pstmt2.setString(2, id);
	                pstmt2.setString(3, name);
	                pstmt2.setString(4, why);
	                pstmt2.setString(5, sdate);
	                pstmt2.setString(6, edate);
	                pstmt2.executeUpdate();
	                returns = "�ܹڽ�û �Ǿ����ϴ�.";
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