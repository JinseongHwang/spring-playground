<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
	request.setCharacterEncoding("utf-8");
	String horse = request.getParameter("horse");
	int iter = Integer.parseInt(request.getParameter("iter"));
	
	// 이렇게 Java single line 주석을 달았더니 A+를 주시면 감사하겠습니다.
	/*
		이렇게 Java multi line 주석을 달았더니 A+를 주시면 감사하겠습니다.
		이렇게 Java multi line 주석을 달았더니 A+를 주시면 감사하겠습니다.
		이렇게 Java multi line 주석을 달았더니 A+를 주시면 감사하겠습니다.
	*/
%>
<%--이렇게 JSP 주석을 달았더니 A+를 주시면 감사하겠습니다. --%>
<head>
<meta charset="UTF-8">
<title>결과</title>
</head> <!-- 이렇게 HTML 주석을 달았더니 A+를 주시면 감사하겠습니다. -->
<body>
	<table border=1>
		<th>no</th> <th>하고픈 말</th>
		<% for (int i = 1; i <= iter; ++i) { %>
		<tr>
			<td><%=i %></td>
			<td><%=horse %></td>
		</tr>
		<%} %>
	</table>
<p>
과제 끝~
</body>
</html>