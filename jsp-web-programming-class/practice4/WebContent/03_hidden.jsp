<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String hobby = request.getParameter("hobby");
	String age = request.getParameter("age");
%>
<head>
<meta charset="UTF-8">
<title><%=name %>님의 취미</title>
</head>
<body style="background-color:#f5ecce">
	<%=name %>님은 <%=age %>세이며, 취미는 <%=hobby %>입니다.
</body>
</html>