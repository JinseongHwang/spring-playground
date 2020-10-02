<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Expression</title>
</head>
<body>
<%!
	String str = "안녕하세요!";
	int a = 5, b = -5;
	public int abs(int n) { 
		if(n < 0) n = -n;
		return n;
	}
%>
<%= str%><br>
<%= a%>의 절대값: <%= abs(a)%><br>
<%= b%>의 절대값: <%= abs(b)%><br>
</body>
</html>