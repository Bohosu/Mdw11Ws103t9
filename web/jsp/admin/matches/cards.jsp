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
<form action="?<%= request.getQueryString()%>" method="post">
    <fieldset>
        <legend>Karty</legend>
        <c:choose>
            <c:when test="${num != null}">
                <c:forEach begin="1" end="${num}" var="i">
                    <div class="label"><label class="non-label">Karta #${i} <select name="card[${i}]">
                            <c:forEach var="s" items="${players}">
                                <option value="${s.id}"<c:if test="${cards[i - 1].player.id == s.id}"> selected</c:if>>${s.name} ${s.surname}</option>
                            </c:forEach>
                            </select>
                            <select name="type[${i}]">
                                <option value="y">Žlutá</option>
                                <option value="r"<c:if test="${cards[i - 1].type eq \"RED\"}"> selected</c:if>>Červená</option>
                            </select></label></div>
                </c:forEach>
                                <input type="hidden" name="num_preserve" value="${num}" />
            </c:when>
            <c:otherwise>
                <label>Počet karet <input name="num" type="text" size="2" value="${num_def}" /></label>
                </c:otherwise>
            </c:choose>
        <div class="submit"><input type="submit" value="Nastavit"></div>
    </fieldset>
</form>