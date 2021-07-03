<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>연봉 확인</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String pos = request.getParameter("pos");
	int salary = 0; // 초기화 필수! 없으면 컴파일 에러 발생한다.
	// salary 초기화 하지 않을거면 switch에 default를 설정해주면 된다.
	switch(pos) {
	case "사원": salary = 3500; break;
	case "대리": salary = 5000; break;
	case "팀장": salary = 7000; break;
	case "임원": salary = 9900; break;
	case "회장": salary = 9901; break;
	case "동아대?": response.sendRedirect("http://www.donga.ac.kr"); break;
	// 지금까지 화면이동을 할 때는 a태그 또는 submit버튼으로 action file로 넘어가는 정도 할 수 있었다.
	// 이는 모두 사용자 클릭에 의존적이다.
	// 하지만 sendRedirect()는 개발자가 원하는 위치에 삽입할 수 있다는 차이점이 존재한다.
	
	}
%>
<%=name %> <%=pos %>님의 연봉은<p>
<%=salary %> 만원 입니다.

</body>
</html>