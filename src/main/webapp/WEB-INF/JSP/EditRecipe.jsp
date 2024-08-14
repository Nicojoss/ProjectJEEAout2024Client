<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.jossart.javabeans.Recipe" %>
<%@ page import="be.jossart.javabeans.RecipeStep" %>
<%@ page import="be.jossart.javabeans.RecipeGender" %>
<%@ page import="be.jossart.javabeans.Ingredient" %>
<%@ page import="be.jossart.javabeans.IngredientType" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Recipe</title>
</head>
<body>
    <%@ include file="LogoutPartialView.jsp" %>
    <a class="btn btn-primary" href="/ProjectJEEAout2024Client/HomeServlet">Home Page</a>
    <%
    	Recipe recipe = (Recipe) request.getAttribute("recipe");
	%>
	<form action="ChangeRecipeServlet" method="POST">
	    <input type="hidden" name="idRecipe" value="<%= recipe.getIdRecipe() %>" />
	    
	    <table border="1" cellspacing="0" cellpadding="5">
	        <tr>
	            <td>Recipe Name: </td>
	            <td>
	                <input type="text" name="recipeName" id="recipeName" value="<%= recipe.getName() %>" size="20" required/>
	            </td>
	        </tr>
	        <tr>
	            <td>Recipe Gender: </td>
	            <td>
	                <select name="recipeGender">
	                    <option value="Entree" <%= recipe.getRecipeGender() == RecipeGender.Entree ? "selected" : "" %>>Entree</option>
	                    <option value="Dish" <%= recipe.getRecipeGender() == RecipeGender.Dish ? "selected" : "" %>>Dish</option>
	                    <option value="Desserts" <%= recipe.getRecipeGender() == RecipeGender.Desserts ? "selected" : "" %>>Desserts</option>
	                    <option value="Cocktails" <%= recipe.getRecipeGender() == RecipeGender.Cocktails ? "selected" : "" %>>Cocktails</option>
	                    <option value="VegetarianDishes" <%= recipe.getRecipeGender() == RecipeGender.VegetarianDishes ? "selected" : "" %>>Vegetarian Dishes</option>
	                </select>
	            </td>
	        </tr>
	        <tr>
	            <td colspan="2">
	                <h3>Ingredients</h3>
	            </td>
	        </tr>
	        <%
	            List<Ingredient> ingredients = new ArrayList<>(recipe.getRecipeIngredientList().values());
	            for (int i = 0; i < ingredients.size(); i++) {
	                Ingredient ingredient = ingredients.get(i);
	        %>
	        <tr>
	            <td>Ingredient <%= i + 1 %></td>
	            <td></td>
	        </tr>
	        <tr>
	            <td>Ingredient Name: </td>
	            <td>
	                <input type="text" name="ingredientName<%= i %>" value="<%= ingredient.getName() %>" size="20" required/>
	            	<input type="hidden" name="idIngredient<%= i %>" value="<%= ingredient.getIdIngredient() %>" />
	            </td>
	        </tr>
	        <tr>
	            <td>Ingredient Type: </td>
	            <td>
	                <select name="ingredientType<%= i %>">
	                    <option value="Fruit" <%= ingredient.getType() == IngredientType.Fruit ? "selected" : "" %>>Fruit</option>
	                    <option value="Vegetable" <%= ingredient.getType() == IngredientType.Vegetable ? "selected" : "" %>>Vegetable</option>
	                    <option value="Spicy" <%= ingredient.getType() == IngredientType.Spicy ? "selected" : "" %>>Spicy</option>
	                    <option value="Other" <%= ingredient.getType() == IngredientType.Other ? "selected" : "" %>>Other</option>
	                </select>
	            </td>
	        </tr>
	        <tr>
	            <td>Ingredient Quantity: </td>
	            <td>
	                <input type="text" name="ingredientQuantity<%= i %>" value="<%= recipe.getRecipeIngredientList().keySet().iterator().next() %>" size="10"/>
	                <input type="hidden" name="ingredientCount" value="<%= ingredients.size() %>" />
	            </td>
	        </tr>
	        <% } %>
	        <tr>
	            <td colspan="2">
	                <h3>Recipe Steps</h3>
	            </td>
	        </tr>
	        <%
	            List<RecipeStep> steps = recipe.getRecipeStepList();
	            for (int i = 0; i < steps.size(); i++) {
	                RecipeStep step = steps.get(i);
	                String instruction = step.getInstruction();
	        %>
	        <tr>
	            <td>Recipe Step <%= i + 1 %></td>
	            <td></td>
	        </tr>
	        <tr>
	            <td>Step Instruction: </td>
	            <td>
	                <textarea name="stepInstruction<%= i %>" rows="3" cols="30"><%= instruction %></textarea>
	                <input type="hidden" name="stepCount" value="<%= steps.size() %>" />
	                <input type="hidden" name="idStep<%= i %>" value="<%= step.getIdRecipeStep() %>" />
	            </td>
	        </tr>
	        <% } %>
	        <tr>
	            <td colspan="2" align="center">
	                <input type="submit" name="submit" value="Update Recipe"/>
	            </td>
	        </tr>
	    </table>
	</form>
</body>
</html>