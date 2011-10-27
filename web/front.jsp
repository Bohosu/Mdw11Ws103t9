<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <title>Horní Dolní</title>
    </head>
    <body>
        <div id="window">
            <h1>Horní dolní</h1>
<div id="page">
<%@include file="jsp/front/menu.jsp" %>
<div id="content">
<div id="section">
<%
String file="jsp/front/" + request.getAttribute("section") + ".jsp";
%>
        <jsp:include page="<%= file %>" />
        </div>
        </div>
        </div>
        </div>
    </body>
</html>