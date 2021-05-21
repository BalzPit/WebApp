<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home</title>
        <c:import url="/jsp/head.jsp"/>
    </head>

    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/patient-nav.jsp"/><!--
                --><section>
                    <div class="personal-info">
                        <link type="text/css" rel="stylesheet" href="<c:url value="/css/profile-style.css"/>">
                        <h2>Profile</h2>
                        <br>
<%--                        <p>You are logged as <c:out value="${cf}"/>.</p>--%>

                        <!-- display the message -->
<%--                        <c:if test='${not empty message}'>--%>
<%--                            <c:import url="/jsp/include/show-message.jsp"/>--%>
<%--                        </c:if>--%>

                        <!-- display the personal information of the logged patient, if any -->
                        <c:if test='${not empty personalInfo}'>
                            <h3>CF</h3>
                            <p><c:out value="${personalInfo.getCf()}"/></p>
                            <br>
                            <h3>Name</h3>
                            <p><c:out value="${personalInfo.getName()}"/></p>
                            <br>
                            <h3>Surname</h3>
                            <p><c:out value="${personalInfo.getSurname()}"/></p>
                            <br>
                            <h3>gender</h3>
                            <p><c:out value="${personalInfo.getGender()}"/></p>
                            <br>
                            <h3>Email</h3>
                            <p><c:out value="${personalInfo.getEmail()}"/></p>
                            <br>
                            <h3>Password</h3>
                            <p><a class="button" href="${pageContext.request.contextPath}/protected/jsp/patient/patient-change-password.jsp">Change</a></p>
                            <br>
                            <h3>Birthday</h3>
                            <p><c:out value="${personalInfo.getBirthday()}"/></p>
                            <br>
                            <h3>Birthplace</h3>
                            <p><c:out value="${personalInfo.getBirthplace()}"/></p>
                            <br>
                            <h3>Address</h3>
                            <p><c:out value="${personalInfo.getAddress()}"/></p>
                        </c:if>
                    </div>
                    </section>
        </div>
        <c:import url="/jsp/footer.jsp"/>
    </body>
</html>
