<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사(1)</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	session.setAttribute("id", request.getParameter("id"));
%>
<body>
	<h1>좋아하는 화가</h1>
	<form method="post" action="Assignment_Week7-3.jsp">
		<input type="radio" name="artist" value="몽크"/>몽크<p>
		<input type="radio" name="artist" value="달리"/>달리<p>
		<input type="radio" name="artist" value="고흐"/>고흐<p>
		<input type="radio" name="artist" value="이중섭"/>이중섭<p>
		<input type="submit" value="확인"/>
	</form>
</body>
</html>