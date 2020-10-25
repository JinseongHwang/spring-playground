<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>checkID</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String[] members = {"a", "b", "c", "d", "james", "dooli"}; // 회원 명단
	for(String m : members) {
		if(id.equals(m)) {
			session.setAttribute("id", id);
			session.setMaxInactiveInterval(60*60); // 테스트 위해 1시간
			response.sendRedirect("13_menu.jsp"); // 등록된 id면 Redirect한다.
		}
	}
%>
<body>
	<!-- 위에서 Redirect 하지 않으면 아래로 와서 alert 띄우고 로그인 화면으로 돌아간다. -->
	<script>
		alert("미등록 아이디입니다~ 문의전화: 000)123-4567");
		// location.href 는 response.sendRedirect와 동일한 기능이다.
		location.href="11_login.html";
	</script>
</body>
</html>