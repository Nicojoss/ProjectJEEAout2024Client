<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="be.jossart.javabeans.Ingredient" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ingredients list</title>
    <jsp:include page="/WEB-INF/JSP/Bootstrap.jsp"/>
</head>
<body>
<div class="container mt-4">
    <h1>Ingredients list</h1>
    <div class="d-flex justify-content-start mb-3">
        <a class="btn btn-primary" href="/ProjectJEEAout2024Client/HomeServlet">Home Page</a>
        <jsp:include page="LogoutPartialView.jsp" />
    </div>
    <div class="table-responsive">
        <% if (request.getAttribute("ingredients") != null && !((ArrayList<Ingredient>)request.getAttribute("ingredients")).isEmpty()) { %>
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Type</th>
                    </tr>
                </thead>
                <tbody>
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
                </tbody>
            </table>
        <% } else { %>
            <div class="alert alert-warning" role="alert">
                No ingredients available.
            </div>
        <% } %>
    </div>
</div>
</body>
</html>