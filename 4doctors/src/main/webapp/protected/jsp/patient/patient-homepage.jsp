<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <title>Homepage</title>
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
                    <h2 class="welcome-title">Welcome, </h2>
                </c:if>

                </section>
        </div>
        <script>var cf="${cf}"</script>
        <script>var contexPath="${pageContext.request.contextPath}"</script>
        <script src="${pageContext.request.contextPath}/js/getPatientName.js"></script>
        <c:import url="/jsp/footer.jsp"/>
    </body>
</html>
