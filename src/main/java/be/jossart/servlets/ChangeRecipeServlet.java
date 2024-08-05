package be.jossart.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import be.jossart.javabeans.Ingredient;
import be.jossart.javabeans.IngredientType;
import be.jossart.javabeans.Person;
import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeGender;
import be.jossart.javabeans.RecipeStep;

public class ChangeRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ChangeRecipeServlet() {
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
        	request.setAttribute("fail", "Failed to update the recipe.");
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        }
        
        Recipe recipe = Recipe.find(idRecipe);
        //List<Integer> idsRecipeIngredients = RecipeIngredient.findIds(idRecipe);
        List<Integer> idsRecipeSteps = RecipeStep.findIds(idRecipe);

        ArrayList<RecipeStep> recipeSteps = new ArrayList<>();

        //for (int idRecipeIngredient : idsRecipeIngredients) {
            //RecipeIngredient recipeIngredient = RecipeIngredient.find(idRecipe,idRecipeIngredient);

            //Ingredient ingredient = Ingredient.find(recipeIngredient.getIdIngredient());
            //recipeIngredient.setIngredient(ingredient);

            //recipeIngredients.add(recipeIngredient);
        //}

        for (int idRecipeStep : idsRecipeSteps) {
            RecipeStep recipeStep = RecipeStep.find(idRecipeStep);
            recipeSteps.add(recipeStep);
        }

        //recipe.setRecipeIngredientList(recipeIngredients);
        recipe.setRecipeStepList(recipeSteps);
        request.setAttribute("recipe", recipe);

        getServletContext().getRequestDispatcher("/WEB-INF/JSP/ChangeRecipe.jsp").forward(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("person") == null) {
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
        	return;
        }
        
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String recipeName = request.getParameter("recipeName");
        RecipeGender recipeGender = RecipeGender.valueOf(request.getParameter("recipeGender"));

        Person person = (Person) session.getAttribute("person");
        
        Recipe updatedRecipe = new Recipe(recipeId, recipeName, person, recipeGender, null, null);
        boolean recipeUpdated = updatedRecipe.update();

        if (recipeUpdated) {
            String[] ingredientNames = request.getParameterValues("ingredientName");
            String[] ingredientTypes = request.getParameterValues("ingredientType");
            String[] ingredientQuantities = request.getParameterValues("ingredientQuantity");
            for (int i = 0; i < ingredientNames.length; i++) {
                String ingredientName = ingredientNames[i];
                IngredientType ingredientType = IngredientType.valueOf(ingredientTypes[i]);
                double ingredientQuantity = Double.parseDouble(ingredientQuantities[i]);

                Ingredient ingredient = new Ingredient();
                ingredient.setName(ingredientName);
                ingredient.setType(ingredientType);
                ingredient.create();
                ingredient = Ingredient.findId(ingredient);

                //RecipeIngredient recipeIngredient = new RecipeIngredient(recipeId, ingredient.getIdIngredient(), ingredientQuantity, ingredient, updatedRecipe);
                //recipeIngredient.update();
            }

            String[] stepInstructions = request.getParameterValues("stepInstruction");
            if(ingredientNames == null ||ingredientTypes == null || 
            		ingredientQuantities == null ||stepInstructions == null)
            {
	        	request.setAttribute("fail", "Failed to update the recipe. Please try again.");
	        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
	        }
            for (String stepInstruction : stepInstructions) {
                RecipeStep recipeStep = new RecipeStep(0, stepInstruction, updatedRecipe);
                recipeStep.update();
            }
            request.setAttribute("success", "Recipe updated successfully!");
            getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        } else {
            request.setAttribute("fail", "Failed to update the recipe. Please try again.");
            getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        }
    }
}
