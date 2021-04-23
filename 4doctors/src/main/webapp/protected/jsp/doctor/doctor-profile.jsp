<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Home</title>
</head>

<body>
<c:import url="/protected/jsp/doctor/doctor-header.jsp"/>

<h3> test cf <c:out value="${cf}"/></h3>
<h3> test role<c:out value="${role}"/></h3>

<%-- Profile Overview --%>
  <h2>Profile</h2>
  <p>You are logged as <c:out value="${cf}"/>.</p>

  <!-- display the message -->
  <c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
  </c:if>

  <!-- display the personal information of the logged doctor, if any -->
  <c:if test='${not empty personalInfo}'>
    <ul>
      <li>CF: <c:out value="${personalInfo.getCf()}"/></li>
      <li>Name: <c:out value="${personalInfo.getName()}"/></li>
      <li>Surname: <c:out value="${personalInfo.getSurname()}"/></li>
      <li>Gender: <c:out value="${personalInfo.getGender()}"/></li>
      <li>Email: <c:out value="${personalInfo.getEmail()}"/></li>
      <li> <a href="${pageContext.request.contextPath}/protected/jsp/doctor/doctor-change-password.jsp">Change Password</a> </li>
      <li>Birthday: <c:out value="${personalInfo.getBirthday()}"/></li>
      <li>Birthplace: <c:out value="${personalInfo.getBirthplace()}"/></li>
      <li>Address: <c:out value="${personalInfo.getAddress()}"/></li>
    </ul>
  </c:if>


</body>
</html>
