<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1723940 황진성</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String sex = request.getParameter("sex");
	String age = request.getParameter("age");
	String[] foods = request.getParameterValues("food");
	String handsome = request.getParameter("handsome");
	String aplus = request.getParameter("aplus");
	String iknow = request.getParameter("iknow");
	String comments = request.getParameter("comments");
%>
<body>
	<div style="color: blue">
		<%=age %>세 <%=sex %>
	</div>
	<br>
	좋아하는 음식:
	<div style="color: blue">
	<%if (foods == null) { %>
		없음
	<%} else {
		for (String elem : foods) { %>
			<%=elem %><br>
		<%} }%>
	</div>
	<br>
	외모 자신감(1-5): <span style="color: blue"><%=handsome %></span><br>
	학점 자신감(1-5): <span style="color: blue"><%=aplus %></span><br>
	수업 이해도(1-5): <span style="color: blue"><%=iknow %></span><br>
	<br>
	의견:
	<div style="color: blue">
		<%=comments %>
	</div>
</body>
</html>