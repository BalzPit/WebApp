<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>401 Unauthorized</title>
    </head>
    <body>
    <h1 style="color:red;">You are not authorized to see the requested page.</h1>
    <c:choose>
        <c:when test='${sessionScope.role.equals("patient")}'>
            <a href="${pageContext.request.contextPath}/protected/jsp/patient/patient-homepage.jsp">go back to the homepage</a>
        </c:when>
        <c:when test='${sessionScope.role.equals("doctor")}'>
            <a href="${pageContext.request.contextPath}/protected/jsp/doctor/doctor-homepage.jsp">go back to the homepage</a>
        </c:when>
        <c:otherwise>
            <a href="${pageContext.request.contextPath}/jsp/login.jsp">go back to the login</a>
        </c:otherwise>
    </c:choose>
    </body>
</html>
