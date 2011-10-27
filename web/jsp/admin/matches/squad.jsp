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
        <legend>Soupiska zápasu</legend>
        <c:forEach var="p" items="${players}">
            <label>${p.name} ${p.surname}<input type="checkbox" name="player[${p.id}]" <c:if test="${key_players[p.id]}">checked </c:if>/></label>
        </c:forEach>
        <div class="submit"><input type="submit" value="Nastavit"></div>
    </fieldset>
</form>