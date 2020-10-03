<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	String name[] = {"Java", "JSP", "Android", "야호"};
%>
<table border="1">
	<% for (int i = 0; i < name.length; ++i) { %>
		<tr>
			<td><%= i%></td> <td><%= name[i]%></td>
		</tr>
	<% } %>
</table>

</body>
</html>