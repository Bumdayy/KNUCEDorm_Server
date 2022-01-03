<%@page import="com.db.S3_sleepout"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   S3_sleepout connectDB = S3_sleepout.getInstance();
	
	//한글 안깨지게 인코딩처리
   request.setCharacterEncoding("UTF-8");
	
	//request로 안드로이드에서 보낸값들을 받음
   String id = request.getParameter("id");
   String name = request.getParameter("name");
   String why = request.getParameter("why");
   String sdate = request.getParameter("sdate");
   String edate = request.getParameter("edate");
	
   String returns = connectDB.connectionDB(id, name, why, sdate, edate);

   //웹페이지 및 이클립스에서 결과출력
   System.out.println(returns);

   // 안드로이드로 전송
   out.println(returns);
%>