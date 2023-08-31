<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Delete Product</title>
	
</head>
<body>

	<div class="container">
		<p>Please confirm you want to delete Product</p>

		<form action="deleteProduct" method="post">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3">
					<table class="table" draggable="true" border="0">

						<tr>
							<th>Product ID</th>
							<th>Product Description</th>
							<th>Product Category</th>
						</tr>
						<tr>

							<td><input type="text" class="form-control" id="prodId"
								readonly="readonly" name="prodId"
								value=${requestScope.product.prodId }></td>

							<td><input type="text" class="form-control"
								id="prodDescription" readonly="readonly" name="prodDescription"
								value=${requestScope.product.prodDescription }></td>

							<td><input type="text" class="form-control"
								id="catDescription" readonly="readonly" name="catDescription"
								value=${requestScope.product.catDescription }></td>
							<td>
								<div class="form-group" align="center">
									<input type="submit" class="btn btn-primary"
										value="Confirm Delete" />
								</div>
							</td>
							<td>
								<div class="form-group" align="center">
									<input type="submit" class="btn btn-primary" formaction="productAdmin"
										value="Cancel" />
								</div>
							</td>
						</tr>

					</table>
				</div>
			</div>
		</form>
	</div>


</body>
</html>