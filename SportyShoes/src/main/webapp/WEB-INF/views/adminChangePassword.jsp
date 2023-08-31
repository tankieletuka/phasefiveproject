<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Admin Change Password</title>

</head>
<body>
	<div class="container">
		<p>Change Password</p>
		<c:if test="${not empty errorMessage}">
			<div class="error">
				<p style="color: red">
					<em>${errorMessage}</em>
				</p>
			</div>
		</c:if>
		<form action="adminChangePassword" method="post">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<table class="table" draggable="true" border="0">
						<tr>
							<td>Email:</td>
							<td><input type="text" class="form-control" id="email"
								readonly="readonly" name="email" value=${email }></td>
						</tr>
						<tr>
							<td>Password:*</td>
							<td><input type="password" class="form-control" id="password"
								placeholder="***enter password***" name="password"></td>
						</tr>

					</table>
					<div class="form-group" align="right">
						<input type="submit" class="btn btn-primary"
							value="Change Password" /> <input type="submit"
							class="btn btn-primary" formaction="productAdmin" value="Cancel" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>