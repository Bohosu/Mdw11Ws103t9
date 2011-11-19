<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Přihlášení do administrace</h1>
<div id="page">
<div id="menu">
    <ul class="right">
        <li><a href="/">Frontend</a></li>
    </ul>
</div>
<div id="content">
<div id="section">
<c:if test="${error != null}">
    <ul><li>${error}</li></ul>
</c:if>
<form action="/admin" method="post" class="login">

<fieldset>
<legend>Přihlaseni</legend>

<table>
<tr>
	<td><label class="required">Email</label></td>

	<td><input type="text" class="text" name="login_email"></td>
</tr>

<tr>
	<td><label class="required">Heslo</label></td>

	<td><input type="password" class="text" name="login_password" ></td>
</tr>

<tr>
	<td class="right">&nbsp;</td>

	<td><input type="submit" class="button" name="send" id="frmloginForm-send" value="Přihlásit"></td>
</tr>
</table>
</fieldset>

</form>

</div>
</div>
</div>