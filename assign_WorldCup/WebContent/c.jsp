<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결승</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String Acompany = request.getParameter("Acompany");
	String Bcompany = request.getParameter("Bcompany");
	String aImgSrc = (String)session.getAttribute(Acompany + "ImgSrc");
	String bImgSrc = (String)session.getAttribute(Bcompany + "ImgSrc");
%>
<body>
	<h2>Who's your favorite company?</h2>
	<h2>!결승!</h2>
	<form method="post" action="d.jsp">
		<table border="1">
			<tr>
				<td>
					<img src=<%=aImgSrc %> width=500 height=500 alt="source: unsplash.com"/>
					<div align="right" style="font-size: 0.8em">source: unsplash.com</div>
				</td>
				<td>
					<img src=<%=bImgSrc %> width=500 height=500 alt="source: unsplash.com"/>
					<div align="right" style="font-size: 0.8em">source: unsplash.com</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="radio" name="Ccompany" value="<%=Acompany%>"/> <%=Acompany%>
				</td>
				<td>
					<input type="radio" name="Ccompany" value="<%=Bcompany%>"/> <%=Bcompany%>
				</td>
			</tr>
		</table>
		<p>
		<input type="submit" value="submit"/>
	</form>

</body>
</html>