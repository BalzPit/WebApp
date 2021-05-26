<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <meta charset="UTF-8">
        <title>Prescriptions</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/main-style.css"/>">
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/main-style-medium.css"/>">
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/main-style-small.css"/>">
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/prescription-style.css"/>">

    </head>

    <%-- BODY --%>
    <body>
        <c:import url="/protected/jsp/patient/patient-header.jsp"/>

        <c:if test='${not empty cf}'>
            <h1>Prescriptions management page</h1>
        </c:if>

        <%-- List the prescriptions --%>

        <%-- Request --%>
        <div class="sideWrap">
            <div class="side _left">
                <h2>Prescription request</h2>
                    <form method="POST" action="<c:url value="/prescription-request"/>">


                    <label>Doctor email</label>
                    <input name="doctor_email" type="text"/><br>


                    <label>Description:</label>
                    <input name="description" type="text"/><br>


                    <label>Times of validity (max. 10)</label>
                    <input type="number" id="quantity" name="numeroprestazioni" min="1" max="10"/><br>

                    <input type="radio" id="exam" name="type" value="ESAME">
                    <label for="patient">Exam</label>
                    <input type="radio" id="medicine" name="type" value="FARMACO">
                    <label for="doctor">Medicine</label><br>

                    <label>Medicine:</label>
                    <select name="code" id="med_select"></select><br>

                    <%-- <label>code: </label>
                    <input name="code" type="text"><br> --%>

                    <label>Quantity</label>
                    <input type="number" id="qnt" name="qnt"/><br>


                    <button type="submit">Submit</button><br>
                    <button type="reset">Reset</button>

                    </form>
            </div>

            <div class="side _right">
                <h2>List of your prescriptions</h2>
                    <form method="GET" action="<c:url value="/list-user-prescriptions"/>">
                        <div>
                        <input name="typeReq" class="p_list" type="submit" value="all" />
                        <input name="typeReq" class="p_list" type="submit" value="pending" />
                        <input name="typeReq" class="p_list" type="submit" value="rejected" />
                        <input name="typeReq" class="p_list" type="submit" value="approved" />
                        </div>
                    </form>
            </div>
        </div>

    <script type="text/javascript" src="<c:url value="/js/medicine-loader.js"/>"></script>
    </body>

</html>