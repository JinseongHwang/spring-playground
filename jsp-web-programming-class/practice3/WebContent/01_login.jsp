<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받는쪽</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8"); // request 객체의 메서드를 호출(선언아님)
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
%>
나는 <%=id %>의 비번을 알고있다. <p>
<%=pw %> 맞지? 아하하핳 !!!
</body>
</html>

<!-- 
getParameter 메서드는 고객에 보낸 정보를 받는 역할
스크립트릿 안에 있어도 되고, 바로 표현식에서 호출해도 된다.
request 자체는 내장객체 이름이니까 변수명으로 사용하지 말고, id는 name 속성 값이니까 변수 명으로 사용 가능!
 -->