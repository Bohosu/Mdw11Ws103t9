<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>${teams[match.home_team]} - ${teams[match.away_team]}</h2>
<table class="match">
    <tbody>
        <tr class="teams">
            <td class="right" width="47%">${teams[match.home_team]}</td>
            <td class="center">-</td>
            <td class="left" width="47%">${teams[match.away_team]}</td>
        </tr>
        <tr class="skore">
            <td class="right">
                ${match.goals_home}
            </td>
            <td class="center">:</td>
            <td class="left">
                ${match.goals_away}
            </td>
        </tr>
        <tr>
            <td class="right">(${match.goals_ht_home}</td>
            <td class="center">:</td>
            <td class="left">${match.goals_ht_away})</td>
        </tr>
    </tbody></table>


<table class="match">
    <thead><tr><th colspan="3">Týmová statistika</th></tr></thead>
    <tbody>
        <tr>
            <td>
                <h3>Góly</h3>
            </td>
            <td>
                <h3>Karty</h3>
            </td>
            <td>
                <h3>Soupiska</h3>
            </td>
        </tr>
        <tr>
            <td>
                <ul class="no-bullets">
                    <c:forEach var="g" items="${goals}">
                        <li><c:if test="${g.minute != 0}">${g.minute}' </c:if>
                            <c:choose><c:when test="${g.scorer != null}"><a href="?section=players&id=${players[g.scorer].id}">${players[g.scorer].name} ${players[g.scorer].surname}</a></c:when><c:otherwise>Vlastní gól</c:otherwise></c:choose>
                            <c:if test="${g.asistent != null}">(<a href="?section=players&id=${players[g.asistent].id}">${players[g.asistent].name} ${players[g.asistent].surname}</a>)</c:if>
                        </li>
                    </c:forEach>
                </ul>
            </td>
            <td>
                <ul class="no-bullets">
                    <c:forEach var="g" items="${cards}">
                        <li>
                            <a href="?section=players&id=${players[g.player].id}">${players[g.player].name} ${players[g.player].surname}</a> [${g.type eq 'RED' ? "Červená" : "Žlutá"}]
                        </li>
                    </c:forEach>
                </ul>
            </td>
            <td rowspan="2">
                <ul>
                    <c:forEach var="p" items="${squad}">
                    <li><a href="?section=players&id=${p.id}">${p.name} ${p.surname}</a></li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h3>Informace</h3>
                <p>
                    <strong>Datum:</strong> <fmt:formatDate pattern="dd. MM. yyyy HH:mm" value="${match.date}" /><br />
                    <strong>Soutěž:</strong> <a href="?section=competitions&competition=${cmp.id}">${cmp.name} ${season}</a><br />
                    <strong>Fáze:</strong> ${match.round}. kolo
                </p>
            </td>
        </tr>
    </tbody>
</table>