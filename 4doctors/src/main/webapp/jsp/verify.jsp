<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify your account</title>
    <c:import url="/jsp/head.jsp"/>
    <style>
        .error {
            color: #d00;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Verify your account</h1>

    <c:if test='${not empty message}'>
        <c:import url="/jsp/include/show-message.jsp"/>
    </c:if>

    <c:choose>
        <c:when test="${not empty sessionScope.verification_code and not empty sessionScope.patient}">
            <script>
                sessionStorage.setItem("verification_code", "${sessionScope.verification_code}");
            </script>

            <p>We already sent a verification code to your email.</p>

            <form id="verification_form" method="POST" action="<c:url value="/user/verify"/>">
                <input id="code" type="text" name="code" placeholder="Verification Code" required/>
                <div class="error"></div>
                <input name="submit" type="submit" value="Verify">
            </form>
            <script src="${pageContext.request.contextPath}/js/verify.js"></script>
        </c:when>
        <c:otherwise>
            <p>An error occurred while processing the request. You will redirected to the registration page in 5 seconds.</p>
            <script>
                setTimeout(function(){
                    window.location.href = "${pageContext.request.contextPath}/jsp/patient_registration.jsp";
                }, 5000);
            </script>
        </c:otherwise>
    </c:choose>
</body>
</html>
