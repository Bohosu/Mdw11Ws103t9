<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:choose>
    <c:when test="${detail == null}">
    <h2>Novinky</h2>
<c:if test="${empty(data)}">
    <p>Žádné novinky</p>
</c:if>
<c:forEach var="news" items="${data}">
    <p><fmt:formatDate pattern="dd. MM. yyyy HH:mm" value="${news.date}" /> | <a href="?section=news&id=${news.id}">${news.name}</a></p>
</c:forEach>
    </c:when>
<c:otherwise>
    <h2>${detail.name}</h2>
    <p><em><fmt:formatDate pattern="dd. MM. yyyy HH:mm" value="${detail.date}" /></em></p>
    <p>${detail.text}</p>
    <h3>Komentáře</h3>
    <c:if test="${empty(comments)}">
        <p>Žádné komentáře</p>
    </c:if>
    <c:forEach var="c" items="${comments}">
        <div class="comment">
            <p>${users[c.user].nickname} | <fmt:formatDate pattern="dd. MM. yyyy HH:mm" value="${c.date}" /></p>
            <p>${c.text}</p>
        </div>
    </c:forEach>
    <c:if test="${login}">
        <%@include file="addcomment.jsp" %>
    </c:if>
</c:otherwise>
</c:choose>