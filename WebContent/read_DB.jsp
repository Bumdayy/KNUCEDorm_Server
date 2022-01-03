<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Read Database Page</title>
</head>
<body>
<%@ page import= "java.sql.*, java.util.*" %>
MySQL 데이터 읽기
<% //[수정X] 연결과정
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection conn = null;
Statement stmt = null;
ResultSet rs = null;
try{
	//[IP변경시 수정] 연결관련함수
	conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.25.130:1521:orcl", "bumday", "789513");
	stmt = conn.createStatement();
	//질의문
	rs = stmt.executeQuery("select * from STUINFO");
%>
<br><br>---------------------------------------------------<br>
<% while(rs.next()) { %>
<br> ID: <%=rs.getString("id") %><br>
<br> NAME: <%=rs.getString("name") %> <br>
<br> PWD: <%=rs.getString("pwd") %> <br>
<br> ADDRESS: <%=rs.getString("address") %> <br>
<br> MAJOR: <%=rs.getString("major") %> <br>
<br> TELNUM: <%=rs.getString("telnum") %> <br>
<br> DORM: <%=rs.getString("dorm") %> <br>
<br> ROOMNUM: <%=rs.getString("roomnum") %> <br>
------------------------------------------------------<br><br>
<%
}
//예외처리
}catch(SQLException e) {%>
<% e.printStackTrace(); %>
<%
} //예외처리 
finally {if(rs!=null)try{rs.close();} catch(SQLException e){}
	if(stmt!=null)try{stmt.close();} catch(SQLException e){}
	if(conn!=null)try{conn.close();}catch(SQLException e){}
}
%>
</body>
</html>