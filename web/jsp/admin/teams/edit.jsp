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
        <legend>Týmy</legend>
        <label>Název <input type="text" name="name" value="<c:out value="${param.name == null ? item.name : param.name}"/>" /></label>
        <label>Vlastní <input type="checkbox" name="own" <c:if test="${item.own}">checked </c:if>/></label>
        <div class="submit"><input type="submit" value="Upravit"></div>
    </fieldset>
</form>