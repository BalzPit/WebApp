<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang=\"en\">
    <head>
        <meta charset=\"utf-8\">
        <title>New Medical Examination</title>
    </head>

    <body>

    <h1>Medical Examination</h1>

        <hr/>

        <c:import url="/jsp/include/show-message.jsp"/>

        <!-- display the just created examination, if any and no errors -->

        <c:if test='${ !message.error }'>
            <ul>
                <li>Doctor: <c:out value="${examination.doctor_cf}"/></li>
                <li>Patient: <c:out value="${examination.patient_cf}"/></li>
                <li>Date: <c:out value="${examination.date}"/></li>
                <li>Time: <c:out value="${examination.time}"/></li>
                <li>Examination Outcome: <c:out value="${examination.outcome}"/></li>
            </ul>
        </c:if>
    </body>
</html>