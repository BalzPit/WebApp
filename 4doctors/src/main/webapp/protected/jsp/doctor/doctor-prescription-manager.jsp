<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Prescription Manager</title>
		<c:import url="/jsp/head.jsp"/>
		<link type="text/css" rel="stylesheet" href="<c:url value="/css/prescription-manager-style.css"/>">
	</head>

    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/doctor-nav.jsp"/><!--
            --><section>
                <h1>Prescriptions</h1>
                <br>
                <div class="row">

                    <!-- display the list of found prescriptions if any -->
                    <div class="col-lg-7">
                        <h3 class="prescription-header">Pending prescriptions</h3>
                        <div class="prescription-box">
                            <c:if test='${not empty pending}'>
                            <c:forEach var="prescription" items="${pending}">
                                <div class="prescription-item" data-id="<c:out value="${prescription.id}"/>">
                                    <div>
                                        <span>Date: <c:out value="${prescription.date}"/></span><br>
                                        <span>Patient: <c:out value="${prescription.patient}"/></span>
                                        <p>Description: <c:out value="${prescription.description}"/></p>
                                    </div>
                                    <div>
                                        <form method="POST" action="<c:url value="prescription-update"/>">
                                            <input type="submit" value="Approve">
                                            <input type="hidden" name="id" value="<c:out value="${prescription.id}"/>">
                                            <input type="hidden" name="status" value="APPROVED">
                                        </form>
                                        <form method="POST" action="<c:url value="prescription-update"/>">
                                            <input type="submit" value="Reject">
                                            <input type="hidden" name="id" value="<c:out value="${prescription.id}"/>">
                                            <input type="hidden" name="status" value="REJECTED">
                                        </form>
                                    </div>
                                </div>
                            </c:forEach>
                            </c:if>
                            <c:if test='${empty pending}'>
                                <div>
                                    <p>There are not pending prescription requests</p>
                                </div>
                            </c:if>
                        </div>
                    </div>
                    <div class="col-lg-5">
                        <h3 class="prescription-header">Past prescriptions</h3>
                        <div class="prescription-box">
                        <c:if test='${not empty past}'>
                            <c:forEach var="prescription" items="${past}">
                                <div class="prescription-item <c:out value="${prescription.status}"/>">
                                    <div>
                                        <span>Date: <c:out value="${prescription.date}"/></span><br>
                                        <span>Patient: <c:out value="${prescription.patient}"/></span>
                                        <p>Description: <c:out value="${prescription.description}"/></p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        </div>
                    </div>
                </div>
            </section><!--
        --></div>
        <c:import url="/jsp/footer.jsp"/>
	</body>
</html>