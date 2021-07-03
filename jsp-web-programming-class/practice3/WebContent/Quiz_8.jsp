<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>반복출력</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String str = request.getParameter("str");
	int iter = Integer.parseInt(request.getParameter("iter"));
%>
<body>
<% for (int i = 0; i < iter; ++i) { %>
	<strong><%=str %></strong><br>	
<% } %>
</body>
</html>