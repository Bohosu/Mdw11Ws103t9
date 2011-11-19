<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${error != null}">
    <ul>
        <li>${error}</li>
    </ul>
</c:if>
<form action="?<%= request.getQueryString() %>" method="post">

<fieldset>
<legend>Přidat komentář</legend>
<label>Text <textarea cols="40" rows="6" name="text"><c:out value="${param.text}"/></textarea></label>
        <div class="submit"><input type="submit" value="Přidat"></div>
</fieldset>

</form>
