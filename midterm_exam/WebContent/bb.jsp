<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("utf-8");
	String first = request.getParameter("first");
	String second = request.getParameter("second");
%>
<head>
<meta charset="UTF-8">
<title><%=first %> & <%=second %></title>
</head>
<body>
	조만간 봅시다 ^^ -<b><%=first %></b> 인사 담당 팀장-<p>
	아니요, 우리 회사로 꼭 오세요!! -<b><%=second %></b> 인사 담당 팀장-
</body>
</html>