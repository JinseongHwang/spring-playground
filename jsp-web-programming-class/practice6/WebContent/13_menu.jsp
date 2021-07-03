<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menu</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String id = (String)session.getAttribute("id");
%>
<body style="background-color: lime">
	<h1>MENU</h1>
	<hr>
	<font color="blue"><%=id %></font>님, 오늘도 즐겁게 쇼핑하세요!<br>
	<hr><p>
	
	<!-- ul: 글머리 기호, ol: 번호매기기, 안에 실제 항목들은 li로 나열한다. -->
	<ul> <!-- ul: unordered list, ol: ordered list -->
		<li><a href="https://images.unsplash.com/photo-1568605117036-5fe5e7bab0b7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1350&q=80">car</a>
		<li><a href="https://images.unsplash.com/photo-1535395155851-2088a2a94701?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1108&q=80">bread and tea</a>
		<li><a href="14_logout.jsp">logout</a>
	</ul>
</body>
</html>