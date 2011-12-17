<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="name.earshinov.WebExample.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="employee" class="name.earshinov.WebExample.Employee"/>
<jsp:setProperty name="employee" property="*"/>

<!DOCTYPE html>
<meta charset="utf-8">
<title>Добавить работника</title>

<p>
	<strong>
<%
	if (request.getMethod().equalsIgnoreCase("POST")) {
		try {
			employee.save();
			out.println("Данные успешно сохранены");
		}
		catch (HandlingException e) {
			// TODO: на всякий случай нужен HTML encode!
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
			<%-- TODO: экранировать кавычки при выводе в value="..." --%>
			<td><input type="text" id="empno" name="empno" value="${fn:escapeXml(param.empno)}"></td>
		</tr>
		<tr>
			<td><label for="ename">ФИО:</label></td>
			<td><input type="text" id="ename" name="ename" value="${fn:escapeXml(param.ename)}"></td>
		</tr>
		<tr>
			<td><label for="jobTitle">Должность:</label></td>
			<td><input type="text" id="jobTitle" name="jobTitle" value="${fn:escapeXml(param.jobTitle)}"></td>
		</tr>
	</table>
	<input type="submit" value="Отправить">
</form>