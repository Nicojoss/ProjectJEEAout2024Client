<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.jossart.javabeans.Ingredient,
                  be.jossart.javabeans.IngredientType,
                  be.jossart.javabeans.Person,
                  be.jossart.javabeans.Recipe,
                  be.jossart.javabeans.RecipeGender,
                  be.jossart.javabeans.RecipeIngredient,
                  be.jossart.javabeans.RecipeStep" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Recipe</title>
    <script>
    	function addIngredientFields() {
        	var container = document.getElementById('ingredientFields');
        	var ingredientNameField = document.createElement('input');
        	ingredientNameField.type = 'text';
        	ingredientNameField.name = 'ingredientName';
        	ingredientNameField.value = '';
        	ingredientNameField.size = '20';
        	var ingredientTypeField = document.createElement('select');
        	ingredientTypeField.name = 'ingredientType';
        	var types = ['Fruit', 'Vegetable', 'Spicy', 'Other'];
        	for (var i = 0; i < types.length; i++) {
            	var option = document.createElement('option');
           		option.value = types[i];
            	option.text = types[i];
            	ingredientTypeField.appendChild(option);
        	}
        	ingredientTypeField.value = 'Other';
        	var ingredientQuantityField = document.createElement('input');
        	ingredientQuantityField.type = 'text';
        	ingredientQuantityField.name = 'ingredientQuantity';
        	ingredientQuantityField.value = '';
        	ingredientQuantityField.size = '10';
        	container.appendChild(ingredientNameField);
        	container.appendChild(ingredientTypeField);
        	container.appendChild(ingredientQuantityField);
    	}
        function addField(containerId, fieldName) {
            var container = document.getElementById(containerId);
            var field = document.createElement("input");
            field.type = "text";
            field.name = fieldName;
            field.value = "";
            field.size = "20";
            container.appendChild(field);
        }
    </script>
</head>
<body>
    <a href="/ProjectJEEAout2024Client/HomeServlet">Home Page</a>
    <form action="ChangeRecipeServlet" method="POST">
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <td>Recipe Name: </td>
                <td>
                    <input type="text" name="recipeName" id="recipeName" value="<%= ((Recipe)request.getAttribute("recipe")).getName() %>" size="20"/>
                </td>
            </tr>
            <tr>
                <td>Recipe Gender: </td>
                <td>
                    <select name="recipeGender">
                        <% RecipeGender currentGender = ((Recipe)request.getAttribute("recipe")).getRecipeGender(); %>
                        <option value="Entree" <%= currentGender == RecipeGender.Entree ? "selected" : "" %>>Entree</option>
                        <option value="Dish" <%= currentGender == RecipeGender.Dish ? "selected" : "" %>>Dish</option>
                        <option value="Desserts" <%= currentGender == RecipeGender.Desserts ? "selected" : "" %>>Desserts</option>
                        <option value="Cocktails" <%= currentGender == RecipeGender.Cocktails ? "selected" : "" %>>Cocktails</option>
                        <option value="VegetarianDishes" <%= currentGender == RecipeGender.VegetarianDishes ? "selected" : "" %>>Vegetarian Dishes</option>
                    </select>
                </td>
            </tr>
			<tr>
    			<td colspan="2">
        			<h3>Ingredients</h3>
   				 </td>
			</tr>
				<%
    				List<RecipeIngredient> recipeIngredients = ((Recipe)request.getAttribute("recipe")).getRecipeIngredientList();
    				if (recipeIngredients != null) {
        			for (int i = 0; i < recipeIngredients.size(); i++) {
            			RecipeIngredient ingredient = recipeIngredients.get(i);
				%>
            <tr>
                <td>Ingredient Name: </td>
                <td>
                    <input type="text" name="ingredientName<%= i %>" value="<%= ingredient.getIngredient().getName() %>" size="20"/>
                </td>
            </tr>
            <tr>
                <td>Ingredient Type: </td>
                <td>
                    <select name="ingredientType<%= i %>">
                        <option value="Fruit" <%= ingredient.getIngredient().getType() == IngredientType.Fruit ? "selected" : "" %>>Fruit</option>
                        <option value="Vegetable" <%= ingredient.getIngredient().getType() == IngredientType.Vegetable ? "selected" : "" %>>Vegetable</option>
                        <option value="Spicy" <%= ingredient.getIngredient().getType() == IngredientType.Spicy ? "selected" : "" %>>Spicy</option>
                        <option value="Other" <%= ingredient.getIngredient().getType() == IngredientType.Other ? "selected" : "" %>>Other</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Ingredient Quantity: </td>
                <td>
                    <input type="text" name="ingredientQuantity<%= i %>" value="<%= ingredient.getQuantity() %>" size="10"/>
                </td>
            </tr>
				<%
        				}
    				}
				%>
			<tr>
    			<td colspan="2">
        			<button type="button" onclick="addIngredientFields()">Add Ingredient</button>
    			</td>
			</tr>
            <tr>
                <td colspan="2">
                    <h3>Recipe Steps</h3>
                </td>
            </tr>
            	<% List<RecipeStep> recipeSteps = ((Recipe)request.getAttribute("recipe")).getRecipeStepList();
               	   if (recipeSteps != null) {
                   		for (RecipeStep step : recipeSteps) {
            	%>
               		<tr>
                    	<td>Step Instruction: </td>
                    	<td>
                        	<textarea name="stepInstruction" rows="3" cols="30"><%= step.getInstruction() %></textarea>
                    	</td>
                	</tr>
            	<% }
               	   } %>
            <tr>
                <td colspan="2">
                    <button type="button" onclick="addField('recipeStepFields', 'stepInstruction')">Add Recipe Step</button>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="submit" value="Update Recipe"/>
                </td>
            </tr>
        </table>
        <input type="hidden" name="recipeId" value="<%= ((Recipe)request.getAttribute("recipe")).getIdRecipe() %>"/>
    </form>
</body>
</html>


