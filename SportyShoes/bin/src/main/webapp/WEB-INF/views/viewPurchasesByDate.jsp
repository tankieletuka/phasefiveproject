<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="header.jsp"%>
<title>Purchases by Date</title>

</head>
<body>
	<form action="viewPurchasesByDate" method="post">
		<div class="container">
			<p>Purchases Report</p>
			<div align="right">
				<table>
					<tr>
						<td>Start Date</td>
						<td><input type="text" id="startDate" name="startDate"
							placeholder="yyyy-mm-dd" value=${startDate }></td>
						<td>End Date</td>
						<td><input type="text" id="endDate" name="endDate"
							placeholder="yyyy-mm-dd" value=${endDate }></td>
					</tr>
					<tr>
						<td></td>
					</tr>
				</table>
			</div>


			<div align="right">
				<br> <input type="submit" class="btn btn-primary"
					value="Search Purchases" /> <br> <br>
			</div>
			<div class="error">
				<c:if test="${empty requestScope.purchases}">
					<em>${errorMessage}</em>
				</c:if>
			</div>


			<c:if test="${not empty requestScope.purchases}">
				<table class="table" draggable="true" border="0">
					<tr>
						<th>ID</th>
						<th>user email</th>
						<th>description</th>
						<th>category</th>
						<th>date</th>
						<th>items count</th>
					</tr>

					<c:forEach var="purchases" items="${purchases}">
						<tr>
							<td>${ purchases.purId}</td>
							<td>${ purchases.userEmail}</td>
							<td>${ purchases.prodDescription}</td>
							<td>${ purchases.prodCategory}</td>
							<td>${ purchases.purDate}</td>
							<td>${ purchases.purCount}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
	</form>
</body>
</html>