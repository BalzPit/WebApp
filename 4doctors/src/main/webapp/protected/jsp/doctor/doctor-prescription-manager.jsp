<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Prescription Manager</title>
		<c:import url="/jsp/head.jsp"/>
		<style>
		    * {
		        font-family: Arial, sans-serif;
		    }
		    .prescription-header {
		        border: 1px solid #000;
                padding: 5px;
                background-color: #ffa132;
                font-size: 20pt;
                font-weight: bold;
                text-transform: uppercase;
                text-align: center;
		    }
		    .prescription-box {
		        max-height: 250px;
		        margin-bottom: 10px;
		        padding: 10px;
		        border: 1px solid #000;
		        background-color: #ffdbb3;
                overflow-y: scroll;
		    }
		    .prescription-item {
		        height: 100px;
		        margin-bottom: 10px;
		        padding: 10px;
		        border: 1px solid #000;
		        background-color: #fff;
            }
            .prescription-item > div {
                display: inline-block;
                vertical-align: middle;
            }
            .prescription-item > div:nth-child(1) {
                width: calc(100% - 170px);
                height: 100%;
            }
            .prescription-item > div:nth-child(2) {
                width: 150px;
                height: 100%;
                text-align: center;
            }
            .prescription-item.APPROVED {
                background-color: #9bff98;
            }
            .prescription-item.REJECTED {
                background-color: #ff9898;
            }
		</style>
	</head>

    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/doctor-nav.jsp"/><!--
            --><section>
                <h2>Prescriptions</h2>
                <!-- display the message -->
                <c:import url="/jsp/include/show-message.jsp"/>

                <!-- display the list of found prescriptions if any -->
                <div class="prescription-header">Pending prescriptions</div>
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

                <div class="prescription-header">Past prescriptions</div>
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
            </section><!--
        --></div>
        <c:import url="/jsp/footer.jsp"/>
	</body>
</html>