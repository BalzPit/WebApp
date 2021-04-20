<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
    </head>

    <body>

        <c:import url="/protected/jsp/patient/patient-header.jsp"/>

        <h2>List of medicines actually available</h2>

            <!-- display the message -->
            <c:if test='${not empty message}'>
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:if>

            <!-- display the list of past medical examinations, if any -->
            <c:if test='${not empty medicineList}'>
                <h3>Medicines</h3>
                <table>
                    <thead>
                    <tr>
                        <td>Code</td><td>Name</td><td>Class</td><td>Producer</td><td>Description</td>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="medicine" items="${medicineList}">
                        <tr>
                            <td><c:out value="${medicine.getCode()}"/></td>
                            <td><c:out value="${medicine.getName()}"/></td>
                            <td><c:out value="${medicine.getMedicine_class()}"/></td>
                            <td><c:out value="${medicine.getProducer()}"/></td>
                            <td><c:out value="${medicine.getDescription()}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

    </body>
</html>
