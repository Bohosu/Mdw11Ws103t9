<%@page import="java.util.Set"%>
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
        <legend>Nový uživatel</legend>
        <label>Jméno <input type="text" name="nickname" value="<c:out value="${param.nickname}"/>" /></label>
        <label>Heslo <input type="password" name="password" value="<c:out value="${param.password}"/>" /></label>
        <label>E-mail <input type="text" name="email" value="<c:out value="${param.email}"/>" /></label>
        <label>Administrátor <input type="checkbox" name="admin" <c:if test="${param.admin != null}">checked </c:if>/></label>
        <div class="submit"><input type="submit" value="Vytvořit"></div>
    </fieldset>
</form>