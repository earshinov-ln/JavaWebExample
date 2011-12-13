<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="utf-8">
<title>Добавить работника</title>

<%@	page import="name.earshinov.WebExample.*" %>
<jsp:useBean id="employee" class="name.earshinov.WebExample.Employee"/>
<jsp:setProperty name="employee" property="*"/>

<p>
	<strong>
<%
	if (request.getMethod().equalsIgnoreCase("POST")) {
		try {
			employee.save();
			out.println("Данные успешно сохранены");
		}
		catch (HandlingException e) {
			out.println(e.getMessage());
		}
	}
%>
	</strong>
</p>

<form method="post">
	<table>
		<tr>
			<td><label for="empno">Уникальный номер:</label></td>
			<td><input type="text" id="empno" name="empno"
				value='<jsp:getProperty name="employee" property="empno"/>'></td>
		</tr>
		<tr>
			<td><label for="ename">ФИО:</label></td>
			<td><input type="text" id="ename" name="ename"
				value='<jsp:getProperty name="employee" property="ename"/>'></td>
		</tr>
		<tr>
			<td><label for="jobTitle">Должность:</label></td>
			<td><input type="text" id="jobTitle" name="jobTitle"
				value='<jsp:getProperty name="employee" property="jobTitle"/>'></td>
		</tr>
	</table>
	<input type="submit" value="Отправить">
</form>