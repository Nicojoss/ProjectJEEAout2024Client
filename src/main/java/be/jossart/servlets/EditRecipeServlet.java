package be.jossart.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.jossart.javabeans.Recipe;


public class EditRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 6964187018861383570L;
	public EditRecipeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int idRecipe = Integer.parseInt(request.getParameter("idRecipe"));
        Recipe recipe = Recipe.find(idRecipe);
        System.out.println("+++++" + recipe);
        if (recipe == null) {
            request.setAttribute("fail", "Recipe not found.");
            getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
            return;
        }
        request.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/WEB-INF/JSP/EditRecipe.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

