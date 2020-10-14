<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>getAttribute</title>
</head>
<%
	String id = (String)session.getAttribute("id");
	String pw = (String)session.getAttribute("pw");
	Integer age = (Integer)session.getAttribute("age");
	String name = (String)session.getAttribute("name");
%>
<body style="background-color:aqua">
	<h3>세션값 하나씩 가져오기</h3>
	id는 <%=id %><br>
	비번은 <%=pw %><br>
	나이는 <%=age %><br>
	이름은 <%=name %><br>
</body>
</html>