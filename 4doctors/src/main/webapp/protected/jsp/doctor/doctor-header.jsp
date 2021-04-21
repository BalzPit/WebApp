<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header style="display: inline-flex">

    <a href="${pageContext.request.contextPath}/protected/jsp/doctor/doctor-homepage.jsp" style="margin: 5px">Home</a>
    <a href="${pageContext.request.contextPath}/protected/jsp/doctor/doctor-homepage.jsp" style="margin: 5px">Patients</a>
    <a href="${pageContext.request.contextPath}/prescription-manager" style="margin: 5px">Prescriptions</a>
    <a href="${pageContext.request.contextPath}/profile?type=Profile" style="margin: 5px">Profile</a>
    <a href="${pageContext.request.contextPath}/user/logout" style="margin: 5px">Log out</a>

</header>
