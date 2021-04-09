<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>Create Medical Examination</title>
	</head>

    <body>
	    <h1>New Medical Examination Form</h1>

	    <form method="POST" action="<c:url value="/create-medical-examination"/>">
		    <label for="date">Date:</label>
            <input name="date" type="date"/><br/>

            <label for="time">Time:</label>
            <input name="time" type="time"/><br/>

            <button type="submit">Submit</button><br/>
            <button type="reset">Reset the form</button>
	    </form>

	    <form method="POST" action="<c:url value="/medical-examinations-list"/>">
            <button type="submit">Show Examinations List</button><br/>
        </form>
    </body>
</html>