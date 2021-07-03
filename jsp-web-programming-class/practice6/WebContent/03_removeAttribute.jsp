<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>removeAttribute</title>
</head>
<%
	session.removeAttribute("pw");
%>
<body>
	<h3> pw 하나 삭제했습니다!</h3>
</body>
</html>