<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome to our website</title>
</head>
<body>
	<h1>welcome to our cooking recipe site</h1>
	<%if (request.getAttribute("success") != null) {%>
        <div class="alert alert-success">
       <p><%= request.getAttribute("success")%></p> 
       </div>
    <% } %>
    	<%
        if (request.getAttribute("fail") != null) {%>
        <div class="alert alert-danger">
       <p><%= request.getAttribute("fail")%></p> 
       </div>
    <% } %>
	<% 
        if (session.getAttribute("person") == null) { 
    %>
        <button onclick="window.location='/ProjectJEEAout2024Client/LogInServlet'">Log In</button>
        <button onclick="window.location='/ProjectJEEAout2024Client/SignInServlet'">Sign In</button>
    <% } else { %>
    	<%@ include file="LogoutPartialView.jsp" %>
        <button onclick="window.location='/ProjectJEEAout2024Client/ManageAccountServlet'">Manage my account</button>
        <button onclick="window.location='/ProjectJEEAout2024Client/CreateRecipeServlet'">Create a new recipe</button>
        <button onclick="window.location='/ProjectJEEAout2024Client/ConsultOwnRecipeServlet'">Consult your recipe</button>
    <% } %>
    <h1>Rechercher une recette :</h1> <br>
    <form action ="SearchRecipeServlet" method="POST">
    <input type="text" id="recherche" name="recherche"><br>
    <input type="submit" value="Rechercher">
    </form>
</body>
</html>