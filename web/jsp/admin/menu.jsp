<%@page import="model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="menu">
    <ul>
        <li><a href="?section=users">Uživatelé</a></li>
        <li><a href="?section=news">Novinky</a></li>
        <li><a href="?section=players">Hráči</a></li>
        <li><a href="?section=posts">Pozice</a></li>
        <li><a href="?section=teams">Týmy</a></li>
        <li><a href="?section=seasons">Sezóny</a></li>
        <li><a href="?section=competitions">Soutěže</a></li>
        <li><a href="?section=matches">Zápasy</a></li>
    </ul>

    <ul class="right">
        <li><a href="/">Frontend</a></li>
        <li><a href="?logout=1">Odhlásit [<%= UserModel.u.getNickname() %>]</a></li>
    </ul>
</div>