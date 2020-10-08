<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1723940 황진성 #10.JSP</title>
</head>

<%
	request.setCharacterEncoding("utf-8");
	String bread = request.getParameter("bread");
	int cost = Integer.parseInt(request.getParameter("cost"));
	int count = Integer.parseInt(request.getParameter("count"));
	int totalPrice = cost * count;
%>

<body style="background-color:#F5DA81">
	고객님께서 구매하신 빵은<br/>
	<strong><%=bread %></strong>이며<br/>
	<strong><%=count %></strong>개를 구매하셨으므로<br/>
	총 가격은 <strong><%=totalPrice %></strong>원 입니다.<br/>
</body>
</html>