<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<meta charset="utf-8">
<title>Калькулятор</title>

<%-- Сообщения, проставленные в сервлете --%>
<c:if test="${!empty error}">
	<p><c:out value="${error}"/></p>
</c:if>
<c:if test="${!empty message}">
	<p><c:out value="${message}"/></p>
</c:if>

<%-- Формочка --%>
<form action="store" method="post">
	<table>
		<tr>
			<td><label for="1">Первое слагаемое:</label></td>
			<td><input type="text" id="1" name="1" size="4" value='${fn:escapeXml(param["1"])}'></td>
		</tr>
		<tr>
			<td><label for="2">Второе слагаемое:</label></td>
			<td><input type="text" id="2" name="2" size="4" value='${fn:escapeXml(param["2"])}'></td>
		</tr>
	</table>
	<input type="submit" value="Отправить">
</form>