<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, be.jossart.javabeans.Recipe, be.jossart.javabeans.Ingredient, be.jossart.javabeans.RecipeStep, be.jossart.javabeans.RecipeGender" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recettes trouvées</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            padding: 8px 12px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f2f2f2;
        }
        h1 {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>

<h1>Recettes</h1>

<table>
    <%
        List<Recipe> recipes = (List<Recipe>) request.getAttribute("Recettes");
        if (recipes != null && !recipes.isEmpty()) {
            for (Recipe r : recipes) {
    %>
                <tr><th colspan="2">Nom : <%= r.getName() %></th></tr>
                <tr><td>Genre :</td><td><%= r.getRecipeGender() %></td></tr>
                <tr><th colspan="2">Ingrédients</th></tr>
                <%
                    Map<Double, Ingredient> ingredientMap = r.getRecipeIngredientList();
                    if (ingredientMap != null && !ingredientMap.isEmpty()) {
                        for (Map.Entry<Double, Ingredient> entry : ingredientMap.entrySet()) {
                            Double quantity = entry.getKey();
                            Ingredient ingredient = entry.getValue();
                %>
                            <tr>
                                <td>Nom :</td><td><%= ingredient.getName() %> (Type: <%= ingredient.getType() %>)</td>
                            </tr>
                            <tr>
                                <td>Quantité :</td><td><%= quantity %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr><td colspan="2">Aucun ingrédient trouvé</td></tr>
                <%
                    }
                %>
                <tr><th colspan="2">Instructions</th></tr>
                <%
                    List<RecipeStep> steps = r.getRecipeStepList();
                    if (steps != null && !steps.isEmpty()) {
                        for (RecipeStep s : steps) {
                %>
                            <tr>
                                <td colspan="2"><%= s.getInstruction() %></td>
                            </tr>
                <%
                        }
                    } else {
                %>
                        <tr><td colspan="2">Aucune instruction trouvée</td></tr>
                <%
                    }
                %>
    <%
            }
            %><tr><td colspan="2"></td></tr>
<%
        } else {
    %>
            <tr><td colspan="2">Aucune recette trouvée.</td></tr>
    <%
        }
    %>
</table>

</body>
</html>
