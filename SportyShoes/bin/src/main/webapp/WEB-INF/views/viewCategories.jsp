<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Registered Categories</title>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<p>Registered Categories</p>


				<table class="table" draggable="true" border="0">
					<tr>
						<th>ID</th>
						<th>description</th>
					</tr>

					<c:forEach var="category" items="${categories}">
						<tr>
							<td>${ category.catId}</td>
							<td>${ category.catDescription}</td>
						</tr>
					</c:forEach>
				</table>
				
				<div align="right">
					<a class="nav-link" href=productAdmin>Back to Products</a> <br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>