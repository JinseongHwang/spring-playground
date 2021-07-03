<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우승</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String winner = request.getParameter("Ccompany");
	String winnerImgSrc = (String)session.getAttribute(winner + "ImgSrc");
	String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
	String uid = "root"; String pass = "111111";
	String sql = "update worldcup set vote=vote+1 where star='" + winner + "'";
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, uid, pass);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
	} catch (Exception e) {
		out.print("득표 수 update 과정에서 예외 발생! <p>" + e.getMessage());
		//e.printStackTrace();
	} finally {
		session.invalidate();
	}
%>
<body>
	<h1>역시 당신은 <%=winner %>로 오실 줄 알았어요 ~</h1>
	<h1>입사를 축하드립니다 !! 당신의 연봉은 10억입니다 !!</h1>
	<img src=<%=winnerImgSrc %> width=700 height=700 alt="source: unsplash.com"/>
	<div align="left" style="font-size: 0.8em">source: unsplash.com</div>
	
	<a href="menu.jsp">메뉴로 돌아가기</a>
</body>
</html>