package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance = new ConnectDB();

    public static ConnectDB getInstance() {
        return instance;
    }
    public ConnectDB() {  }

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
    public String connectionDB(String id, String name, String pwd, String address, String major, String telnum, String dorm, String roomnum) 
    {
        try {
            // [����X]DB����. ����- <DB URL, ���̵�, �н�����>
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(jdbcUrl, userId, userPw);

            sql = "SELECT id FROM STUINFO WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                returns = "�̹� �����ϴ� ȸ�� �Դϴ�.";
            } else {////////////////////////////// ���̺������, �̰��� ����
                sql2 = "INSERT INTO STUINFO VALUES(?,?,?,?,?,?,?,?)";
                pstmt2 = conn.prepareStatement(sql2);
                pstmt2.setString(1, id);
                pstmt2.setString(2, name);
                pstmt2.setString(3, pwd);
                pstmt2.setString(4, address);
                pstmt2.setString(5, major);
                pstmt2.setString(6, telnum);
                pstmt2.setString(7, dorm);
                pstmt2.setString(8, roomnum);
                pstmt2.executeUpdate();
                returns = "ȸ������ �Է� ���� !";
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