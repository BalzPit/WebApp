<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header style="display: inline-flex">
    <form action="" method="GET" style="margin: 5px">
        <input type="hidden" value="Home" style="width: 110px" />
        <a href="${pageContext.request.contextPath}/protected/jsp/doctor/doctor-homepage.jsp">Home</a>
    </form>
    <form action="<c:url value="/list-my-patients"/>" method="GET" style="margin: 5px">
        <input type="submit" value="Patients" style="width: 110px" />
    </form>
    <form action="<c:url value="/prescription-manager"/>" method="GET" style="margin: 5px">
        <input type="submit" value="Prescriptions" style="width: 110px" />
    </form>
    <form action="" method="GET" style="margin: 5px">
        <input type="submit" value="Profile" style="width: 110px" />
    </form>
    <form action="<c:url value="/logout-user"/>" method="GET" style="margin: 5px">
        <input type="submit" value="Log out" style="width: 110px" />
    </form>
</header>
