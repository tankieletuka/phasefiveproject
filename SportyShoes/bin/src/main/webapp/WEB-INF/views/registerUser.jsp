<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>User Registration</title>

<style>
.error {
	color: red;
}
</style>
</head>
<body>


	<div class="container">
		<p>User Registration</p>
		<c:if test="${not empty errorMessage}">
			<div class="error">
				<p style="color: red">
					<em>${errorMessage}</em>
				</p>
			</div>
		</c:if>

		<form action="registerUser" method="post">

			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<div class="form-group">
						<label for="name">Name:*</label> <input type="text"
							class="form-control" id="userName" placeholder="Enter Enter"
							name="userName">
					</div>

					<div class="form-group">
						<label for="city">Surname:*</label> <input type="text"
							class="form-control" id="surname" placeholder="Enter surname"
							name="userSurname">
					</div>

					<div class="form-group">
						<label for="customerEmailId">Email:* </label> <input type="text"
							class="form-control" id="userEmail" placeholder="Enter email"
							name="userEmail">
					</div>

					<div align="center">
						<input type="submit" class="btn btn-primary" value="Register" />
						<input type="submit" class="btn btn-primary" formaction="userAdmin"
							value="Cancel" />

					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>