<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${error != null}">
    <ul><li>${error}</li></ul>
</c:if>
<form action="/" method="post" class="login">

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
