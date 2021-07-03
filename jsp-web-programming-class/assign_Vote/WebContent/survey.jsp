<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>survey - 1723940 황진성</title>
</head>
<body>
	<h2>Who's your favorite star?</h2><p>
	
	<form method="post" action="survey_iu.jsp">
		ID: <input type="text" name="id"/><p>
		<table border="1">
			<tr>
				<td><img src="https://images.unsplash.com/photo-1574144611937-0df059b5ef3e?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=700&q=80" width="200" height="200" alt="출처: unsplash.com"/></td>
				<td><img src="https://images.unsplash.com/photo-1552053831-71594a27632d?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=612&q=80" width="200" height="200" alt="출처: unsplash.com"/></td>
				<td><img src="https://images.unsplash.com/photo-1499114794761-d2743d4eb6f2?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1350&q=80" width="200" height="200" alt="출처: unsplash.com"/></td>
				<td><img src="https://images.unsplash.com/photo-1470854989922-5be2f7456d78?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1355&q=80" width="200" height="200" alt="출처: unsplash.com"/></td>
			</tr>
			<tr>
				<td><input type="radio" name="star" value="cat"/>cat</td>
				<td><input type="radio" name="star" value="dog"/>dog</td>
				<td><input type="radio" name="star" value="frog"/>frog</td>
				<td><input type="radio" name="star" value="hedgehog"/>hedgehog</td>
			</tr>
		</table><p>
		<input type="submit" value="선택"/>
	</form>
</body>
</html>