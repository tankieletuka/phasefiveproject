<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Search Specific User</title>

</head>
<body>

	<div class="container">
		<p>Search user(s) by full or partial name, surname or email - case insensitive.(<b>* Please include the wildcards in your text, e.g. %abc%</b>)</p>

		<form action="searchUser" method="post">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<table class="table" draggable="true" border="0">
						<tr>
							<td>Name/Surname/Email*</td>
							<td><input type="text" class="form-control" id="searchValue"
								name="searchValue" placeholder="%partial or full%"
								value=${searchValue }></td>

							<td>
								<div class="form-group" align="center">
									<input type="submit" class="btn btn-primary"
										value="Search User(s)" />
								</div>
							</td>
							<td>
								<div class="form-group" align="center">
									<input type="submit" class="btn btn-primary"
										formaction="userAdmin" value="Cancel" />
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>

		<div class="error">
			<c:if test="${empty requestScope.users}">
				<em>${errorMessage}</em>
			</c:if>
		</div>


		<c:if test="${not empty requestScope.users}">
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
		</c:if>

	</div>
</body>
</html>