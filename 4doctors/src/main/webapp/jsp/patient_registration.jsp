<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Sign up</title>
    <c:import url="/jsp/head.jsp"/>
	<link type="text/css" rel="stylesheet" href="<c:url value="/css/login-style.css"/>">
	<script>
		$(function() {

			if($(window).height() > $("body").height()) $("body").css("height", $(window).height());

		});
	</script>
</head>
<body>
	<div id="box">
		<img src="<c:url value="/media/logo.png"/>" alt="Logo 4Doctors" title="Logo 4Doctors">
		<h2>Registration</h2>
		<form method="POST" action="<c:url value="/patient"/>">
			<input name="cf" type="text" placeholder="Fiscal Code" required pattern="[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}">
			<input name="name" type="text" placeholder="Name" required pattern="[A-Za-z ]+">
			<input name="surname" type="text" placeholder="Surname" required pattern="[A-Za-z ]+">
			<input name="email" type="text" placeholder="Email" required pattern=".*@.*\..+">
			<br>
			<label>Date of birth</label>
			<input name="birthday" type="date" placeholder="Date of birth" required>
			<input name="birthplace" type="text" placeholder="Place of birth" required pattern="[\w/ ]+">
			<input name="address" type="text" placeholder="Residence Address" required pattern="[\w/ ]+">
			<div>
				<label><input type="radio" name="gender" required> Male </label>
				<label><input type="radio" name="gender" required> Female</label>
			</div>
			<input name="password" type="password" placeholder="Password" required pattern=".{8,}">
			<input name="retype_password" type="password" placeholder="Retype Password" required pattern=".{8,}">
			<br><br>
			<input name="submit" type="submit" value="Create account">
		</form>
	</div>
</body>
</html>