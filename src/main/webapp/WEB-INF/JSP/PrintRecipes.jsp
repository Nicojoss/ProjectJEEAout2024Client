<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@page import="java.util.List"%>
    <%@page import="be.jossart.javabeans.Recipe" %>
    <%@page import="be.jossart.javabeans.RecipeGender" %>
    <%! private String afficherType(RecipeGender gender){
    	String type;
    	switch(gender){
    	case Entree:
    		type ="Entree";
    		break;
    	case Dish:
    		type ="Dish";
    		break;
    	case Desserts:
    		type ="Desserts";
    		break;
    	case Cocktails:
    		type ="Cocktails";
    		break;
    	case VegetarianDishes:
    		type ="VegetarianDishes";
    		break;
    	default:
    		type ="default";
    		break;
    	}
    	
    	
    	
    	return "<p>Genre :"+type+" </p>";
    }
    %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recettes trouvees </title>
</head>
<body>
<h1>Recettes : </h1>
<%
    List<Recipe> recipes = (List<Recipe>) request.getAttribute("Recettes");
    if (!recipes.isEmpty()) {
        for (Recipe r : recipes) {
    %>
            <div>
            	<p>Id : <%= r.getIdRecipe() %><p>
                <p>Nom :<%= r.getName() %><p>
                <%= afficherType(r.getRecipeGender()) %>
                <form action=RecipeDetailsServlet method="post">
        		<input type="hidden" name="id" value="<%= r.getIdRecipe() %>">
        		<label>Nom :</label>
        		<input type="hidden" name="nom" value="<%= r.getName() %>">
        		<label>Type :</label>
        		<input type="hidden" name="type" value="<%= afficherType(r.getRecipeGender()) %>">
        		<input type="submit" value="Voir les détails">
        		<input type="hidden" name="recipeId" value="<%= r.getIdRecipe() %>"/>
    </form>
                
            </div>
    <%
        }
    } else {
    %>
        <p>Aucune recette trouvée.</p>
    <%
    }
    %>
</body>
</html>