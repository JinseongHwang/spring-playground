<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공!
<ul>
    <%-- 원론적인 방법 --%>
    <li>id: <%=((Member) request.getAttribute("member")).getId()%></li>

    <%-- JSP 에서 제공하는 편한 방법 --%>
    <li>username: ${member.username}</li>
    <li>age: ${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
