package be.jossart.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.jossart.javabeans.Ingredient;
import be.jossart.javabeans.Person;
import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeIngredient;
import be.jossart.javabeans.RecipeStep;

public class RecipeDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public RecipeDetailsServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idRecipe = Integer.parseInt(request.getParameter("idRecipe"));
        
        Recipe recipe = Recipe.find(idRecipe);
        Person person = Person.find(recipe.getPerson().getIdPerson());
        recipe.setPerson(person);
        List<Integer> idsRecipeIngredients = RecipeIngredient.findIds(idRecipe);
        List<Integer> idsRecipeSteps = RecipeStep.findIds(idRecipe);

        ArrayList<RecipeIngredient> recipeIngredients = new ArrayList<>();
        ArrayList<RecipeStep> recipeSteps = new ArrayList<>();

        for (int idRecipeIngredient : idsRecipeIngredients) {
            RecipeIngredient recipeIngredient = RecipeIngredient.find(idRecipe,idRecipeIngredient);

            Ingredient ingredient = Ingredient.find(recipeIngredient.getIdIngredient());
            recipeIngredient.setIngredient(ingredient);

            recipeIngredients.add(recipeIngredient);
        }

        for (int idRecipeStep : idsRecipeSteps) {
            RecipeStep recipeStep = RecipeStep.find(idRecipeStep);
            recipeSteps.add(recipeStep);
        }

        recipe.setRecipeIngredientList(recipeIngredients);
        recipe.setRecipeStepList(recipeSteps);
        request.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/WEB-INF/JSP/RecipeDetails.jsp").forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
