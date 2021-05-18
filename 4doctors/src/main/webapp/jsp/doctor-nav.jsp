<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav>
    <a href="${pageContext.request.contextPath}/protected/jsp/doctor/doctor-homepage.jsp">Home</a>
    <a href="${pageContext.request.contextPath}/list-my-patients">Patients</a>
    <a href="${pageContext.request.contextPath}/prescription-manager">Prescriptions</a>
    <a href="${pageContext.request.contextPath}/profile?type=Profile">Profile</a>
    <a href="${pageContext.request.contextPath}/user/logout">Log out</a>
</nav>