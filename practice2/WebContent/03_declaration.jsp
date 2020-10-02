<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Declaration</title>
</head>
<body>
<%! // Declaration 선언문
	String str = "안녕하세요!";
	int a = 5, b = -5;
	public int abs(int n) { // 메서드 선언은 무조건 선언문 안에서!
		if(n < 0) n = -n;
		return n;
	}
%>
<% // Scriptlet
	// 지역변수는 여기다 선언해도 된다! 하지만 메서드는 안됨!
	out.print(str + "<br>");
	out.print(a + "의 절대값: " + abs(a) + "<br>");
	out.print(b + "의 절대값: " + abs(b) + "<br>");
	// 출력 화면에는 영향이 없지만 HTML 소스에 줄바꿈을 해주려면?
	// out.println(...);
	// out.print(..."\n");
%>
</body>
</html>