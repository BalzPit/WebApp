<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Login</title>
		<c:import url="/jsp/head.jsp"/>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/login-style.css"/>">

		<script>
			$(function() {

				if($(window).height() > $("body").height()) $("body").css("height", $(window).height());

			});
		</script>
		<style>
			input[type="text"], input[type="password"] {
				background-size: auto 20px;
				background-position: 10px center;
				background-repeat: no-repeat;
			}
			#box {
				height: 450px;
				margin: auto;
			}
			input[name=user] {
				background-image: url(user.png);
			}
			input[name=psw] {
				background-image: url(key.png);
			}
		</style>
	</head>
	<body>
		<div id="box">
			<img src="<c:url value="/media/logo.png"/>" alt="Logo 4Doctors" title="Logo 4Doctors">
			<form id="login_form" method="POST" action="<c:url value="/user/login"/>">
				<input id="cf" name="cf" type="text" placeholder="Fiscal Code"><br>
				<div class="error"></div>
				<input id="password" name="password" type="password" placeholder="Password"><br>
				<div class="error"></div>
				<div>
					<label><input id="patient" type="radio" name="role" value="patient"> Patient</label>
					<label><input id="doctor" type="radio" name="role" value="doctor"> Doctor</label>
					<div class="error"></div>
				</div>
				<input name="submit" type="submit" value="login">
			</form>
			<br>
			<p>Don't you have an account yet? <a href="${pageContext.request.contextPath}/jsp/patient_registration.jsp">Sign up</a></p>
		</div>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/crypto-js/4.0.0/crypto-js.min.js" integrity="sha512-nOQuvD9nKirvxDdvQ9OMqe2dgapbPB7vYAMrzJihw5m+aNcf0dX53m6YxM4LgA9u8e9eg9QX+/+mPu8kCNpV2A==" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/js/login.js"></script>
	</body>
</html>