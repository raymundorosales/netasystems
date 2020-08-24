<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Meta, title, CSS, favicons, etc. -->

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Neta Systems Login</title>

<link href="dist/bootstrap.min.css" rel="stylesheet">
<link href="dist/font-awesome.min.css"	rel="stylesheet">
<link href="dist/nprogress.css" rel="stylesheet">
<link href="dist/animate.min.css" rel="stylesheet">

<!-- Custom Theme Style -->
<link href="dist/custom.min.css" rel="stylesheet">
</head>

<body class="login">
	<div>
		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<form:form method="post" modelAttribute="login">
						
						<h1>Login Form</h1>
						<div>
							<p class="red"><i><form:errors path="*" cssClass="error" /></i></p>
							<p class="red"><i>${error}</i></p>
						</div>
						<div>
							<form:input path="id" class="form-control" placeholder="Username" minlength="10" />
							<span class="red"><i><form:errors path="id" cssClass="error" /></i></span>
						</div>
						<div>
							<form:password path="password" class="form-control" placeholder="Password" minlength="5" />
							<span class="red"><i><form:errors path="password" cssClass="error" /></i></span>
						</div>
						<div>
							<input type="submit" value="Log in" class="btn btn-default">
						</div>

						<div class="clearfix"></div>
					</form:form>
				</section>
			</div>
		</div>
	</div>
</body>
</html>