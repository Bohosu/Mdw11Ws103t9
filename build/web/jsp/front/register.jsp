<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${!empty(errors)}">
    <ul>
    <c:forEach items="${errors}" var="error">
        <li>${error}</li>
    </c:forEach>
    </ul>
</c:if>
<form action="?section=register" method="post">

<fieldset>
<legend>Registrace</legend>
        <label>Jméno <input type="text" name="reg_nickname" value="<c:out value="${param.nickname}"/>" /></label>
        <label>Heslo <input type="password" name="reg_password" value="<c:out value="${param.password}"/>" /></label>
        <label>E-mail <input type="text" name="reg_email" value="<c:out value="${param.email}"/>" /></label>
        <div class="submit"><input type="submit" value="Registrovat"></div>
</fieldset>

</form>
