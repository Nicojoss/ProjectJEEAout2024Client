<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to the SignIn page</title>
</head>
	<title>Sign in page</title>
</head>
<body>
	<a href="/ProjectJEE2023Client/HomeServlet">Home Page</a>
	<form action="SignInServlet" method="POST">
			<table border="1" cellspacing="0" cellpadding="5">
				<tr>
					<td>Firstname : </td>
					<td>
						<input type="text" name="firstname" id="firstname" value="" size="20" title="Minimum 3 characters"/>
						<span class="comment">Minimum 3 characters</span>
					</td>
				</tr>
				<tr>
	    			<td>Lastname: </td>
	    			<td>
	    				<input type="text" name="lastname" id="lastname" value="" size="20" title="Minimum 3 characters"/>
	    				<span class="comment">Minimum 3 characters</span>
	   				</td>
	 			</tr>
	 			<tr>
	    			<td>Username: </td>
	    			<td>
	    				<input type="text" name="username" id="username" value="" size="15" title="Minimum 5 characters"/>
	    				<span class="comment">Minimum 5 characters</span>
	   				</td>
	 			</tr>
	 			<tr>
	    			<td>Password: </td>
	    			<td>
	    				<input type="password" name="password" id="password" value="" size="20" title="Minimum 5 characters"/>
	    				<span class="comment">Password only in capital letters and minimum 5 characters</span>
	   				</td>
	 			</tr>
	 			<tr>
	 			<tr>
	 				<td colspan="2" align="center">
	 					<input type="submit" name="submit" id="submit" value="submit"/>
					</td>
				</tr>
			</table>
		</form>
</body>
</html> 