<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.jossart.javabeans.Ingredient,
                  be.jossart.javabeans.IngredientType,
                  be.jossart.javabeans.Person,
                  be.jossart.javabeans.Recipe,
                  be.jossart.javabeans.RecipeGender,
                  be.jossart.javabeans.RecipeIngredient,
                  be.jossart.javabeans.RecipeStep" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recipe Details</title>
</head>
<body>
	<p><%@ include file="LogoutPartialView.jsp" %></p>
    <h1>Recipe Details</h1>
	<% Recipe recipe = (Recipe)request.getAttribute("recipe"); %>
    <% if (recipe != null) { %>
        <h2><%= recipe.getName() %></h2>
        <p>Creator: <%= recipe.getPerson().getUsername() %></p>
        <p>Gender: <%= recipe.getRecipeGender().toString() %></p>
        <p>Ingredients:</p>
        <ul>
            <% for (RecipeIngredient recipeIngredient : recipe.getRecipeIngredientList()) { %>
                <li><%= recipeIngredient.getIngredient().getName() %> - <%= recipeIngredient.getQuantity() %>
                 <%= recipeIngredient.getIngredient().getType().toString() %></li>
            <% } %>
        </ul>
        <p>Steps:</p>
        <ol>
            <% for (RecipeStep recipeStep : recipe.getRecipeStepList()) { %>
                <li><%= recipeStep.getInstruction() %></li>
            <% } %>
        </ol>
    <% } %>

    <a href="/WEB-INF/JSP/Home.jsp">Retourner à la page d'accueil</a>
</body>
</html>