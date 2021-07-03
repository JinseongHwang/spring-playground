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
String sql = "select * from test_table";
/*
	정렬하고자 하면 order by 를 뒤에 붙인다.
	예를들어, 
	String sql = "select * from test_table order by name"; 와 같이 표현한다.
*/
try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn = DriverManager.getConnection(url, uid, pass);
	Statement stmt = conn.createStatement(); // 3c로 했음. 3p는 나중에
	ResultSet rs = stmt.executeQuery(sql); // select니까 executeQuery (4Q)
%>
<table width="800" border="1"> <!-- width를 지정하는 것은 HTML5 이전 문법이므로 주의 표시가 생긴다. -->
	<tr>
		<th>id</th><th>이름</th><th>아이디</th><th>암호</th><th>이메일</th><th>전화번호</th><th>구분</th>
	</tr>
<%
	while (rs.next()) {
		// 컬러명 대신 컬럼 순서(index)로 해도 된다.
		// 예를 들어 rs.getString("name") 대신에 rs.getString(2) 도 가능~
		// 참고로 index 참조 할 때는 1부터 시작이네? 왜지?
		out.println("<tr>");
		out.println("<td>" + rs.getString("id") + "</td>");
		out.println("<td>" + rs.getString("name") + "</td>");
		out.println("<td>" + rs.getString("userid") + "</td>");
		out.println("<td>" + rs.getString("pwd") + "</td>");
		out.println("<td>" + rs.getString("email") + "</td>");
		out.println("<td>" + rs.getString("phone") + "</td>");
		out.println("<td>" + rs.getInt("admin") + "</td>"); // getString 해도됨
		out.println("</tr>");
	} // while-end
%>
</table>
<% 
} // try - end
catch (Exception e) {
	out.print("죄송합니다. 시스템 상 문제가 생겼어요 <br>" + e.getMessage());
}
%>
</body>
</html>