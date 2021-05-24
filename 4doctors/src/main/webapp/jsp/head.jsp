<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="author" content="Balzan Pietro, Bettin Manuel, Bullo Marcello, Levorato Nicola, Piva Giovanni, Scicchitano Wiliam">

<!-- FAVICON -->
<link rel="apple-touch-icon" sizes="57x57" href="<c:url value="/media/favicon/apple-icon-57x57.png"/>">
<link rel="apple-touch-icon" sizes="60x60" href="<c:url value="/media/favicon/apple-icon-60x60.png"/>">
<link rel="apple-touch-icon" sizes="72x72" href="<c:url value="/media/favicon/apple-icon-72x72.png"/>">
<link rel="apple-touch-icon" sizes="76x76" href="<c:url value="/media/favicon/apple-icon-76x76.png"/>">
<link rel="apple-touch-icon" sizes="114x114" href="<c:url value="/media/favicon/apple-icon-114x114.png"/>">
<link rel="apple-touch-icon" sizes="120x120" href="<c:url value="/media/favicon/apple-icon-120x120.png"/>">
<link rel="apple-touch-icon" sizes="144x144" href="<c:url value="/media/favicon/apple-icon-144x144.png"/>">
<link rel="apple-touch-icon" sizes="152x152" href="<c:url value="/media/favicon/apple-icon-152x152.png"/>">
<link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/media/favicon/apple-icon-180x180.png"/>">
<link rel="icon" type="image/png" sizes="192x192"  href="<c:url value="/media/favicon/android-icon-192x192.png"/>">
<link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/media/favicon/favicon-32x32.png"/>">
<link rel="icon" type="image/png" sizes="96x96" href="<c:url value="/media/favicon/favicon-96x96.png"/>">
<link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/media/favicon/favicon-16x16.png"/>">
<link rel="manifest" href="<c:url value="/media/favicon/manifest.json"/>">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage" content="<c:url value="/media/favicon/ms-icon-144x144.png"/>">
<meta name="theme-color" content="#ffffff">

<!-- CSS -->
<link type="text/css" rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="<c:url value="/css/main-style.css"/>">
<link type="text/css" rel="stylesheet" href="<c:url value="/css/main-style-medium.css"/>" media="screen and (min-width: 769px) and (max-width: 1023px">
<link type="text/css" rel="stylesheet" href="<c:url value="/css/main-style-small.css"/>" media="screen and (max-width: 768px)">

<!-- JS -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="<c:url value="/js/menu-mobile.js"/>"></script>
<script>
    // triggers at the very beginning when the document is loaded
    $(function() {

        setSiteHeight();

    });

    // triggers each time the window is resized
    $(window).resize(setSiteHeight);

    // set the #site height to avoid overflow (and consequent scrollbars) out of the body
    function setSiteHeight() {
        $("#site").css("height", $(window).height() - $("header").outerHeight() - $("footer").outerHeight());
    }
</script>