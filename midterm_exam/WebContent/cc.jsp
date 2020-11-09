<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("utf-8");
	String part = request.getParameter("part");
%>
<head>
<meta charset="UTF-8">
<title>환영합니다</title>
</head>
<body>
	축하합니다!<p>
	<b><%=part %></b> 으로 임용되셨습니다!!
</body>
</html>