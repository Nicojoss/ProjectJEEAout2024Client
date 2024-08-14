<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to the login page!</title>
<jsp:include page="/WEB-INF/JSP/Bootstrap.jsp"/>
</head>
<body>
    <div class="container">
        <a class="btn btn-primary" href="/ProjectJEEAout2024Client/home">Home Page</a>
        <% if (request.getAttribute("success") != null) { %>
            <div class="alert alert-success mt-3">
                <p><%= request.getAttribute("success") %></p>
            </div>
        <% } %>
        <% if (request.getAttribute("fail") != null) { %>
            <div class="alert alert-danger mt-3">
                <p><%= request.getAttribute("fail") %></p>
            </div>
        <% } %>
        <form action="LogInServlet" method="POST" class="mt-4">
            <div class="form-group">
                <label for="username">Username :</label>
                <input type="text" class="form-control" name="username" id="username" value="" size="20" />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" name="password" id="password" value="" size="20" />
            </div>
            <div class="form-group text-center">
                <input type="submit" class="btn btn-primary" name="submit" id="submit" value="Submit" />
            </div>
        </form>
    </div>
</body>
</html>