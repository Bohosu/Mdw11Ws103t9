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
        <legend>Novinka</legend>
        <label>Titulek <input type="text" name="name" value="<c:out value="${param.name}"/>" /></label>
        <label>Text <textarea cols="20" rows="8" name="text"><c:out value="${param.text}"/></textarea></label>
        <div class="submit"><input type="submit" value="Vytvořit"></div>
    </fieldset>
</form>