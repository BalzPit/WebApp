<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

	<head>
		<title>Medical Examination</title>
        <c:import url="/jsp/head.jsp"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/patient-medical-examination.css"/>">
	</head>

    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/patient-nav.jsp"/><!--
            --><section>
                <section id="reservation">
                    <div id="form-box">
                        <h1>New Reservation</h1>
                        <form action="javascript:void(0);">
                            <label for="patientDoctor">Select Doctor:</label>
                            <select name = "patientDoctor" id = "patientDoctor">
                                <c:forEach var = "patientDoctor" items="${patientDoctors}" >
                                    <option value ="${patientDoctor.cf}">${patientDoctor.surname} ${patientDoctor.name}</option>
                                </c:forEach>
                            </select><br/>

                            <label>Select Date:</label>
                            <input name="dateselect" type="date" id="dateselect"/><br/>

                            <label for="timeToSelect">Select Time:</label>
                            <select name = "timeToSelect" id = "timeToSelect">
                                <c:forEach var = "timeToSelect" items="${timeSelection}" >
                                    <option ng-disabled="${timeToSelect.booked}" value ="${timeToSelect.hour}:${timeToSelect.min}">${timeToSelect.hour}:${timeToSelect.min}</option>
                                </c:forEach>
                            </select><br/>

                            <button type="reset">Cancel</button>
                            <button onclick="addMedEx()" style="float: right">Confirm</button><br/>

                        </form>
                    </div>

                    <!--<h1>Medical Examinations List</h1>

                    <hr/>

                    <c:import url="/jsp/include/show-message.jsp"/>
                    -->

                    <!-- display the list of FUTURE medical examinations -->



                        <table id="future-exams">
                            <caption>FUTURE EXAMINATIONS</caption>
                            <thead>
                            <tr>
                                <th>Date</th><th>Time</th><th>Doctor</th><th></th>
                            </tr>
                            </thead>

                            <tbody>
                                <c:choose>
                                    <c:when test='${not empty futureExaminationsList && !message.error}'>
                                        <c:forEach var="examination" items="${futureExaminationsList}">
                                            <tr>
                                                <td><c:out value="${examination.date}"/></td>
                                                <td><c:out value="${examination.time}"/></td>
                                                <td><c:out value="${examination.doctor_cf}"/></td>
                                                <td><button id="deletemedex" onclick="deleteMedEx(this)">Cancel</button></td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <tr id="notbooked"><td>No examinations booked.</td><td></td><td></td></tr>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                        </table>

                </section>

                <hr/>

                <!-- display the list of PAST medical examinations -->
                <div>
                    <c:if test='${not empty pastExaminationsList && !message.error}'>
                        <table>
                            <caption>PAST EXAMINATIONS</caption>
                            <thead>
                            <tr>
                                <th>Doctor</th><th>Date</th><th>Time</th><th>Outcome</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="examination" items="${pastExaminationsList}">
                                <tr>
                                    <td><c:out value="${examination.doctor_cf}"/></td>
                                    <td><c:out value="${examination.date}"/></td>
                                    <td><c:out value="${examination.time}"/></td>
                                    <!--<td><c:out value="${examination.outcome}"/></td>-->
                                    <td><button id="outcome" value="${examination.outcome}" onclick="openPopup(this)">Details</button></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>

                <!-- The Modal -->
                <div id="popupModal" class="modal">
                    <div class="modal-content">
                      <div class="modal-header">
                        <h2>Examination Outcome</h2>
                        <span class="close" onclick="closePopup()">&times;</span>
                      </div>
                      <div class="modal-body">
                        <p id="outcomeText"></p>
                      </div>
                    </div>
                </div>
            </section>
        </div>

        <c:import url="/jsp/footer.jsp"/>

        <script type="text/javascript" src="<c:url value="/js/patient-medical-examinations.js"/>">
        </script>
    </body>
</html>