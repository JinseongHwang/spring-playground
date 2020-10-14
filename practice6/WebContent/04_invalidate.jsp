<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>invalidate</title>
</head>
<%
	session.invalidate();
	// 세션에 대한 완전한 초기화
%>
<body>
	<h3>로그아웃 되었습니다!</h3>
</body>
</html>