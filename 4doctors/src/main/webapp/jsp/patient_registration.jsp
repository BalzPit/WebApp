<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
</head>

<body>
<h1>Create your account</h1>

<!-- display the message -->
<c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
</c:if>

<form method="POST" action="<c:url value="/create-patient"/>">
    <label>Codice Fiscale:</label>
    <input name="cf" type="text"/><br>

    <label>Name:</label>
    <input name="name" type="text"/><br>

    <label>Surname:</label>
    <input name="surname" type="text"/><br>

    <label>Email:</label>
    <input name="email" type="text"/><br>

    <label>Password:</label>
    <input name="password" type="password"/><br>

    <label>Retype password:</label>
    <input name="retype_password" type="password"/><br>

    <label>Date of birth:</label>
    <input name="birthday" type="date"/><br>

    <label>Place of birth:</label>
    <input name="birthplace" type="text"/><br>

    <label>Residence Address:</label>
    <input name="address" type="text"/><br>

    <label>Gender:</label>
    <input type="radio" id="male" name="gender" value="M">
    <label for="male">Male</label>
    <input type="radio" id="female" name="gender" value="F">
    <label for="female">Female</label><br><br>

    <button type="submit">Create account</button><br>
    <button type="reset">Reset</button>
</form>

</body>
</html>