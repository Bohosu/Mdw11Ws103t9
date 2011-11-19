<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="data.News"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="actions.jsp" %>
<table>
    <thead>
        <tr>
            <th>Jméno</th>
            <th>Příjmení</th>
            <th>Číslo</th>
            <th>Výška</th>
            <th>Váha</th>
            <th>Pozice</th>
            <th>Akce</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${empty(data)}">
        <tr><td colspan="2">Žádná data!</td></tr>
        </c:if>
        <c:forEach var="n" items="${data}">
<tr>
    <td>${n.name}</td><td>${n.surname}</td><td>${n.number}</td><td>${n.height == 0 ? "?" : n.height} cm</td><td>${n.weight == 0 ? "?" : n.weight} kg</td><td>${posts[n.post].name}</td><td><a href="?<%= request.getQueryString() %>&action=edit&id=${n.id}">Upravit</a> | <a href="?<%= request.getQueryString() %>&action=delete&id=${n.id}">Smazat</a></td>
</tr>
        </c:forEach>
    </tbody>
</table>
