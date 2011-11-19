<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="data.News"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="actions.jsp" %>
<table>
    <thead>
        <tr>
            <th>Uživatel</th>
            <th>Email</th>
            <th>Admin</th>
            <th>Akce</th>
        </tr>
    </thead>
    <tbody>
        <c:if test="${empty(data)}">
        <tr><td colspan="4">Žádná data!</td></tr>
        </c:if>
        <c:forEach var="n" items="${data}">
<tr>
    <td>${n.nickname}</td><td>${n.email}</td><td>${n.admin}</td><td><a href="?<%= request.getQueryString() %>&action=edit&id=${n.id}">Upravit</a><c:if test="${!n.admin}"> | <a href="?<%= request.getQueryString() %>&action=delete&id=${n.id}">Smazat</a></c:if></td>
</tr>
        </c:forEach>
    </tbody>
</table>
