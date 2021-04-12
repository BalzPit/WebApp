
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Prescription Manager</title>
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
            .prescription-item.approved {
                background-color: #9bff98;
            }
            .prescription-item.rejected {
                background-color: #ff9898;
            }
		</style>
	</head>

    <body>
	    <h2>Prescriptions</h2>
	    <!-- display the message -->
        <c:import url="/jsp/include/show-message.jsp"/>

	    <!-- display the list of found employees, if any -->
        <div class="prescription-header">Pending prescriptions</div>
        <div class="prescription-box">
            <c:if test='${not empty pending}'>
            <c:forEach var="prescription" items="${pending}">
                <div class="prescription-item" data-id="<c:out value="${prescription.id}"/>">
                    <div>
                        <span><c:out value="${prescription.date}"/></span><br>
                        <span><c:out value="${prescription.patient}"/></span>
                        <p><c:out value="${prescription.description}"/></p>
                    </div>
                    <div>
                        <form method="GET" action="prescription-update">
                            <input type="submit" value="Approve">
                            <input type="submit" value="Reject">
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
        <c:if test='${not empty approved}'>
            <c:forEach var="prescription" items="${approved}">
                <div class="prescription-item approved">
                    <div>
                        <span><c:out value="${prescription.date}"/></span><br>
                        <span><c:out value="${prescription.patient}"/></span>
                        <p><c:out value="${prescription.description}"/></p>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test='${not empty rejected}'>
            <c:forEach var="prescription" items="${rejected}">
                <div class="prescription-item rejected">
                    <div>
                        <span><c:out value="${prescription.date}"/></span><br>
                        <span><c:out value="${prescription.patient}"/></span>
                        <p><c:out value="${prescription.description}"/></p>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        </div>
	</body>
</html>