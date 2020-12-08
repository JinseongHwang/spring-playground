<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>월드컵 과제</title>
</head>
<%
	/*
		과제 내용들은 비슷한데, 세션을 사용한 적이 거의 없는 것 같아 세션을 적극 활용해봤습니다 ~~~
	*/
	request.setCharacterEncoding("utf-8");
	session.setAttribute("samsungImgSrc", "https://images.unsplash.com/photo-1591122947157-26bad3a117d2?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
	session.setAttribute("appleImgSrc", "https://images.unsplash.com/photo-1503852460961-aa7ffdd3d64d?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1350&q=80");
	session.setAttribute("microsoftImgSrc", "https://images.unsplash.com/photo-1583146191066-dd148554b72b?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80");
	session.setAttribute("googleImgSrc", "https://images.unsplash.com/photo-1600783245777-080fd7ff9253?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1267&q=80");
%>
<body>
	<h1>제발 저희 회사로 와주세요 ㅠㅠㅠ</h1>
	<h1>당신은 저희가 찾던 최고의 인재입니다 !!!!</h1>
	<a href="a.jsp">4강전 시작</a><p>
	<a href="result.jsp">결과 보기</a>
</body>
</html>
