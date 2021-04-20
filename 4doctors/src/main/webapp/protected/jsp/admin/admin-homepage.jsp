<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Homepage</title>
</head>
<body>
  <c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
  </c:if>

  <c:if test='${not empty sessionScope.username}'>
    <h1>Welcome, admin <c:out value="${sessionScope.username}"/></h1>
  </c:if>

  <form method="GET" action="<c:url value="/user/logout"/>">
    <input type="submit" value="Logout" />
  </form>
</body>
</html>
