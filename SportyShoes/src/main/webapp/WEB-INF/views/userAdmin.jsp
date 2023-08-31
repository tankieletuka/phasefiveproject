<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Users Admin</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div align="right">
					<a class="nav-link" href="registerUser">1. Register More Users</a> 
					<a class="nav-link" href="searchUser">2. Search Specific User</a><br>
				</div>
				<p>Registered Users</p>


				<table class="table" draggable="true" border="0">
					<tr>
						<th>ID</th>
						<th>email</th>
						<th>name</th>
						<th>surname</th>
						<th>isAdmin</th>
					</tr>

					<c:forEach var="user" items="${users}">
						<tr>
							<td>${ user.id}</td>
							<td>${ user.email}</td>
							<td>${ user.name}</td>
							<td>${ user.surname}</td>
							<td>${ user.isadmin}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>