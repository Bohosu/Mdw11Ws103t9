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
        <legend>Úprava uživatele</legend>
        <label>Jméno <input type="text" name="nickname" value="<c:out value="${param.nickname == null ? item.nickname : param.nickname}"/>" /></label>
        <label>E-mail <input type="text" name="email" value="<c:out value="${param.email == null ? item.email : param.email}"/>" /></label>
        <c:if test="${!item.admin}"><label>Administrátor <input type="checkbox" name="admin" <c:if test="${param.admin != null}">checked </c:if>/></label></c:if>
        <div class="submit"><input type="submit" value="Upravit"></div>
    </fieldset>
</form>