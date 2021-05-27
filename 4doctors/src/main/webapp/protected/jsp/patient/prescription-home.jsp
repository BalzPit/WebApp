<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <title>Prescriptions</title>
        <c:import url="/jsp/head.jsp"/>
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/prescription-patient-style.css"/>">
    </head>
    <%-- BODY --%>
    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/patient-nav.jsp"/><!--
            --><section>
                <c:if test='${not empty cf}'>
                <h1>Prescriptions management page</h1>
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
                        <input type="number" id="quantity" name="numeroprestazioni" min="1" max="10"/><br>

                        <input type="radio" id="exam" name="type" value="ESAME">
                        <label for="exam">Exam</label>
                        <input type="radio" id="medicine" name="type" value="FARMACO">
                        <label for="medicine">Medicine</label><br><br>
                        <div id="medicine_select">
                            <label>Choose a medicine</label>
                            <select name="codeM" id="med_select"></select>
                        </div>
                        <div id="exam_select">
                             <label>Choose an exam</label>
                             <select name="codeE" id="ex_select"></select>
                        </div>
                        <br>
                        <div id="quant">
                            <label>Quantity</label>
                            <input type="number" id="qnt" name="qnt"/>
                        </div><br>
                    </div>

                    <button type="submit">Submit</button><br>
                    <button type="reset">Reset</button>

                </form>

                <%-- Available medicines prescripted to the user --%>
                <div>
                <h2>Prescribed medicines</h2>
                    <button id="pres_med" value=<c:out value="${cf}"/> >List</button>
                    <div id="medicine_wrapper">
                         <table id="medicine_table">
                            <tr>
                                <th scope="col">Code</th>
                                <th scope="col">Name</th>
                                <th scope="col">Description</th>
                                <th scope="col">Medicine class</th>
                                <th scope="col">Producer</th>
                            </tr>
                         </table>
                    </div>
                </div>
        </section>
        </div>
        <c:import url="/jsp/footer.jsp"/>
        <script type="text/javascript" src="<c:url value="/js/prescription-home.js"/>"></script>
    </body>

</html>