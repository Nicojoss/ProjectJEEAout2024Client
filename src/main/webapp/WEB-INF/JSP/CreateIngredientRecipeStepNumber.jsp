<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add ingredients and recipe step number for your recipe</title>
</head>
<body>
	<%@ include file="LogoutPartialView.jsp" %>
    <a href="/ProjectJEE2023Client/LogInServlet">Home Page</a>
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
    <form action="CreateIngredientRecipeStepNumberServlet" method="POST">
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <td>Ingredient Number: </td>
                <td>
                    <input type="number" name="ingredientNumber" id="ingredientNumber" 
                    	min="1" max="50" required/>
                </td>
            </tr>
            <tr>
                <td>Recipe Step Number: </td>
                <td>
                    <input type="number" name="recipeStepNumber" id="recipeStepNumber" 
                    	min="1" max="50" required/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="submit" value="Next"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>