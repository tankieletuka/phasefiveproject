<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin sign in</title>
</head>
<body>
	<h1>Sporty Shoes - Admin</h1>
	<h3>Please log in</h3>
	<form action="login" method="POST">
		<input type="text" name="email" value="tankieletuka@gmail.com" /> <input
			type="password" name="password" value="password" /> <input
			type="submit" value="Log In" />
	</form>
	<br>

	<c:if test="${not empty errorMessage}">
		<div class="error">
			<p style="color: red">
				<em>${errorMessage}</em>
			</p>
		</div>
	</c:if>

</body>
</html>