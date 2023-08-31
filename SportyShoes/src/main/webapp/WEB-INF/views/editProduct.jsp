<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Edit Products</title>

</head>
<body>
	<div class="container">
	<p>Edit product details</p>
		<form action="editProduct" method="post">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<table class="table" draggable="true" border="0">
						<tr>
							<td>Product ID:</td>
							<td><input type="text" class="form-control" id="prodId"
								readonly="readonly" placeholder="Enter prodId" name="prodId"
								value=${requestScope.product.prodId }></td>
						</tr>
						<tr>
							<td>Product Description:*</td>
							<td><input type="text" class="form-control"
								id="prodDescription" placeholder="Enter Description"
								name="prodDescription"
								value='${requestScope.product.prodDescription }'></td>
						</tr>
						<tr>
							<td>Product Category:*</td>
							<td><select name="catId">
									<c:forEach items="${requestScope.categories}" var="category">
										<option value="${category.catId }"
											<c:if test="${category.catDescription eq requestScope.product.catDescription}"> selected="selected"</c:if>>
											${category.catDescription}</option>
									</c:forEach>
							</select></td>
						</tr>

					</table>
					<div class="form-group" align="right">
						<input type="submit" class="btn btn-primary" value="Edit Product" />
						<input type="submit" class="btn btn-primary" formaction="productAdmin"
							value="Cancel" />
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>