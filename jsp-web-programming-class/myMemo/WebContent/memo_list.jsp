<%@ page language="java" contentType ="text/html; charset=UTF-8" pageEncoding ="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1723940 황진성 - 3</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String url = "jdbc:mysql://localhost:3306/jspstudy?serverTimezone=UTC";
	String uid = "root"; String pass = "111111";
	String sql = "select * from memo_ order by wdate";
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url, uid, pass);
	Statement stmt = conn.createStatement(); // 3c로 했음. 3p는 나중에
	ResultSet rs = stmt.executeQuery(sql); // select니까 executeQuery (4Q)
%>
<body style="background-color: aqua">
	<div align="center">
		<h3>나의 메모(시간순)</h3>
		<table width="600" border="1">
			<tr>
				<th>제목</th> <th>내용</th> <th>일시</th>
			</tr>
			<%while (rs.next()) { %>
				<tr>
					<td style="color: blue"><%=rs.getString("title") %></td>
					<td><%=rs.getString("comments") %></td>
					<td><%=rs.getString("wdate") %>
				</tr>
			<%} %>
		</table><br>
		<a href="memo.html">작성화면으로 돌아가기</a>
	</div>
<% 
} // try - end
catch (Exception e) {
	out.print("예외상황 발생! 고객센터로 연락 바랍니다.<p>" + e.getMessage());
}
%>
</body>
</html>