<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Receiver</title>
</head>
<body>
<%
	String iam = request.getParameter("iam");
	String youare = request.getParameter("youare");
%>
2 <p>
<%=iam %> <%=youare %> <p>
3 <p>

</body>
</html>

<!-- 여기서 실행했을 경우 어떻게 출력될까? -->
<!-- 
	2
	null null
	3
	
	이 출력된다.

	getParameter를 못받아왔기 때문이다.
	오류가 발생하지는 않는다 !
 -->