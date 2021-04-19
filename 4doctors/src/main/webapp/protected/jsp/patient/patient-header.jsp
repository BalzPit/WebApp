<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header style="display: inline-flex">
    <form action="" method="GET" style="margin: 5px">
        <input type="submit" value="Home" style="width: 110px" />
    </form>
    <form action="" method="GET" style="margin: 5px">
        <input type="submit" value="Prescriptions" style="width: 110px" />
    </form>
    <form action="" method="GET" style="margin: 5px">
        <input type="submit" value="Examinations" style="width: 110px" />
    </form>
    <form action="" method="GET" style="margin: 5px">
        <input type="submit" value="Profile" style="width: 110px" />
    </form>
    <form action="<c:url value="/logout-user"/>" method="GET" style="margin: 5px">
        <input type="submit" value="Log out" style="width: 110px" />
    </form>
</header>
