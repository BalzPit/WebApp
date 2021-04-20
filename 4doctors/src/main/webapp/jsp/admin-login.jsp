<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Login</title>
</head>
<body>
<h1>Sign In</h1>
<c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
</c:if>

<form method="POST" action="<c:url value="/admin/login"/>">
    <label>Username:</label>
    <input name="username" type="text"/><br>

    <label>Password:</label>
    <input name="password" type="password"/><br>

    <button type="submit">Log in</button><br>
    <button type="reset">Reset</button>
</form>

</body>
</html>
