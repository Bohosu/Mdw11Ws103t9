<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="data.News"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="actions.jsp" %>
<table>
    <thead>
        <tr>
            <th>Název</th>
            <th>Ročník</th>
            <th>Akce</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${empty(data)}">
        <tr><td colspan="2">Žádná data!</td></tr>
        </c:if>
        <c:forEach var="n" items="${data}">
<tr>
    <td>${n.name}</td><td>${seasons[n.season]}</td><td><a href="?<%= request.getQueryString() %>&action=teams&id=${n.id}">Týmy</a> | <a href="?<%= request.getQueryString() %>&action=edit&id=${n.id}">Upravit</a> | <a href="?<%= request.getQueryString() %>&action=delete&id=${n.id}">Smazat</a></td>
</tr>
        </c:forEach>
    </tbody>
</table>