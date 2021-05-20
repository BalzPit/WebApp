<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login</title>
	<c:import url="/jsp/head.jsp"/>
	<link type="text/css" rel="stylesheet" href="<c:url value="/css/login-registration-style.css"/>">
	<script src="<c:url value="/js/login-registration-centralizer.js"/>"></script>
</head>
<body>
	<div class="form-box">
		<img src="<c:url value="/media/logo.png"/>" alt="Logo 4Doctors" title="Logo 4Doctors">
		<form method="POST" action="<c:url value="/user/login"/>">
			<input id="user" name="cf" type="text" placeholder="Username" required><br>
			<input id="key" name="password" type="password" placeholder="Password" required><br>
			<div>
				<label><input type="radio" name="role" value="patient" required> Patient</label>
				<label><input type="radio" name="role" value="doctor" required> Doctor</label>
			</div>
			<input name="submit" type="submit" value="login">
		</form>
		<br>
		<p>Don't you have an account yet? <a href="${pageContext.request.contextPath}/jsp/patient_registration.jsp">Sign up</a></p>
	</div>
</body>
</html>