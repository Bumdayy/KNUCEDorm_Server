<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="org.json.simple.*"%>
<%
    String DB_URL = "jdbc:oracle:thin:@192.168.25.130:1521:orcl";
    String DB_USER = "bumday";
    String DB_PASSWORD = "789513";
    
    String sql = null; 
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {   
        Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("드라이버 로딩 성공");
        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        System.out.println("접속 성공");
        stmt = conn.createStatement();
         
        if(request.getParameter("name") != null)
        {
            request.setCharacterEncoding("UTF-8");
            String id = new String(request.getParameter("id").getBytes("8859_1"),"UTF-8");
            String name = new String(request.getParameter("name").getBytes("8859_1"),"UTF-8");
            sql = String.format("INSERT INTO XMLTEST(NAME, AGE) values('%s', %s)", id, name);
            System.out.print(sql);
            stmt.executeUpdate(sql);
            System.out.println("추가 성공");
        }
 
        sql = "SELECT * FROM XMLTEST";
        rs = stmt.executeQuery(sql);
        JSONArray arr = new JSONArray();
         
        while(rs.next())
        {
            String name = URLEncoder.encode(rs.getString("id"), "UTF-8");
            String age = URLEncoder.encode(rs.getString("name"), "UTF-8");
            JSONObject obj = new JSONObject();
            obj.put("name", name);
            obj.put("age", age);
            if(obj != null)
                arr.add(obj);
        }
        out.print(arr);
    } catch (Exception e) {
        System.out.println("접속 실패");
        e.printStackTrace();
    }
%>