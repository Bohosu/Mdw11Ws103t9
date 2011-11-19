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
        <legend>Hráč</legend>
        <label>Jméno <input type="text" name="name" value="<c:out value="${param.name != null ? param.name : item.name}"/>" /></label>
        <label>Příjmení <input type="text" name="surname" value="<c:out value="${param.surname != null ? param.surname : item.surname}"/>" /></label>
        <label>Číslo <input type="text" name="number" value="<c:out value="${param.number != null ? param.number : item.number}"/>" /></label>
        <label>Výška v cm <input type="text" name="height" value="<c:out value="${param.height != null ? param.height : item.height}"/>" /></label>
        <label>Váha v kg <input type="text" name="weight" value="<c:out value="${param.weight != null ? param.weight : item.weight}"/>" /></label>
        <label>Pozice <select name="post"><c:forEach var="post" items="${posts}"><option value="${post.id}"<c:if test="${post.id == (param.post == null ? item.post.id : param.post)}"> selected</c:if>>${post.name}</option></c:forEach></select></label>
        <div class="submit"><input type="submit" value="Upravit"></div>
    </fieldset>
</form>