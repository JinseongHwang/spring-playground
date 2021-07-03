<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>history - 1723940 황진성</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
	String uid = "root"; String pass = "111111";
	String sql = "select * from survey_ order by sdate desc";
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, uid, pass);
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
%>
	<table border="1">
		<tr>
			<th>ID</th> <th>Voting Date/Time</th> <th>Selected Star</th>
		</tr>
		<%while (rs.next()) { %>
		<tr>
			<td><%=rs.getString("id") %></td> <td><%=rs.getString("sdate") %></td> <td><%=rs.getString("star") %></td>
		</tr>
		<%} %>
	</table>


	<% } catch (Exception e) {
		out.print("history 출력 중 예외 발생! <p>" + e.getMessage());
	} %>
<body>
	<p>
	<a href="menu.jsp">메뉴로 돌아가기</a>
</body>
</html>