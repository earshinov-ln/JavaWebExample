<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="utf-8">
<title>Калькулятор</title>


<%@	page import="name.earshinov.WebExample.*" %>

<p>
	<strong>
<%
	// месиво из разметки и кода...
	String firstParameter = "";
	String secondParameter = "";

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

		// складирование firstParameter и secondParameter для вывода в инпутах
		firstParameter = request.getParameter("1");
		if (firstParameter == null)
			firstParameter = "";
		secondParameter = request.getParameter("2");
		if (secondParameter == null)
			secondParameter = "";
	}
%>
	</strong>
</p>

<form method="post">
	<table>
		<tr>
			<td><label for="1">Первое слагаемое:</label></td>
			<td><input type="text" id="1" name="1" size="4" value="<%= firstParameter %>"></td>
		</tr>
		<tr>
			<td><label for="2">Второе слагаемое:</label></td>
			<td><input type="text" id="2" name="2" size="4" value="<%= secondParameter %>"></td>
		</tr>
	</table>
	<input type="submit" value="Отправить">
</form>