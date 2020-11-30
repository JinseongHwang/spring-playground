<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>survey_iu - 1723940 황진성</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String star = request.getParameter("star");
	int currentStarCount = -1;
	
	String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
	String uid = "root"; String pass = "111111";
	String sql = String.format("insert into survey_ values('%s', sysdate(), '%s')", id, star);
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, uid, pass);
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		
		ResultSet rs = stmt.executeQuery(String.format("select vote from result_ where star='%s'", star));
		rs.next();
		currentStarCount = rs.getInt(1);
		
		sql = String.format("update result_ set vote=%d where star='%s'", currentStarCount + 1, star);
		stmt.executeUpdate(sql);
	%>
	<script>
		alert("투표해주셔서 감사합니다~~~");
		location.href="result.jsp";
	</script>
	<% } catch (Exception e) {
		out.print("투표 결과를 DB에 연동 중 예외 발생! <p>" + e.getMessage());
	}
%>

</html>