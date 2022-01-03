<%@page import="com.db.S3_select_slout"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   S3_select_slout connectDB = S3_select_slout.getInstance();
	
	//한글 안깨지게 인코딩처리
   request.setCharacterEncoding("UTF-8");
	
	//request로 안드로이드에서 보낸값들을 받음
   String id = request.getParameter("id");
	
   String returns = connectDB.connectionDB(id);

   //웹페이지 및 이클립스에서 결과출력
   System.out.println(returns);

   // 안드로이드로 전송
   out.println(returns);
%>