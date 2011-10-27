<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${detail == null}">
    <h2>Hráči</h2>
    <table>
        <thead>
            <tr><th>Jméno</th><th>Číslo</th><th>Pozice</th></tr>
        </thead>
        <tbody>
<c:if test="${empty(data)}">
    <tr><td colspan="3">Žádní hráči</td></tr>
</c:if>
<c:forEach var="p" items="${data}">
    <tr><td><a href="?section=players&id=${p.id}">${p.name} ${p.surname}</a></td><td>${p.number}</td><td>${posts[p.post].name}</td></tr>
</c:forEach>
    </tbody>
    </table>
    </c:when>
<c:otherwise>
    <h2>${detail.name} ${detail.surname}</h2>
    <p><strong>Číslo dresu</strong>: ${detail.number}</p>
    <p><strong>Výška</strong>: ${detail.height == 0 ? "?" : detail.height} cm</p>
    <p><strong>Váha</strong>: ${detail.weight == 0 ? "?" : detail.weight} kg</p>
    <p><strong>Pozice</strong>: ${posts[detail.post].name}</p>
</c:otherwise>
</c:choose>