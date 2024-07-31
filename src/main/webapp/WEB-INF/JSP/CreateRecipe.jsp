<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Recipe</title>
    <title>Recipe creation</title>
</head>
<body>
	<%@ include file="LogoutPartialView.jsp" %>
    <a href="/ProjectJEE2024Client/LogInServlet">Home Page</a>
    <%if (request.getAttribute("success") != null) {%>
       <div class="alert alert-success">
       <p><%= request.getAttribute("success")%></p> 
       </div>
    <% } %>
    <% if (request.getAttribute("fail") != null) {%>
        <div class="alert alert-danger">
      	<p><%= request.getAttribute("fail")%></p> 
       	</div>
    <% } %>
    <form action="CreateRecipeServlet" method="POST">
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <td>Recipe Name: </td>
                <td>
                    <input type="text" name="recipeName" id="recipeName" value="" size="20" required/>
                </td>
            </tr>
            <tr>
                <td>Recipe Gender: </td>
                <td>
                    <select name="recipeGender">
                        <option value="Entree" selected>Entree</option>
                        <option value="Dish">Dish</option>
                        <option value="Desserts">Desserts</option>
                        <option value="Cocktails">Cocktails</option>
                        <option value="VegetarianDishes">Vegetarian Dishes</option>
                    </select>
                </td>
            </tr>
			<tr>
                <td colspan="2">
                    <h3>Ingredients</h3>
                </td>
            </tr>
            <%
            	if (request.getAttribute("ingredientNumber") != null) {
            	for(int i = 0; i < (Integer)request.getAttribute("ingredientNumber"); i++) { 
            %>
           	<tr>
           		<td>Ingredient <%= i + 1 %></td>
           		<td></td>
           	</tr>
            <tr>
                <td>Ingredient Name: </td>
                <td>
                    <input type="text" name="ingredientName<%= i %>" value="" size="20" required/>
                </td>
            </tr>
            <tr>
                <td>Ingredient Type: </td>
                <td>
                    <select name="ingredientType<%= i %>">
                        <option value="Fruit">Fruit</option>
                        <option value="Vegetable">Vegetable</option>
                        <option value="Spicy">Spicy</option>
                        <option value="Other" selected>Other</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Ingredient Quantity: </td>
                <td>
                    <input type="text" name="ingredientQuantity<%= i %>" value="" size="10"/>
                </td>
            </tr>
            <% }} %>
            <tr>
                <td colspan="2">
                    <h3>Recipe Steps</h3>
                </td>
            </tr>
            <%
            	if (request.getAttribute("recipeStepNumber") != null) {
                for(int i = 0; i < (Integer)request.getAttribute("recipeStepNumber"); i++) {
            %>
            <tr>
            	<td>Recipe Step <%= i + 1 %></td>
            	<td></td>
            </tr>
            <tr>
            	
                <td>Step Instruction: </td>
                <td>
                    <textarea name="stepInstruction<%= i %>" rows="3" cols="30"></textarea>
                </td>
            </tr>
            <% }} %>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="submit" value="Create Recipe"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
