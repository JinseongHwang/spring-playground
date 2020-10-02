<!-- 지시자 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tags practice</title>
</head>
<body>
<% // scriptlet
int num1 = 20;
int num2 = 10;
int add = num1 + num2;
// scriptlet 안에서 실제 html에 영향을 주는 것은 out.print이다.
out.print(num1 + " + " + num2 + "<br/> = " + add); // html에 출력하는 것(console 출력 x)
%>
</body>
</html>