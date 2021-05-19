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

			<!-- display the message -->
			<div id="message">
				<c:if test='${not empty message}'>
					<c:import url="/jsp/include/show-message.jsp"/>
				</c:if>
			</div>

			<form id="registration_form" method="POST" action="<c:url value="/patient"/>">
				<input id="cf" name="cf" type="text" placeholder="Fiscal Code">
				<div class="error"></div>
				<input id="name" name="name" type="text" placeholder="Name">
				<div class="error"></div>
				<input id="surname" name="surname" type="text" placeholder="Surname">
				<div class="error"></div>
				<input id="email" name="email" type="email" placeholder="Email">
				<div class="error"></div>
				<br>
				<label>Date of birth</label>
				<input id="birthday" name="birthday" type="date" placeholder="Date of birth">
				<div class="error"></div>
				<input id="birthplace" name="birthplace" type="text" placeholder="Place of birth">
				<div class="error"></div>
				<input id="address" name="address" type="text" placeholder="Residence Address">
				<div class="error"></div>
				<div>
					<label><input id="male" type="radio" name="gender" value="M"> Male </label>
					<label><input id="female" type="radio" name="gender" value="F"> Female</label>
					<div class="error"></div>
				</div>
				<input id="password" name="password" type="password" placeholder="Password">
				<div id="password_strength"></div>
				<div class="error"></div>
				<input id="retype_password" name="retype_password" type="password" placeholder="Retype Password">
				<div class="error"></div>
				<br><br>
				<input name="submit" type="submit" value="Create account">
			</form>
		</div>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" integrity="sha512-nOQuvD9nKirvxDdvQ9OMqe2dgapbPB7vYAMrzJihw5m+aNcf0dX53m6YxM4LgA9u8e9eg9QX+/+mPu8kCNpV2A==" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/js/registration.js"></script>
	</body>
</html>