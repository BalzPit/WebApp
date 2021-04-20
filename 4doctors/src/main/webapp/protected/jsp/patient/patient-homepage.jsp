<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <meta charset="UTF-8">
        <title>Homepage</title>
    </head>

    <%-- BODY --%>
    <body>

        <c:import url="/protected/jsp/patient/patient-header.jsp"/>

        <c:if test='${not empty cf}'>
            <h1>Welcome, <c:out value="${cf}"/></h1>
        </c:if>

        <!-- display the message -->
        <c:if test='${not empty message}'>
            <c:import url="/jsp/include/show-message.jsp"/>
        </c:if>

    </body>

</html>
