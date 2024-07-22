<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage account page</title>
</head>
<body>
	<a href="/ProjectJEEAout2024Client/HomeServlet">Home Page</a>
	<%@ include file="LogoutPartialView.jsp" %>
        <form action="ManageAccountServlet" method="POST">
		<table border="1" cellspacing="0" cellpadding="5">
			<tr>
				<td>Username :</td>
				<td><input type="text" name="username" id="username" value="" size="20" /></td>
			</tr>
			<tr>
				<td>Enter your new password :</td>
				<td><input type="password" name="password" id="password" value="" size="20" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" name="submit" id="submit" value="submit" /></td>
			</tr>
		</table>
	</form>
</body>
</html>