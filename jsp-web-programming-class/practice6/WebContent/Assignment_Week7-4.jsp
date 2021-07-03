<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 결과</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String id = (String)session.getAttribute("id");
	String artist = (String)session.getAttribute("artist");
	String musician = request.getParameter("musician");
	session.invalidate();
%>
<body style="background-color:lime">
	<%=id %>님은 <p>
	<strong><%=artist %></strong> 화가님과 <p>
	<strong><%=musician %></strong> 음악가님을 선택하셨습니다!!
</body>
</html>