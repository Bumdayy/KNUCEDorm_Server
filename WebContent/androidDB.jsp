<%@page import="com.db.ConnectDB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   ConnectDB connectDB = ConnectDB.getInstance();
	
	//한글 안깨지게 인코딩처리
   request.setCharacterEncoding("UTF-8");
	
	//request로 안드로이드에서 보낸값들을 받음
   String id = request.getParameter("id");
   String name = request.getParameter("name");
   String pwd = request.getParameter("pwd");
   String address = request.getParameter("address");
   String major = request.getParameter("major");
   String telnum = request.getParameter("telnum");
   String dorm = request.getParameter("dorm");
   String roomnum = request.getParameter("roomnum");
	
   String returns = connectDB.connectionDB(id, name, pwd, address, major, telnum, dorm, roomnum);

   //웹페이지 및 이클립스에서 결과출력
   System.out.println(returns);

   // 안드로이드로 전송
   out.println(returns);
%>