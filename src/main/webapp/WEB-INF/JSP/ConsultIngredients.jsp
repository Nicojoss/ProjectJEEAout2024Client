<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.jossart.javabeans.Ingredient" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingredients list</title>
</head>
<body>

<h1>Ingredients list</h1>
<%@ include file="LogoutPartialView.jsp" %>
<a href="/ProjectJEEAout2024Client/HomeServlet">Home Page</a>
<table border="1">
    <tbody>
        <% if (request.getAttribute("ingredients") != null && !((ArrayList<Ingredient>)request.getAttribute("ingredients")).isEmpty()) { %>
	        <table border="1" cellspacing="0" cellpadding="5">
	            <tr>
	                <th>ID</th>
	                <th>Name</th>
	                <th>Type</th>
	            </tr>
	            <% 
	            	ArrayList<Ingredient> ingredients = (ArrayList<Ingredient>) request.getAttribute("ingredients");
	                for (Ingredient ingredient : ingredients) { 
	            %>
	                <tr>
	                    <td><%= ingredient.getIdIngredient() %></td>
	                    <td><%= ingredient.getName() %></td>
	                    <td><%= ingredient.getType() %></td>
	                </tr>
	            <% } %>
	        </table>
	    <% } else { %>
	        <p>No ingredients available.</p>
	    <% } %>
    </tbody>
</table>

</body>
</html>