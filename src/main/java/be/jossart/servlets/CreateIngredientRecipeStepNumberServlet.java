package be.jossart.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.jossart.javabeans.Person;

public class CreateIngredientRecipeStepNumberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateIngredientRecipeStepNumberServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserLoggedIn(request)) {
            forwardToLogin(request, response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/JSP/CreateIngredientRecipeStepNumber.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isUserLoggedIn(request)) {
            forwardToLogin(request, response);
            return;
        }

        String ingredientNumberParam = request.getParameter("ingredientNumber");
        String recipeStepNumberParam = request.getParameter("recipeStepNumber");
        if (ingredientNumberParam == null || recipeStepNumberParam == null) {
            request.setAttribute("fail", "Ingredient number and recipe step number are required!");
            getServletContext().getRequestDispatcher("/WEB-INF/JSP/CreateIngredientRecipeStepNumber.jsp").forward(request, response);
            return;
        }

        if(request.getParameter("submit") != null) {
            HttpSession session = request.getSession();
            session.setAttribute("ingredientNumber", ingredientNumberParam);
            session.setAttribute("recipeStepNumber", recipeStepNumberParam);
            response.sendRedirect(request.getContextPath() + "/CreateRecipeServlet");
        } else {
            request.setAttribute("fail", "Failed to add ingredient and recipe step number!");
            getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        }
    }

    private boolean isUserLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return false;
        }
        Person person = (Person) session.getAttribute("person");
        return person != null;
    }

    private void forwardToLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
    }
}
