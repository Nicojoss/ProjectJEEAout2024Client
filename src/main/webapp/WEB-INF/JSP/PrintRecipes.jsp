<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="be.jossart.javabeans.Recipe" %>
<%@ page import="be.jossart.javabeans.Ingredient" %>
<%@ page import="be.jossart.javabeans.RecipeStep" %>
<%@ page import="be.jossart.javabeans.RecipeGender" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Recettes trouvées</title>
</head>
<body>
<h1>Recettes</h1>

<%
    List<Recipe> recipes = (List<Recipe>) request.getAttribute("Recettes");
    if (recipes != null && !recipes.isEmpty()) {
        for (Recipe r : recipes) {
%>
            <div>
                <p>Id : <%= r.getIdRecipe() %></p>
                <p>Nom : <%= r.getName() %></p>
                <p>Genre : <%= r.getRecipeGender() %></p>
<%
                Map<Integer, Ingredient> ingredientMap = r.getRecipeIngredientList();
                if (ingredientMap != null && !ingredientMap.isEmpty()) {
                    for (Map.Entry<Integer, Ingredient> entry : ingredientMap.entrySet()) {
                        Integer key = entry.getKey();
                        Ingredient value = entry.getValue();
%>
                        <div>
                            <p>Ingredient Key: <%= key %></p>
                            <p>Ingredient: <%= value.getName() %> (Type: <%= value.getType() %>)</p>
                        </div>
<%
                    }
                } else {
%>
                    <p>Aucun ingrédient trouvé</p>
<%
                }

                List<RecipeStep> steps = r.getRecipeStepList();
                if (steps != null && !steps.isEmpty()) {
                    for (RecipeStep s : steps) {
%>
                        <div>
                            <p>Instructions : <%= s.getInstruction() %></p>
                        </div>
<%
                    }
                } else {
%>
                    <p>Aucune étape trouvée</p>
<%
                }
%>
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
