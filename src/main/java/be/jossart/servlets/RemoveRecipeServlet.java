package be.jossart.servlets;

import java.io.IOException;


import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.jossart.javabeans.Person;
import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeIngredient;
import be.jossart.javabeans.RecipeStep;

public class RemoveRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RemoveRecipeServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int idRecipe = Integer.parseInt(request.getParameter("idRecipe"));
        HttpSession session = request.getSession(false);
        Person person = (Person) session.getAttribute("person");
        if (session == null || person == null) {
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
        	return;
        }
        
        List<Integer> idsRecipe = Recipe.findIds(person.getIdPerson());
        if(!idsRecipe.contains(idRecipe))
        {
        	request.setAttribute("fail", "Failed to delete the recipe.");
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        	return;
        }

        List<Integer> recipeStepListIds = RecipeStep.findIds(idRecipe);
        List<Integer> recipeIngredientListIds = RecipeIngredient.findIds(idRecipe);

        for (int recipeStepId : recipeStepListIds) {
        	RecipeStep recipeStep = new RecipeStep(recipeStepId,null,null);
            recipeStep.delete();
        }

        for (int recipeIngredientId : recipeIngredientListIds) {
        	RecipeIngredient recipeIngredient = new RecipeIngredient(idRecipe,recipeIngredientId,null,null);
            recipeIngredient.delete();
        }
        
        Recipe recipe = new Recipe(idRecipe,null,null,null,null,null);
        boolean isRecipeDeleted = recipe.delete();
        if (isRecipeDeleted) {
        	request.setAttribute("success", "Success to delete the recipe!");
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        } else {
        	request.setAttribute("fail", "Failed to delete the recipe.");
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
