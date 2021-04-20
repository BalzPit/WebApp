<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <meta charset="UTF-8">
        <title>Prescriptions List</title>
    </head>

    <%-- BODY --%>
    <body>

        <c:if test='${not empty cf}'>
            <h1>List of all prescriptions of <c:out value="${cf}"/></h1>
        </c:if>

        <%-- Log out button --%>
        <form method="GET" action="<c:url value="/logout-user"/>">
            <input type="submit" value="Logout" />
        </form>

        <!-- display the message -->
        <c:if test='${not empty message}'>
            <c:import url="/jsp/include/show-message.jsp"/>
        </c:if>

        <!-- display the list of all prescriptions, if any -->
        <c:if test='${not empty prescriptionsList}'>
        <h3>Prescriptions</h3>
        <table>
            <thead>
                <tr>
                    <td>Id</td><td>Doctor</td><td>Patient</td><td>Date</td><td>Description</td><td>Duration</td><td>Type</td><td>Status</td>
                </tr>
            </thead>

            <tbody>
                    <c:forEach var="prescription" items="${prescriptionsList}">
                        <!-- print all the rows -->
                        <tr>
                            <td><c:out value="${prescription.getId()}"/></td>
                            <td><c:out value="${prescription.getDoctor()}"/></td>
                            <td><c:out value="${prescription.getPatient()}"/></td>
                            <td><c:out value="${prescription.getDate()}"/></td>
                            <td><c:out value="${prescription.getDescription()}"/></td>
                            <td><c:out value="${prescription.getDuration()}"/></td>
                            <td><c:out value="${prescription.getType()}"/></td>
                            <td><c:out value="${prescription.getStatus()}"/></td>
                        </tr>
                    </c:forEach>
            </tbody>
        </table>
        </c:if>

    </body>
</html>