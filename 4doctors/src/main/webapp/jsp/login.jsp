<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
</head>

<body>
<h1>Sign In</h1>

<!-- display the message -->
<c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
</c:if>

<c:if test='${not empty patient}'>
    <p>Patient <c:out value="${patient.cf}"/> successfully registered</p>
</c:if>

<form method="POST" action="<c:url value="/user/login"/>">
    <label>Codice Fiscale:</label>
    <input name="cf" type="text"/><br>

    <label>Password:</label>
    <input name="password" type="password"/><br>

    <input type="radio" id="patient" name="role" value="patient">
    <label for="patient">Patient</label>
    <input type="radio" id="doctor" name="role" value="doctor">
    <label for="doctor">Doctor</label><br><br>

    <button type="submit">Log in</button><br>
    <button type="reset">Reset</button>
</form><br>

<label>Don't have an account yet? </label>
<a href="${pageContext.request.contextPath}/jsp/patient_registration.jsp">Sign up</a>
</body>
</html>
