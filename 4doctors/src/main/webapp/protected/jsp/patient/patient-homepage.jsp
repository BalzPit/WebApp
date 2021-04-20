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

        <%-- Available medicines in the database --%>
        <h2>Medicine List</h2>
        <form method="GET" action="<c:url value="/list-medicines"/>">
            <input name="type" type="submit" value="list medicines" />
        </form>

        <%-- Go to the prescriptions page --%>
        <h2>Prescriptions</h2>
        <form method="GET" action="<c:url value="/prescriptions"/>">
            <input name="type" type="submit" value="prescriptions" />
        </form>

    </body>

</html>
