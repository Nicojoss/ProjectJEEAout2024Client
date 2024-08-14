package be.jossart.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
        int recipeId = Integer.parseInt(request.getParameter("idRecipe"));
        String recipeName = request.getParameter("recipeName");
        String recipeGenderStr = request.getParameter("recipeGender");
        RecipeGender recipeGender = RecipeGender.valueOf(recipeGenderStr);
        
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("person");
        
        HashMap<Double, Ingredient> recipeIngredientList = new HashMap<>();
        
        int ingredientCount = Integer.parseInt(request.getParameter("ingredientCount").trim());
        for (int i = 0; i < ingredientCount; i++) {
        	int idIngredient = Integer.parseInt(request.getParameter("idIngredient" + i).trim());
            String ingredientName = request.getParameter("ingredientName" + i);
            String ingredientTypeStr = request.getParameter("ingredientType" + i);
            IngredientType ingredientType = IngredientType.valueOf(ingredientTypeStr);
            double ingredientQuantity = Double.parseDouble(request.getParameter("ingredientQuantity" + i));
            
            Ingredient ingredient = new Ingredient(idIngredient, ingredientName, ingredientType, null);
            recipeIngredientList.put(ingredientQuantity, ingredient);
        }
        
        ArrayList<RecipeStep> recipeStepList = new ArrayList<>();
        String stepCountStr = request.getParameter("stepCount");
        int stepCount = stepCountStr != null ? Integer.parseInt(stepCountStr.trim()) : 0;
        
        for (int i = 0; i < stepCount; i++) {
        	int idStep = Integer.parseInt(request.getParameter("idStep" + i).trim());
            String stepInstruction = request.getParameter("stepInstruction" + i);
            RecipeStep step = new RecipeStep(idStep, stepInstruction, null);
            recipeStepList.add(step);
        }
        
        Recipe recipe = new Recipe(recipeId, recipeName, person, recipeGender, recipeIngredientList, recipeStepList);
        
        if(recipe.update()) {
        	request.setAttribute("success", "Success to update the recipe.");
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
            return;
        }else {
        	request.setAttribute("fail", "Fail to update the recipe.");
        	getServletContext().getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
            return;
        }
    }
}
