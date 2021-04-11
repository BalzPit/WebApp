<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify your account</title>
</head>
<body>
    <h1>Verify your account</h1>

    <c:if test='${not empty message}'>
        <c:import url="/jsp/include/show-message.jsp"/>
    </c:if>

    <p>We already sent a verification code to your email.</p>

    <form method="POST" action="<c:url value="/verify"/>">
        <label>Verification Code</label>
        <input type="text" name="code"/>
        <button type="submit">Verify</button>
    </form>
</body>
</html>
