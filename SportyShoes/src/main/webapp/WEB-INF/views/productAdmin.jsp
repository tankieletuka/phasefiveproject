<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Products Admin</title>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-lg-6 col-lg-offset-3">
				<div align="right">
					<a class="nav-link" href="addCategory">1. Add Product Category</a> <a
						class="nav-link" href="viewCategories">2. View Product Categories</a> <a
						class="nav-link" href="addProduct">3. Add More Products</a> <br>
				</div>
				<p>Registered Products</p>
				<c:if test="${not empty updateMessage}">
					<div class="error">
						<p style="color: red">
							<em>${updateMessage}</em>
						</p>
					</div>
				</c:if>
				<table class="table">
					<tr>
						<th>ID</th>
						<th>Description</th>
						<th>Category</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
					<c:forEach var="product" items="${requestScope.products }">
						<tr
							<c:if test="${product.prodId eq updatedProduct}"> style="color: red"</c:if>>
							<td>${ product.prodId}</td>
							<td>${ product.prodDescription}</td>
							<td>${ product.catDescription}</td>
							<td><a
								href="editProduct?prodId=${product.prodId}&prodDescription='${product.prodDescription}'&catDescription='${product.catDescription}'">
									Edit</a></td>
							<td><a
								href="deleteProduct?prodId=${product.prodId}&prodDescription='${product.prodDescription}'&catDescription='${product.catDescription}'">
									Delete</a></td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</div>
</body>
</html>