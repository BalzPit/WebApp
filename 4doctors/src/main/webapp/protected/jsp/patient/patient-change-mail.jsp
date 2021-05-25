<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Change E-mail</title>
  <c:import url="/jsp/head.jsp"/>
</head>

<body>
<c:import url="/jsp/header.jsp"/>
<div id="site">
  <c:import url="/jsp/patient-nav.jsp"/><!--
      --><section>
  <h2>Change E-mail</h2>

  <!-- display the message -->
  <c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
  </c:if>

  <form method="POST" action="<c:url value="/change-email"/>">
    <input type="text" name="current" placeholder="Current e-mail"><br>
    <input type="text" name="new" placeholder="New e-mail"><br>
    <input type="submit" value="Change E-mail">
  </form>
</section>
</div>
<c:import url="/jsp/footer.jsp"/>
</body>
