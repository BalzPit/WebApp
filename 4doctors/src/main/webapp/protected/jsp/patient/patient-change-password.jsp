<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Change Password</title>
    <c:import url="/jsp/head.jsp"/>
  </head>

  <body>
    <c:import url="/jsp/header.jsp"/>
    <div id="site">
      <c:import url="/jsp/patient-nav.jsp"/><!--
      --><section>
          <h1>Change Password</h1>

          <!-- display the message -->
          <c:if test='${not empty message}'>
            <c:import url="/jsp/include/show-message.jsp"/>
          </c:if>

          <form method="POST" action="<c:url value="/change-password"/>">
              <input type="password" name="current" placeholder="Current password"><br>
              <input type="password" name="new" placeholder="New password"><br>
              <input type="password" name="confirm" placeholder="Confirm password"><br>
              <input type="submit" value="Change Password">
          </form>
          </section>
    </div>
    <c:import url="/jsp/footer.jsp"/>
  </body>


