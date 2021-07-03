<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1723940 황진성 #9.JSP</title>
</head>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String memo = request.getParameter("memo");
%>

<body>
	<h3>Who am I ...</h3><br/>
	<table border="1">
		<tr>
			<td>성명</td>
			<td><%=name %></td>
		</tr>
		<tr>
			<td>메모</td>
			<td><%=memo %></td>
		</tr>
	</table>

</body>
</html>