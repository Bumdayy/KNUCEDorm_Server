<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.sql.*, java.util.*"%>
<%@ page import="org.json.simple.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Read Database Page</title>
</head>
<body>
	<%
   		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.131:1521:orcl", "bumday", "789513");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select JSON_OBJECT('ID', ID, 'NAME', NAME) from STUINFO");
	%>
	<%
	
	JSONObject json = new JSONObject();
	JSONArray jsonArray = new JSONArray();
	JSONObject result = new JSONObject();

	while(rs.next()){
	    json.put("id", "시발");
	    jsonArray.add(json);
	    json = new JSONObject();
	}
	
	result.put("rows", jsonArray);
	
		while (rs.next()) {
	%>

	<br> id:<%=rs.getString("ID")%><br>
	<br> name:<%=rs.getString("NAME")%><br>
	--------------------------------------------
	<br>
	<br>
	<%
		}
		} catch (SQLException e) {
	%>
	<%
		e.printStackTrace();
	%>
	<%
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
				}
		}
	%>

</body>
</html>