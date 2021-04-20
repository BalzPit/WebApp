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

<h2>List of Your Followed Patients</h2>

<!-- display the message -->
<c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
</c:if>

<!-- display the list of past medical examinations, if any -->
<c:if test='${not empty patientsList}'>
    <h3>Your Patients</h3>
    <table>
        <thead>
        <tr>
            <td>CF</td><td>Name</td><td>Surname</td><td>email</td><td>Birthday</td>
        </tr>
        </thead>

        <tbody>
        <c:forEach var="patient" items="${patientsList}">
            <tr>
                <td><c:out value="${patient.getCf()}"/></td>
                <td><c:out value="${patient.getName()}"/></td>
                <td><c:out value="${patient.getSurname()}"/></td>
                <td><c:out value="${patient.getEmail()}"/></td>
                <td><c:out value="${patient.getBirthday()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>
<br>
<br>
<br>
<form method="POST" action="<c:url value="/add-my-patient"/>">
    <label>Patient's CF:</label>
    <input name="patient_cf" type="text"/><br>
    <button type="submit">Add</button><br>
</form>

</body>
</html>