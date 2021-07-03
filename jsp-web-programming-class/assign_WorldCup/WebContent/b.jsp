<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>준결승 B</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String microsoftImgSrc = (String)session.getAttribute("microsoftImgSrc");
	String googleImgSrc = (String)session.getAttribute("googleImgSrc");
	String Acompany = request.getParameter("Acompany");
%>
<body>
	<h2>Who's your favorite company?</h2>
	<h2>준결승 B</h2>
	<form method="post" action="c.jsp?Acompany=<%=Acompany%>">
		<table border="1">
			<tr>
				<td>
					<img src=<%=microsoftImgSrc %> width=500 height=500 alt="source: unsplash.com"/>
					<div align="right" style="font-size: 0.8em">source: unsplash.com</div>
				</td>
				<td>
					<img src=<%=googleImgSrc %> width=500 height=500 alt="source: unsplash.com"/>
					<div align="right" style="font-size: 0.8em">source: unsplash.com</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="Bcompany" value="microsoft"/> microsoft
				</td>
				<td>
					<input type="radio" name="Bcompany" value="google"/> google
				</td>
			</tr>
		</table>
		<p>
		<input type="submit" value="submit"/>
	</form>

</body>
</html>