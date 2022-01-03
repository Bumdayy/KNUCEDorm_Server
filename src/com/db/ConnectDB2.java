package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB2 {
    private static ConnectDB2 instance = new ConnectDB2();

    public static ConnectDB2 getInstance() {
        return instance;
    }
    public ConnectDB2() {  }

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
    String returns = "�������!";

    // �Լ�(���ڰ� ���̵�,��й�ȣ)
    public String connectionDB2(String id, String name) 
    {
        try {
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT * FROM STUINFO WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {////////////////////////////// ���̺������, �̰��� ����
                sql2 = "DELETE FROM STUINFO WHERE id = ?";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, id);
                pstmt2.executeUpdate();
                returns = name.concat("���� ������ �����Ǿ����ϴ�.") ;
            } else {
                returns = "�������� �ʴ� ȸ���Դϴ�.";
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