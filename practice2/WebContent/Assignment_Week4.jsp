<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" import="java.util.Calendar" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1723940 황진성</title>
</head>
<body style="background-color: #e1b12c">
<%!
	String name[] = {"고길동", "공유", "둘리", "몽크", "아이유", "원빈", "피카소", "홍길동"};
	public int randomNumber() {
		return (int)(Math.random() * 100) + 1;
	}
	Calendar date = Calendar.getInstance();
	SimpleDateFormat today = new SimpleDateFormat("yyyy년 MM월 dd일");
	SimpleDateFormat now = new SimpleDateFormat("HH시 mm분 ss초");
%>
<h2>중간고사 점수</h2><br>
<table border="1">
	<th>번호</th> <th>이름</th> <th>웹프점수</th>
	<% for (int i = 1; i <= name.length; ++i) { %>
		<tr>
			<td><%=i%></td>
			<td><%=name[i - 1]%></td>
			<td><%=randomNumber()%></td>
		</tr>
	<% } %>
</table>

<p>(
<%= today.format(date.getTime()) %>
<%= now.format(date.getTime()) %>
)</p>

</body>
</html>