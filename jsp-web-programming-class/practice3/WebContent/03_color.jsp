<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String color = request.getParameter("color");
%>

<head>
	<meta charset="UTF-8">
	<title>
		<%=name %>의 색
	</title>
</head>
<body style="background-color:<%=color%>">
	<strong><%=name %></strong>님이 좋아하는 색은
	<strong><%=color %></strong>입니다.
</body>
</html>