<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <title>Administrace</title>
    </head>
    <body>
        <div id="window">
<%
String file="jsp/admin/" + request.getAttribute("view");
%>
        <jsp:include page="<%= file %>" />
        </div>
    </body>
</html>