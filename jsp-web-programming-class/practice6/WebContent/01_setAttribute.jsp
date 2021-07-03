<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>setAttribute</title>
</head>
<%
	session.setAttribute("id", "dooli");
	session.setAttribute("pw", "5252");
	session.setAttribute("age", 15);
	session.setAttribute("name", "둘리 사우르스");
%>
<body>
	<h3>세션에 4개 넣음!! </h3>
</body>
</html>