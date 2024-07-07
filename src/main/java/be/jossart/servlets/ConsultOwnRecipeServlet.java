package be.jossart.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import be.jossart.dao.RecipeDAO;
import be.jossart.javabeans.Person;
import be.jossart.javabeans.Recipe;

public class ConsultOwnRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ConsultOwnRecipeServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("person") == null) {
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
        	return;
        }
        Person person = (Person) session.getAttribute("person");
        int idPerson = person.getIdPerson();

        RecipeDAO recipeDAO = new RecipeDAO();
        List<Integer> recipeIds = recipeDAO.findIds(idPerson);

        ArrayList<Recipe> recipes = new ArrayList<>();

        for (int idRecipe : recipeIds) {
            Recipe recipe = recipeDAO.find(idRecipe);
            if (recipe != null) {
                recipes.add(recipe);
            }
        }
        request.setAttribute("recipes", recipes);
        getServletContext().getRequestDispatcher("/WEB-INF/JSP/ConsultOwnRecipe.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
