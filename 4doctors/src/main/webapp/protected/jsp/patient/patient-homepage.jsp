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

        <c:if test='${not empty cf}'>
            <h1>Welcome, <c:out value="${cf}"/></h1>
        </c:if>

        <%-- Log out button --%>
        <form method="GET" action="<c:url value="/logout-user"/>">
            <input type="submit" value="Logout" />
        </form>

        <%-- Profile Overview --%>
        <h2>Profile Overview</h2>
        <p>You are logged as <c:out value="${cf}"/>.</p>
        <form method="GET" action="<c:url value="/patient-homepage"/>">
            <input name="type" type="submit" value="profile overview" />
        </form>


        <%-- Reminders --%>
        <h2>Reminders</h2>
        <form method="GET" action="<c:url value="/patient-homepage"/>">
            <input name="type" type="submit" value="reminder" />
        </form>

        <form method="GET" action="<c:url value="/patient-medical-examinations-page"/>">
            <input name="type" type="submit" value="Medical Examinations" />
        </form>

    </body>

</html>
