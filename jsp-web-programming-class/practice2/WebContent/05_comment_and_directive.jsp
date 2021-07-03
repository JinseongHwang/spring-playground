<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Comment and Directive</title>
</head>
<body>
<%--
나는 주석이야 ~~~

Directive 지시자는 2가지만 존재한다.
 
@page: 이 JSP 파일의 속성에 대한 표현.
contentType(utf-8..), import, session,...

@include: 소스파일 가져오기
 --%>

<% // page 지시자로 java 라이브러리 include 후 함수 사용
	Calendar date = Calendar.getInstance();
	SimpleDateFormat today = new SimpleDateFormat("yyyy년 MM월 dd일");
	SimpleDateFormat now = new SimpleDateFormat("HH시 mm분 ss초");
%>
오늘은 <strong><%= today.format(date.getTime()) %> </strong> 입니다. <br>
지금 시각은 <strong> <%= now.format(date.getTime()) %> </strong> 입니다. <br>

<%@include file="01_hello.jsp" %>

</body>
</html>