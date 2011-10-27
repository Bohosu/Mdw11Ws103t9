<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="actions.jsp" %>
<c:if test="${errors != null}">
    <ul>
    <c:forEach items="${errors}" var="error">
        <li>${error}</li>
    </c:forEach>
    </ul>
</c:if>
<form action="?<%= request.getQueryString() %>" method="post">
    <fieldset>
        <legend>Týmy v soutěži</legend>
        <c:forEach var="t" items="${teams}">
            <label>${t.name} <input type="checkbox" name="team[${t.id}]" <c:if test="${key_teams[t.id]}">checked </c:if>/></label>
        </c:forEach>
        <div class="submit"><input type="submit" value="Vytvořit"></div>
    </fieldset>
</form>