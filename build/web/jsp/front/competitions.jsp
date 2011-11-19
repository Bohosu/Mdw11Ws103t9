<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<h2>${title}</h2>
<div id="actions">
<c:if test="${seasons != null}">
<ul>
    <c:forEach var="s" items="${seasons}">
        <li><a href="?section=competitions&season=${s.id}"<c:if test="${season == s.id}"> class="active"</c:if>>${s}</a></li>
    </c:forEach>
</ul>
<div class="clear"></div>
<c:if test="${competitions != null}">
<ul>
    <c:forEach var="c" items="${competitions}">
        <li><a href="?section=competitions&competition=${c.id}"<c:if test="${param.competition == c.id}"> class="active"</c:if>>${c.name}</a></li>
    </c:forEach>
</ul>
<div class="clear"></div>

<c:if test="${competition != null}">
<ul>
<c:if test="${!competition.friendly}">
    <li><a href="?section=competitions&competition=${competition.id}"<c:if test="${param.type == null}"> class="active"</c:if>>Tabulka</a></li>
</c:if>
    <li><a href="?section=competitions&competition=${competition.id}&type=schedule"<c:if test="${(param.type == null && competition.friendly) || param.type eq \"schedule\"}"> class="active"</c:if>>Rozpis</a></li>
    <li><a href="?section=competitions&competition=${competition.id}&type=results"<c:if test="${param.type eq \"results\"}"> class="active"</c:if>>Výsledky</a></li>
    <li><a href="?section=competitions&competition=${competition.id}&type=stats"<c:if test="${param.type eq \"stats\"}"> class="active"</c:if>>Statistika</a></li>
</ul>
<div class="clear"></div>
</div>

<c:choose>
    <c:when test="${results != null}">
        <c:forEach var="r" items="${rounds}">
            <table>
                <thead>
                    <tr><th colspan="3"><c:choose><c:when test="${!competition.friendly}">${r}. kolo</c:when><c:otherwise>Přátelské zápasy</c:otherwise></c:choose></th></tr>
                </thead>
                <tbody>
                    <c:forEach var="m" items="${results[r]}">
                        <tr>
                            <td width="30%"><fmt:formatDate pattern="dd. MM. yyyy HH:mm" value="${m.date}" /></td>
                            <td width="40%">${teams[m.home_team]} - ${teams[m.away_team]}</td>
                            <td><c:choose><c:when test="${teams[m.home_team].own || teams[m.away_team].own}"><a href="?section=match&id=${m.id}">${m.goals_home}:${m.goals_away} (${m.goals_ht_home}:${m.goals_ht_away})</a></c:when><c:otherwise>${m.goals_home}:${m.goals_away} (${m.goals_ht_home}:${m.goals_ht_away})</c:otherwise></c:choose></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:forEach>
    </c:when>
    <c:when test="${schedule != null}">
        <c:forEach var="r" items="${rounds}">
            <table>
                <thead>
                    <tr><th colspan="2"><c:choose><c:when test="${!competition.friendly}">${r}. kolo</c:when><c:otherwise>Přátelské zápasy</c:otherwise></c:choose></th></tr>
                </thead>
                <tbody>
                    <c:forEach var="m" items="${schedule[r]}">
                        <tr>
                            <td width="30%"><fmt:formatDate pattern="dd. MM. yyyy HH:mm" value="${m.date}" /></td>
                            <td>${teams[m.home_team]} - ${teams[m.away_team]}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:forEach>
    </c:when>
    <c:when test="${table != null}">
        <table>
            <thead>
                <tr>
                    <th>#</th><th>Název</th><th>Z</th><th>V</th><th>R</th><th>P</th><th>Skóre</th><th>B</th>
                </tr>
            </thead>
            <tbody>
        <c:forEach var="t" items="${table}" varStatus="status">
            <tr><td>${status.count}.</td><td>${teams[t.team]}</td><td>${t.m}</td><td>${t.w}</td><td>${t.d}</td><td>${t.l}</td><td>${t.gs}:${t.go}</td><td><strong>${t.p}</strong></td></tr>
        </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Žádná data.</p>
    </c:otherwise>
</c:choose>
</c:if>
</c:if>
</c:if>