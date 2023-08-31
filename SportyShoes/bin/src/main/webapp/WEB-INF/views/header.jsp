<link rel="stylesheet"
	href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous">
	
</script>


<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script>
	$(function() {
		$("#startDate").datepicker({
			dateFormat : 'yy-mm-dd'
		});
		$("#endDate").datepicker({
			dateFormat : 'yy-mm-dd'
		});

	});
</script>


<%
String email = (String) session.getAttribute("email");
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container-fluid">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" href="productAdmin">Products
						Admin</a></li>
				<li class="nav-item"><a class="nav-link" href="userAdmin">Users
						Admin</a></li>
				<li class="nav-item"><a class="nav-link" href="purchasesAdmin">Purchases
						Reports</a></li>
				<li class="nav-item"><a class="nav-link"
					href="adminChangePassword">Change Password</a></li>
			</ul>
			<em>Logged in as : ${email}<a class="nav-link" href="logout">[Logout]</a></em>
		</div>
	</div>
</nav>




<style>
.error {
	color: red;
	font: italic;
}
</style>

<!-- <p style="text-align: right; font-size: 14px"> -->
<%-- 	<em>Logged in as : ${email}</em><br>[<a href="logout">logout</a>] --%>
<!-- </p> -->