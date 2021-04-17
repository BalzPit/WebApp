<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
    </head>

    <body>
        <c:if test='${not empty sessionScope.patient.cf}'>
            <h1>Welcome, <c:out value="${sessionScope.patient.cf}"/></h1>
        </c:if>

        <form method="GET" action="<c:url value="/logout-user"/>">
            <input type="submit" value="Logout" />
        </form>


        <%-- Profile Overview --%>
        <h2>Profile Overview</h2>
        <p>You are logged as <c:out value="${sessionScope.patient.cf}"/>.</p>
        <c:if test='${requestType=="profile overview"}'>

            <!-- display the message -->
            <c:if test='${not empty message}'>
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:if>

            <!-- display the list of found employees, if any -->
            <c:if test='${not empty personalInfo}'>
                <ul>
                    <li>CF: <c:out value="${personalInfo.getCf()}"/></li>
                    <li>Name: <c:out value="${personalInfo.getName()}"/></li>
                    <li>Surname: <c:out value="${personalInfo.getSurname()}"/></li>
                    <li>Gender: <c:out value="${personalInfo.getGender()}"/></li>
                    <li>Email: <c:out value="${personalInfo.getEmail()}"/></li>
                    <li> <a href="${pageContext.request.contextPath}/protected/jsp/patient/change-password.jsp">Change Password</a> </li>
                    <li>Birthday: <c:out value="${personalInfo.getBirthday()}"/></li>
                    <li>Birthplace: <c:out value="${personalInfo.getBirthplace()}"/></li>
                    <li>Address: <c:out value="${personalInfo.getAddress()}"/></li>
                </ul>
            </c:if>
        </c:if>


        <h2>Reminders</h2>
        <c:if test='${requestType=="reminder"}'>

            <!-- display the message -->
            <c:if test='${not empty message}'>
                <c:import url="/jsp/include/show-message.jsp"/>
            </c:if>

            <!-- display the list of past medical examinations, if any -->
            <c:if test='${not empty pastMedicalExaminationList}'>
                <h3>Reminder of past medical examinations</h3>
                <table>
                    <thead>
                    <tr>
                        <th>Doctor</th><th>date</th><th>Outcome</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="medicalExamination" items="${pastMedicalExaminationList}">
                        <tr>
                            <td><c:out value="${medicalExamination.doctor_cf}"/></td>
                            <td><c:out value="${medicalExamination.date}"/></td>
                            <td><c:out value="${medicalExamination.outcome}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <!-- display the list of future medical examinations, if any -->
            <c:if test='${not empty futureMedicalExaminationList}'>
                <h3>Next appointements</h3>
                <table>
                    <thead>
                    <tr>
                        <th>Doctor</th><th>date</th><th>Outcome</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach var="medicalExamination" items="${futureMedicalExaminationList}">
                        <tr>
                            <td><c:out value="${medicalExamination.doctor_cf}"/></td>
                            <td><c:out value="${medicalExamination.date}"/></td>
                            <td><c:out value="${medicalExamination.outcome}"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

        </c:if>



    </body>
</html>
