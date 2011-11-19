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
        <legend>Upravit zápas</legend>
        <label>Domácí <select name="home_team"><c:forEach var="team" items="${teams}"><option value="${team.id}"<c:if test="${team.id == (param.home_team == null ? item.home_team.id : param.home_team)}"> selected</c:if>>${team.name}</option></c:forEach></select></label>
        <label>Hosté <select name="away_team"><c:forEach var="team" items="${teams}"><option value="${team.id}"<c:if test="${team.id == (param.away_team == null ? item.away_team.id : param.away_team)}"> selected</c:if>>${team.name}</option></c:forEach></select></label>
        <label>Soutěž <select name="competition"><c:forEach var="cmp" items="${cmps}"><option value="${cmp.id}"<c:if test="${cmp.id == param.competition}"> selected</c:if>>${cmp.name} ${seasons[cmp.season]}</option></c:forEach></select></label>
        <label>Skóre I:I (I:I) <input type="text" name="score" value="<c:out value="${param.score == null ? (item.played ? itemScore : null) : param.score}"/>" /></label>
        <label>Kolo <input type="text" name="round" value="<c:out value="${param.round == null ? item.round : param.round}"/>" /></label>
        <label>Datum a čas DD.MM.RRRR HH:MM <input type="text" name="date" value="<c:out value="${param.date == null ? itemDate : param.date}"/>" /></label>
        <div class="submit"><input type="submit" value="Upravit"></div>
    </fieldset>
</form>