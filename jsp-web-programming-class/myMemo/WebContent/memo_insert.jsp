<%@ page language="java" contentType ="text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1723940 황진성 - 2</title>
</head>
<body style="background-color:#f5ecce">
<%
	request.setCharacterEncoding("utf-8");
	String title = request.getParameter("title");
	String comments = request.getParameter("comments");
	String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
	String uid = "root"; String pass = "111111";
	// 날짜를 저장할 때,
	// date: YYYY-MM-DD, datetime: YYYY-MM-DD HH:MM:SS
	String sql = String.format("INSERT INTO memo_ VALUES('%s', '%s', sysdate())", title, comments);
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url, uid, pass);
	Statement stmt = conn.createStatement();
	stmt.executeUpdate(sql); // i/u/d 할 때는 executeUpdate 해야한다.
%>
<script>
	alert("잘 저장됐어요 ~~^_^");
	location.href = "memo_list.jsp";
</script>
<% 
} // try - end
catch (Exception e) {
	out.print("죄송합니다. 시스템 상 문제가 생겼어요 <br>" + e.getMessage());
}
%>
</body>
</html>