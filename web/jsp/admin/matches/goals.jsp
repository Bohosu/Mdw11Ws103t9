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
        <legend>Góly</legend>
        <c:forEach begin="1" end="${goals}" var="i">
            <div class="label"><strong>Gól #${i}</strong> |
                <label class="non-label">Střelec <select name="scorer[${i}]">
                    <option value="-1">vlastní</option>
                    <c:forEach var="s" items="${players}">
                        <option value="${s.id}"<c:if test="${g[i - 1].scorer.id == s.id}"> selected</c:if>>${s.name} ${s.surname}</option>
                    </c:forEach>
                </select></label>
                <label class="non-label">Asistent <select name="asistent[${i}]">
                    <option value="-1">bez asistence</option>
                    <c:forEach var="s" items="${players}">
                        <option value="${s.id}"<c:if test="${g[i - 1].asistent.id == s.id}"> selected</c:if>>${s.name} ${s.surname}</option>
                    </c:forEach>
                </select></label>
                <label class="non-label">Minuta <input type="text" size="2" name="minute[${i}]" value="${g[i - 1].minute}" /></label>
            </div>
        </c:forEach>
        <div class="submit"><input type="submit" value="Vytvořit"></div>
    </fieldset>
</form>