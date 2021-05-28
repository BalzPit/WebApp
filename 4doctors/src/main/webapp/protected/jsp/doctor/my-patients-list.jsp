<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <c:import url="/jsp/head.jsp"/>
</head>


<body>
<c:import url="/jsp/header.jsp"/>
<div id="site">
<c:import url="/jsp/doctor-nav.jsp"/><!--
    --><section>
<h2>List of Your Followed Patients</h2>


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
    </section><!--
--></div>
</body>
</html>