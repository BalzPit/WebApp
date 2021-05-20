<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

    <%-- HEAD --%>
    <head>
        <title>Homepage</title>
        <c:import url="/jsp/head.jsp"/>
    </head>
    <%-- BODY --%>
    <body>
        <c:import url="/jsp/header.jsp"/>
        <div id="site">
            <c:import url="/jsp/patient-nav.jsp"/><!--
            --><section>
                <c:if test='${not empty cf}'>
                    <h2>Welcome, <c:out value="${cf}"/></h2>
                </c:if>
                <!-- display the message -->
                <c:if test='${not empty message}'>
                    <c:import url="/jsp/include/show-message.jsp"/>
                </c:if>
                </section>
        </div>
        <c:import url="/jsp/footer.jsp"/>
    </body>
</html>
