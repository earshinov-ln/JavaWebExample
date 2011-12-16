<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="name.earshinov.WebExample.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<meta charset="utf-8">
<title>Калькулятор</title>

<p>
	<strong>
<%
	// месиво из разметки и кода...
	if (request.getMethod().equalsIgnoreCase("POST")) {
		// вычисление результата
		try {
			int first = Utils.getIntegerArgument("1", request.getParameter("1"));
			int second = Utils.getIntegerArgument("2", request.getParameter("2"));
			out.println("Результат: " + (first + second));
		}
		catch (HandlingException e) {
			out.println("Ошибка: " + e.getMessage());
		}
	}
%>
	</strong>
</p>

<form method="post">
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