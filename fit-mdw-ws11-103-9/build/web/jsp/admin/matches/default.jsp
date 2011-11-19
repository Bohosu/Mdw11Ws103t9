<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="data.News"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
<c:when test="${competitions != null}">
    <form method="post" action="?<%= request.getQueryString() %>">
        <fieldset>
            <legend>Výběr soutěžě</legend>
            <label>Soutěž
        <select name="cmp">
        <c:forEach var="c" items="${competitions}">
            <option value="${c.id}">${c.name} ${c.season.id}</option>
        </c:forEach>
        </select>
            </label>
            <div class="submit"><input type="submit" value="Vybrat"></div>
        </fieldset>
    </form>
</c:when>
<c:otherwise>
<%@include file="actions.jsp" %>
<table>
    <thead>
        <tr>
            <th>Domácí</th>
            <th>Hosté</th>
            <th>Výsledek</th>
            <th>Kolo</th>
            <th>Akce</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${empty(data)}">
        <tr><td colspan="5">Žádná data!</td></tr>
        </c:if>
        <c:forEach var="n" items="${data}">
<tr>
    <td>${teams[n.home_team]}</td><td>${teams[n.away_team]}</td><td>
        <c:choose>
            <c:when test="${n.played}">${n.goals_home}:${n.goals_away} (${n.goals_ht_home}:${n.goals_ht_away})</c:when>
            <c:otherwise>Nehráno</c:otherwise>
        </c:choose></td>
    <td>${n.round}.</td>
    <td>
        <c:if test="${n.played && (teams[n.home_team].own || teams[n.away_team].own)}">
            <c:if test="${!empty(n.players)}">
                <c:if test="${(teams[n.home_team].own && n.goals_home > 0) || (teams[n.away_team].own && n.goals_away > 0)}">
            <a href="?<%= request.getQueryString() %>&action=goals&id=${n.id}">Góly</a> |
                </c:if>
            <a href="?<%= request.getQueryString() %>&action=cards&id=${n.id}">Karty</a> |
            </c:if>
            <a href="?<%= request.getQueryString() %>&action=squad&id=${n.id}">Soupiska</a> |
        </c:if>
        <a href="?<%= request.getQueryString() %>&action=edit&id=${n.id}">Upravit</a> | <a href="?<%= request.getQueryString() %>&action=delete&id=${n.id}">Smazat</a></td>
</tr>
        </c:forEach>
    </tbody>
</table>

</c:otherwise>
</c:choose>