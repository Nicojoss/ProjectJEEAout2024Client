package be.jossart.servlets;

import java.io.IOException;
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
import be.jossart.javabeans.RecipeIngredient;
import be.jossart.javabeans.RecipeStep;

public class CreateRecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CreateRecipeServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
	    Person person = (Person) session.getAttribute("person");
        if (session == null || person == null) {
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
        	return;
        }
        
        Integer ingredientNumber = Integer.parseInt((String) session.getAttribute("ingredientNumber"));
        Integer recipeStepNumber = Integer.parseInt((String) session.getAttribute("recipeStepNumber"));
        request.setAttribute("ingredientNumber", ingredientNumber);
        request.setAttribute("recipeStepNumber", recipeStepNumber);
        
		getServletContext().getRequestDispatcher("/WEB-INF/JSP/CreateRecipe.jsp").forward(request, response);
	}
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		    HttpSession session = request.getSession(false);
		    Person person = (Person) session.getAttribute("person");
	        if (session == null || person == null) {
	        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/LogIn.jsp").forward(request, response);
	        	return;
	        }
	        //Recipe
	        Recipe recipe = new Recipe(request.getParameter("recipeName"),
	        					       RecipeGender.valueOf(request.getParameter("recipeGender")),
	        					       person);
	        //Ingredient
	        Ingredient ingredient = new Ingredient();
	        RecipeIngredient recipeIngredient = new RecipeIngredient();
	        
	        //RecipeStep
	        RecipeStep recipeStep = new RecipeStep();
	        
	        if (recipe.create()) {
	        	//RecipeStep Attributes
	            for(int i = 0; i < Integer.parseInt((String) session.getAttribute("recipeStepNumber")); i++){
		        	recipeStep.setInstruction(request.getParameter("stepInstruction"+i));
		        	recipeStep.setRecipe(recipe);
		        	if(!recipeStep.create()) {
		        		request.setAttribute("fail", "Failed to create the recipe. Please try again.");
		            	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
		                return;
		        	}
			        recipeStep = new RecipeStep();
		        }
	        	//Ingredient Creation & RecipeIngredient
	            for(int i = 0; i < Integer.parseInt((String) session.getAttribute("ingredientNumber")); i++) {
		        	ingredient.setName(request.getParameter("ingredientName"+i));
		        	ingredient.setType(IngredientType.valueOf(request.getParameter("ingredientType"+i)));
			        recipeIngredient.setQuantity(Double.parseDouble(request.getParameter("ingredientQuantity"+i)));
			        recipeIngredient.setRecipe(recipe);
			        if (ingredient.create()) {
		                recipeIngredient.setIngredient(ingredient);
		                if(!recipeIngredient.create()) {
			        		request.setAttribute("fail", "Failed to create the recipe. Please try again.");
			            	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
			                return;
			        	}
		            } else {
		            	request.setAttribute("fail", "Failed to create the recipe. Please try again.");
		            	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
		                return;
		            }
			        ingredient = new Ingredient();
			        recipeIngredient = new RecipeIngredient();
		        }
		        request.setAttribute("success", "Success to create the recipe.");
	        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
	            return;
	        } else {
	            request.setAttribute("fail", "Failed to create the recipe. Please try again.");
	            getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
	            return;
	        }
	 }
}
