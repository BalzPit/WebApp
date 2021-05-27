<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <title>Prescriptions</title>
        <c:import url="/jsp/head.jsp"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/patient-section.css"/>">
    </head>
    <%-- BODY --%>
    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/patient-nav.jsp"/><!--
            --><section>
                <c:if test='${not empty cf}'>
                <h1>Prescriptions management page of <c:out value="${cf}"/></h1>
                </c:if>

                <%-- List the prescriptions --%>
                <h2>List of your prescriptions</h2>
                <form method="GET" action="<c:url value="/list-user-prescriptions"/>">
                    <input name="typeReq" type="submit" value="all" />
                    <input name="typeReq" type="submit" value="pending" />
                    <input name="typeReq" type="submit" value="rejected" />
                    <input name="typeReq" type="submit" value="approved" />
                </form>

                <%-- Request --%>
                <h2>Prescription request</h2>
                <form method="POST" action="<c:url value="/prescription-request"/>">
                    <div>
                        <label>Doctor email</label>
                        <input name="doctor_email" type="text"/><br>

                        <label>Description:</label>
                        <input name="description" type="text"/><br>
                        <label>Times of validity (max. 10)</label>
                        <input type="number" id="quantity" name="numeroprestazioni" min="1" max="10"/>


                        <input type="radio" id="exam" name="type" value="ESAME">
                        <label for="patient">Exam</label>
                        <input type="radio" id="medicine" name="type" value="FARMACO">
                        <label for="doctor">Medicine</label><br><br>

                        <%-- <select name="medicine" id="medicine">
                            <c:forEach var="med" items="${medicineList}">
                                <option name="code" value="<c:out value="${med.getCode()}"/>"><c:out value="${med.getName()}"/></option>
                            </c:forEach>
                        </select> --%>
                        <label>code: </label>
                        <input name="code" type="text"><br>

                        <label>Quantity</label>
                        <input type="number" id="qnt" name="qnt"/>
                    </div>

                    <button type="submit">Submit</button><br>
                    <button type="reset">Reset</button>

                </form>

                <%-- Available medicines in the database --%>
                <h2>Medicine List</h2>
                <form method="GET" action="<c:url value="/list-medicines"/>">
                    <input name="type" type="submit" value="list medicines" />
                </form>
        </section>
        </div>
        <c:import url="/jsp/footer.jsp"/>
    </body>

</html>