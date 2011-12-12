<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="utf-8">
<title>Калькулятор</title>

<form action="store" method="post">
	<table>
		<tr>
			<td><label for="1">Первое слагаемое:</label></td>
			<td><input type="text" id="1" name="1" size="4"></td>
		</tr>
		<tr>
			<td><label for="2">Второе слагаемое:</label></td>
			<td><input type="text" id="2" name="2" size="4"></td>
		</tr>
	</table>
	<input type="submit" value="Отправить">
</form>