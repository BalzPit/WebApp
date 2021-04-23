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

        <h2>Profile</h2>
        <p>You are logged as <c:out value="${cf}"/>.</p>

        <!-- display the message -->
        <c:if test='${not empty message}'>
            <c:import url="/jsp/include/show-message.jsp"/>
        </c:if>

        <!-- display the personal information of the logged patient, if any -->
        <c:if test='${not empty personalInfo}'>
            <ul>
                <li>CF: <c:out value="${personalInfo.getCf()}"/></li>
                <li>Name: <c:out value="${personalInfo.getName()}"/></li>
                <li>Surname: <c:out value="${personalInfo.getSurname()}"/></li>
                <li>Gender: <c:out value="${personalInfo.getGender()}"/></li>
                <li>Email: <c:out value="${personalInfo.getEmail()}"/></li>
                <li> <a href="${pageContext.request.contextPath}/protected/jsp/patient/patient-change-password.jsp">Change Password</a> </li>
                <li>Birthday: <c:out value="${personalInfo.getBirthday()}"/></li>
                <li>Birthplace: <c:out value="${personalInfo.getBirthplace()}"/></li>
                <li>Address: <c:out value="${personalInfo.getAddress()}"/></li>
            </ul>
        </c:if>

    </body>
</html>
