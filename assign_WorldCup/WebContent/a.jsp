<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>준결승 A</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String samsungImgSrc = (String)session.getAttribute("samsungImgSrc");
	String appleImgSrc = (String)session.getAttribute("appleImgSrc");
%>
<body>
	<h2>Who's your favorite company?</h2>
	<h2>준결승 A</h2>
	<form method="post" action="b.jsp">
		<table border="1">
			<tr>
				<td>
					<img src=<%=samsungImgSrc %> width=500 height=500 alt="source: unsplash.com"/>
					<div align="right" style="font-size: 0.8em">source: unsplash.com</div>
				</td>
				<td>
					<img src=<%=appleImgSrc %> width=500 height=500 alt="source: unsplash.com"/>
					<div align="right" style="font-size: 0.8em">source: unsplash.com</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="Acompany" value="samsung"/> samsung
				</td>
				<td>
					<input type="radio" name="Acompany" value="apple"/> apple
				</td>
			</tr>
		</table>
		<p>
		<input type="submit" value="submit"/>
	</form>

</body>
</html>