<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <title>Homepage</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/css/patient-homepage.css"/>">
        <c:import url="/jsp/head.jsp"/>
    </head>
    <%-- BODY --%>
    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/patient-nav.jsp"/><!--
            --><section>
                <c:if test='${not empty cf}'>
                    <h2 class="welcome-title">Welcome, </h2>
                </c:if>

<%--                <div class="card">--%>
<%--                    <img src="${pageContext.request.contextPath}/media/image-profile.png" alt="Avatar" style="width:100%">--%>
<%--                    <div class="container">--%>
<%--                        <h4><b>John Doe</b></h4>--%>
<%--                        <p>Architect & Engineer</p>--%>
<%--                    </div>--%>
<%--                </div>--%>

                </section>
        </div>
        <script>var cf="${cf}"</script>
        <script>var contexPath="${pageContext.request.contextPath}"</script>
        <script src="${pageContext.request.contextPath}/js/getPatientName.js"></script>
        <c:import url="/jsp/footer.jsp"/>
    </body>
</html>
