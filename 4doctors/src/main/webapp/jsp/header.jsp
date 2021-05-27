<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- display error/message -->
<c:if test='${not empty message}'>
    <c:import url="/jsp/include/error-message.jsp"/>
</c:if>

<header>
    <div id="toggle-menu-button" class="mobile-only">
        <span></span>
        <span></span>
        <span></span>
    </div>
    <img src="<c:url value="/media/logo.png"/>" alt="Logo 4Doctors" title="Logo 4Doctors">
</header>