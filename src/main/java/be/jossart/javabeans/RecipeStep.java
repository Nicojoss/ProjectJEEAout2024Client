package be.jossart.javabeans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import be.jossart.dao.DAO;
import be.jossart.dao.RecipeStepDAO;

public class RecipeStep implements Serializable{
	//ATTRIBUTES
	private static final long serialVersionUID = -1368662956594765085L;
	private int idRecipeStep;
	private String instruction;
	private Recipe recipe;
	private static final DAO<RecipeStep> recipeStepDAO = new RecipeStepDAO();
	//CTOR
	public RecipeStep() { 
	}
	public RecipeStep(int idRecipeStep, String instruction, Recipe recipe) {
		this.idRecipeStep = idRecipeStep;
		this.instruction = instruction;
		this.recipe = recipe;
	}
	//METHODS
	public boolean create() {
		return recipeStepDAO.create(this);
	}
	public boolean delete() {
		return recipeStepDAO.delete(this);
	}
	public boolean update() {
		return recipeStepDAO.update(this);
	}
	public static RecipeStep find(int id) {
		return recipeStepDAO.find(id);
	}
	public static RecipeStep findId(RecipeStep recipeStep) {
		RecipeStepDAO dao = new RecipeStepDAO();
		return dao.findId(recipeStep);
	}
	public static List<Integer> findIds(int id) {
		RecipeStepDAO dao = new RecipeStepDAO();
		return dao.findIds(id);
	}
	//GETTERS SETTERS
	public int getIdRecipeStep() {
		return idRecipeStep;
	}
	public void setIdRecipeStep(int idRecipeStep) {
		this.idRecipeStep = idRecipeStep;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	@Override
	public String toString() {
		return "RecipeStep [idRecipeStep=" + idRecipeStep + ", instruction=" + instruction + ", recipe=" + recipe + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRecipeStep, instruction, recipe);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeStep other = (RecipeStep) obj;
		return idRecipeStep == other.idRecipeStep && Objects.equals(instruction, other.instruction)
				&& Objects.equals(recipe, other.recipe);
	}
}
