package be.jossart.servlets;

import java.io.IOException;
import java.util.List;
import be.jossart.javabeans.Recipe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchRecipeServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recherche = request.getParameter("recherche");
		if (recherche.isEmpty()) {
			request.setAttribute("fail", "Veuillez entrer un nom à rechercher !");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp");
			dispatcher.forward(request, response);
		}else {
			List<Recipe> recipes = Recipe.findRecipe(recherche);
			request.setAttribute("Recettes", recipes);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/PrintRecipes.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
