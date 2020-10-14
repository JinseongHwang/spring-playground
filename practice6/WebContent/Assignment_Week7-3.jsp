<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사(2)</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	session.setAttribute("artist", request.getParameter("artist"));
%>
<body>
	<h1>좋아하는 음악가</h1>
	<form method="post" action="Assignment_Week7-4.jsp">
		<select name="musician">
			<option>베토벤</option>
			<option>생상스</option>
			<option>모짜르트</option>
			<option>아이유</option>
		</select>
		&nbsp;
		<input type="submit" value="확인"/>
	</form>
</body>
</html>