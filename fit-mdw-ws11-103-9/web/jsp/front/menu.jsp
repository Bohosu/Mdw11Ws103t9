<%@page import="model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu">
    <ul>
        <li><a href="?section=news">Novinky</a></li>
        <li><a href="?section=competitions">Soutěže</a></li>
        <li><a href="?section=players">Hráči</a></li>
    </ul>

    <ul class="right">
                <li><a href="/admin">Administrace</a></li>
        <c:choose>
            <c:when test="${login}">
                <li><a href="?logout=1">Odhlásit [<%= UserModel.u.getNickname() %>]</a></li>
            </c:when>
            <c:otherwise>
        <li><a href="?section=login">Přihlásit</a></li>
        <li><a href="?section=register">Registrovat</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>