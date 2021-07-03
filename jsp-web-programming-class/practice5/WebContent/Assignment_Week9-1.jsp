<%@ page language="java" contentType ="text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1723940 황진성</title>
</head>
<body style="background-color:#f5ecce">
<%
String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
String uid = "root"; String pass = "111111";
String sql = "select * from sale_";
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url, uid, pass);
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql); // select니까 executeQuery
%>
<table width="800" border="1">
	<tr>
		<th>고객ID</th>
		<th>제품이름</th>
		<th>구매일자</th>
		<th>구매수량</th>
		<th>구매금액</th>
	</tr>
<% while(rs.next()) { %>
	<tr>
		<td><%= rs.getString(1) %></td> <!-- customer_id -->
		<td><%= rs.getString(2) %></td> <!-- product_name -->
		<td><%= rs.getString(3) %></td> <!-- perchase_date -->
		<td><%= rs.getString(4) %></td> <!-- perchase_size -->
		<td><%= rs.getString(5) %></td> <!-- perchase_cost -->
	</tr>
<% } // while - end %> 
</table>
<%
} // try - end
catch (Exception e) {
	out.print("죄송합니다. 시스템 상 문제가 생겼어요 <br>" + e.getMessage());
}
%>
</body>
</html>