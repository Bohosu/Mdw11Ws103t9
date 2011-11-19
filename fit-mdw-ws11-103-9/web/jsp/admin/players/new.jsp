<%@page import="java.util.Set"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="actions.jsp" %>
<c:if test="${!empty(errors)}">
    <ul>
    <c:forEach items="${errors}" var="error">
        <li>${error}</li>
    </c:forEach>
    </ul>
</c:if>
<form action="?<%= request.getQueryString() %>" method="post">
    <fieldset>
        <legend>Hráč</legend>
        <label>Jméno <input type="text" name="name" value="<c:out value="${param.name}"/>" /></label>
        <label>Příjmení <input type="text" name="surname" value="<c:out value="${param.surname}"/>" /></label>
        <label>Číslo <input type="text" name="number" value="<c:out value="${param.number}"/>" /></label>
        <label>Výška v cm <input type="text" name="height" value="<c:out value="${param.height}"/>" /></label>
        <label>Váha v kg <input type="text" name="weight" value="<c:out value="${param.weight}"/>" /></label>
        <label>Pozice <select name="post"><c:forEach var="post" items="${posts}"><option value="${post.id}">${post.name}</option></c:forEach></select></label>
        <div class="submit"><input type="submit" value="Vytvořit"></div>
    </fieldset>
</form>