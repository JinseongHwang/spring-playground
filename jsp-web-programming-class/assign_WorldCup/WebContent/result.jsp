<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 보기</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
	String uid = "root"; String pass = "111111";
	String sql = "select * from worldcup";
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, uid, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
%>
		<table border="1">
			<tr>
				<th>Company</th> <th>Number of votes</th>
			</tr>
			<% while (rs.next()) { %>
			<tr>
				<td><%=rs.getString("star") %></td>
				<td><%=rs.getInt("vote") %></td>
			</tr>
			<% } %>
		</table>
<%		
	} catch (Exception e) {
		out.print("결과 확인 과정에서 예외 발생! <p>" + e.getMessage());
	}
%>
<body>
	<p>
	<a href="menu.jsp">메뉴로 돌아가기</a>
</body>
</html>