<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Product Registration</title>

<style>
</style>
</head>
<body>


	<div class="container">
		<p>Product Registration</p>
		<br>
		<c:if test="${not empty errorMessage}">
			<div class="error">
				<em>${errorMessage}</em>
			</div>
		</c:if>

		<form action="addProduct" method="post">

			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<table class="table" draggable="true" border="0">
						<tr>
							<td>Description:</td>
							<td><input type="text" class="form-control"
								id="prodDescription" placeholder="***enter description***"
								name="prodDescription" value='${prodDescription }'></td>
						</tr>
						<tr>
							<td>Category:</td>
							<td><select name="catId">
									<option value=0>Select Category</option>
									<c:forEach items="${requestScope.categories}" var="category">
										<option value="${category.catId }"
											<c:if test="${category.catDescription eq catDescription}"> selected="selected"</c:if>>
											${category.catDescription}</option>
									</c:forEach>
							</select></td>
						</tr>
					</table>

					<div align="center">
						<input type="submit" class="btn btn-primary" value="Add Product" />
						<input type="submit" class="btn btn-primary"
							formaction="productAdmin" value="Cancel" />

					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>