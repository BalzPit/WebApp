<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header style="display: inline-flex">

    <form action="" method="GET" style="margin: 5px">
        <input type="hidden" value="Home" style="width: 110px" />
        <a href="${pageContext.request.contextPath}/protected/jsp/patient/patient-homepage.jsp">Home</a>
    </form>
    <form action="<c:url value="/prescriptions"/>" method="GET" style="margin: 5px">
        <input name="type" type="submit" value="prescriptions" style="width: 110px" />
    </form>
    <form action="<c:url value="/patient-medical-examinations"/>" method="GET" style="margin: 5px">
        <input name="type" type="submit" value="Examinations" />
    </form>
    <form action="<c:url value="/patient-homepage"/>" method="GET" style="margin: 5px">
        <input name="type" type="submit" value="Profile" />
    </form>
    <form action="<c:url value="/user/logout"/>" method="GET" style="margin: 5px">
        <input type="submit" value="Log out" style="width: 110px" />
    </form>

</header>
