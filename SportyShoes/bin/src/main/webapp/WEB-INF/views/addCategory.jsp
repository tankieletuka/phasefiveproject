<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Category Registration</title>

</head>
<body>


	<div class="container">
		<p>Category Registration</p>
		<br>
		<c:if test="${not empty errorMessage}">
			<div class="error">
				<p style="color: red">
					<em>${errorMessage}</em>
				</p>
			</div>
		</c:if>

		<form action="addCategory" method="post">

			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<table class="table" draggable="true" border="0">
						<tr>
							<td>Description:</td>
							<td><input type="text" class="form-control"
								id="catDescription" placeholder="***enter description***"
								name="catDescription" value='${catDescription }'></td>
						</tr>
					</table>

					<div align="center">
						<input type="submit" class="btn btn-primary" value="Add Category" />
						<input type="submit" class="btn btn-primary" formaction="productAdmin"
							value="Cancel" />

					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>