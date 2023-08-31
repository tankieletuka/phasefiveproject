	<%@ page language="java" contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<%@ include file="header.jsp"%>
	<title>Purchases Admin</title>
	
	</head>
	<body>
		<form action="purchasesAdmin" method="post">
			<div class="container">
			<p>View Purchases</p>
				<div class="row">
					<div class="col-lg-6 col-lg-offset-3">
						<div>
							<a class="nav-link" href="viewPurchasesByDate">1. Purchases By Date</a>
							<a class="nav-link" href="viewPurchasesByDateAndCategory">2. Purchases by Date and Category</a> 
							<a class="nav-link" href="viewPurchasesByDateAndUser">3. Purchases by Date and User</a> 
						</div>
					</div>
				</div>
			</div>
		</form>
	</body>
	</html>