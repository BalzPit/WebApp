<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
    </head>

    <body>
        <c:import url="/protected/jsp/doctor/doctor-header.jsp"/>
        <c:if test='${not empty cf}'>
            <h1>Welcome, doctor <c:out value="${cf}"/></h1>
        </c:if>
    </body>
</html>
