<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Administrace</h1>
<div id="page">
<%@include file="menu.jsp" %>
<div id="content">
<div id="section">
<%
String sectionParamter = request.getParameter("section");
String section = "welcome.jsp";
if(sectionParamter != null) {
    section = sectionParamter + "/default.jsp";
    String actionParamter = request.getParameter("action");
    if(actionParamter != null) {
        section = sectionParamter + "/" + actionParamter + ".jsp";
    }
}
%>
<jsp:include page="<%= section %>" />
</div>
</div>
</div>