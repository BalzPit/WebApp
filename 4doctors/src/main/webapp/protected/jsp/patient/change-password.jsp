<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Change Password</title>
  </head>

  <body>
  <h1>Change Password</h1>

  <!-- display the message -->
  <c:if test='${not empty message}'>
    <c:import url="/jsp/include/show-message.jsp"/>
  </c:if>

  <form method="POST" action="<c:url value="/change-password"/>">
    <table>
      <tr><td>Current Password</td><td><input type="password" name="current" ></td></tr>
      <tr><td>New Password</td><td><input type="password" name="new"></td></tr>
      <tr><td>Confirm Password</td><td><input type="password" name="confirm"></td></tr>
      <tr><td><input type="submit" value="Change Password"></td></tr>
    </table>
  </form>
  </body>


