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

<!-- jsp:include 액션 태그 방식으로 데이터 전송 -->
<!-- 표현되는 주소창에 ~~01_include.jsp~~ 라고 표시된다. -->
<jsp:include page="02_receiver.jsp">
	<jsp:param name="iam" value="APPLE"/>
	<jsp:param name="youare" value="CARROT"/>
</jsp:include>

4   끝 <p>

<!-- 1과 4 사이에 include 한 파일의 내용이 추가된다. -->

</body>
</html>

<!-- include 액션태그랑 include 지시자는 어떤 차이가 있을까?

<include 액션 태그>
: 화면 자체를 현재 화면에다가 복붙해준다.

<include 지시자>
: 소스코드 자체를 include 위치에다가 복붙해준다.
따라서 중간에 들어가도 상관없는 소스코드만 include 지시자로 쓸 수 있다.
!! 이 경우에는 include와 동시에 get 형식으로 데이터 전송이 불가능하다. !!
!! 지역 변수 선언 시에도 똑같은 변수 명으로 선언하면 안된다. 하지만 액션 태그는 상관없다. !!
 -->