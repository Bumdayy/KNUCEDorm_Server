<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*"%>
<%
    //�� db ���ӿ� ����� conn ����
    Connection conn = null;
    //����Ŭ ����̹� ��� ����
    String driver = "oracle.jdbc.driver.OracleDriver";
    //�� db�� ���� ��� ����
    String url = "jdbc:oracle:thin:@192.168.1.129:1521:TestDB";
    //���� ����,���� ���� ���� ���� ����
    Boolean connect = false;
    
    //db ���� �ڵ�� �ݵ�� try~catch�� �ȿ� �������
    try {
        //����Ŭ ����̹� ����
        Class.forName(driver);
        //�� db ����
        conn = DriverManager.getConnection(url, "bumday", "789513");
        //���Ӽ����� true ����
        connect = true;
        //���� ����, ���� ���� : �׻� db�� ����Ǿ� ������ ���� ���� �� ������ �浹�� �Ͼ�� ���� 
        conn.close();
    } catch (Exception e) {
        //���� ���н� false ��� ����ó������
        connect = false;
        e.printStackTrace();
    }
%>
<%
if(connect==true){%>
    ����Ǿ����ϴ�.
<%}else{ %>
    ���ῡ �����Ͽ����ϴ�.
<%}%>