<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.jossart.javabeans.Recipe" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consult Own Recipes</title>
</head>
<body>
    <h2>Your Recipes</h2>
	<div class="d-flex justify-content-start mb-3">
        <a class="btn btn-primary" href="/ProjectJEEAout2024Client/HomeServlet">Home Page</a>
        <jsp:include page="LogoutPartialView.jsp" />
    </div>
    <% if (request.getAttribute("recipes") != null && ((ArrayList<Recipe>)request.getAttribute("recipes")).size() > 0) { %>
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Gender</th>
                <th>Action</th>
            </tr>
            <% for (Recipe recipe : (ArrayList<Recipe>)request.getAttribute("recipes")) { %>
                <tr>
                    <td><%= recipe.getIdRecipe() %></td>
                    <td><%= recipe.getName() %></td>
                    <td><%= recipe.getRecipeGender() %></td>
                    <td>
                        <a class="btn btn-warning" href="EditRecipeServlet?idRecipe=<%= recipe.getIdRecipe() %>">Edit</a>
                        <a class="btn btn-danger" href="RemoveRecipeServlet?idRecipe=<%= recipe.getIdRecipe() %>">Delete</a>
                    </td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No recipes available.</p>
    <% } %>

    <br>
</body>
</html>



