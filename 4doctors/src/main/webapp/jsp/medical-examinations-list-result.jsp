<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang=\"en\">
    <head>
        <meta charset=\"utf-8\">
        <title>Medical Examinations List</title>
    </head>

    <body>

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


        <!-- display the list of PAST medical examinations

        <c:if test='${not empty pastExaminationsList && !message.error}'>
            <table>

                <caption>PAST EXAMINATIONS</caption>

                <thead>
                    <tr>
                	    <th>Doctor</th><th>Patient</th><th>Date</th><th>Time</th><th>Outcome</th>
                	    </tr>
                </thead>

                <tbody>
                    <c:forEach var="examination" items="${pastExaminationsList}">
                	    <tr>
                		    <li><c:out value="${examination.doctor_cf}"/></li>
                            <li><c:out value="${examination.patient_cf}"/></li>
                            <li><c:out value="${examination.date}"/></li>
                            <li><c:out value="${examination.time}"/></li>
                            <li><c:out value="${examination.outcome}"/></li>
                		</tr>
                	</c:forEach>
                </tbody>
            </table>
        </c:if>

        -->

    </body>
</html>