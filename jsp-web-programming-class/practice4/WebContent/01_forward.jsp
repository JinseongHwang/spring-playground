<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>forward test</title>
</head>
<body>
1 <p>

<!-- response.sendRedirect 로 get 방식으로 데이터 전송 -->
<!-- 표현되는 주소창에 ~~02_receiver.jsp~~ 라고 표시된다. -->
<% response.sendRedirect("02_receiver.jsp?iam=LATTE&youare=HORSE"); %>

<!-- jsp:forward 액션 태그 방식으로 데이터 전송 -->
<!-- 표현되는 주소창에 ~~01_forward.jsp~~ 라고 표시된다. -->
<jsp:forward page="02_receiver.jsp">
	<jsp:param name="iam" value="APPLE"/>
	<jsp:param name="youare" value="CARROT"/>
</jsp:forward>

4   끝 <p>

<!-- 둘 다 1 4 는 출력 안된다. --> 

</body>
</html>

<!-- jsp:forward VS response.sendRedirect 차이
forward는 주소창에 이름이 변하지 않는다. 호출한 쪽 프로그램(웹페이지)명이 나온다.
반면 sendRedirect는 넘어간쪽 이름이 나온다.(실제 화면을 나타내는 프로그램 이름이 나옴)
개발자는 필요에 따라 선택하면 된다.
 -->