<%@ page language="java" contentType ="text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mysql_update</title>
</head>
<body style="background-color:#f5ecce">
<%
String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
String uid = "root"; String pass = "111111";
String sql = "update test_table set name='둘ㄹㅣ 사우르스' where userid='dooli'";
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url, uid, pass);
	Statement stmt = conn.createStatement();
	stmt.executeUpdate(sql); // i/u/d 할 때는 executeUpdate 해야한다.
%>
update 성공!
<% 
} // try - end
catch (Exception e) {
	out.print("죄송합니다. 시스템 상 문제가 생겼어요 <br>" + e.getMessage());
}
%>
</body>
</html>