<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>Create Medical Examination</title>
	</head>

    <body>

        <form method="GET" action="<c:url value="/patient-homepage"/>">
            <button type="submit" value="profile overview">Home</button><br/>
        </form>

	    <h1>New Medical Examination Form</h1>

	    <form method="POST" action="<c:url value="/patient-medical-examinations-page"/>">
		    <label for="date">Date:</label>
            <input name="date" type="date"/><br/>

            <label for="time">Time:</label>
            <input name="time" type="time"/><br/>

            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
	    </form>

        <h1>Medical Examinations List</h1>

        <hr/>

        <c:import url="/jsp/include/show-message.jsp"/>

        <!-- display the list of FUTURE medical examinations -->

        <c:if test='${not empty futureExaminationsList && !message.error}'>
            <table><caption>FUTURE EXAMINATIONS</caption>
                <thead>
                    <tr>
                		<th>Doctor</th><th>Patient</th><th>Date</th><th>Time</th><th>Outcome</th>
                    </tr>
                </thead>

                <tbody>
                	<c:forEach var="examination" items="${futureExaminationsList}">
                		<tr>
                			<td><c:out value="${examination.doctor_cf}"/></td>
                            <td><c:out value="${examination.patient_cf}"/></td>
                            <td><c:out value="${examination.date}"/></td>
                            <td><c:out value="${examination.time}"/></td>
                            <td><c:out value="${examination.outcome}"/></td>
                		</tr>
                	</c:forEach>
                </tbody>
            </table>
        </c:if>


        <!-- display the list of PAST medical examinations -->

        <c:if test='${not empty pastExaminationsList && !message.error}'>
            <table><caption>PAST EXAMINATIONS</caption>
                <thead>
                    <tr>
                        <th>Doctor</th><th>Patient</th><th>Date</th><th>Time</th><th>Outcome</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach var="examination" items="${pastExaminationsList}">
                        <tr>
                            <td><c:out value="${examination.doctor_cf}"/></td>
                            <td><c:out value="${examination.patient_cf}"/></td>
                            <td><c:out value="${examination.date}"/></td>
                            <td><c:out value="${examination.time}"/></td>
                            <td><c:out value="${examination.outcome}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </body>
</html>