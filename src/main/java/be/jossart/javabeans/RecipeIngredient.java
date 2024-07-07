package be.jossart.javabeans;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import be.jossart.dao.DAO;
import be.jossart.dao.RecipeIngredientDAO;

public class RecipeIngredient implements Serializable{
	//ATTRIBUTES
	private static final long serialVersionUID = -3286148896425585540L;
	private int idIngredient;
	private int idRecipe;
	private double quantity;
	private Ingredient ingredient;
	private Recipe recipe;
	private static final DAO<RecipeIngredient> recipeIngredientDAO = new RecipeIngredientDAO();
	//CTOR
	public RecipeIngredient() {}
	public RecipeIngredient(int idRecipe, int idIngredient, double quantity,
			Ingredient ingredient, Recipe recipe) {
		super();
		this.idIngredient = idIngredient;
		this.idRecipe = idRecipe;
		this.quantity = quantity;
		this.ingredient = ingredient;
		this.recipe = recipe;
	}
	public RecipeIngredient(int idRecipe, double quantity,Ingredient ingredient,
			Recipe recipe) {
		super();
		this.idRecipe = idRecipe;
		this.quantity = quantity;
		this.ingredient = ingredient;
		this.recipe = recipe;
	}
	//METHODS
	public boolean create() {
		return recipeIngredientDAO.create(this);
	}
	public boolean delete() {
		return recipeIngredientDAO.delete(this);
	}
	public boolean update() {
		return recipeIngredientDAO.update(this);
	}
	public static RecipeIngredient find(int idRecipe, int idIngredient) {
		RecipeIngredientDAO dao = new RecipeIngredientDAO();
		return dao.find(idRecipe, idIngredient);
	}
	public static RecipeIngredient findId(RecipeIngredient recipeIngredient) {
		RecipeIngredientDAO dao = new RecipeIngredientDAO();
		return dao.findId(recipeIngredient);
	}
	public static List<Integer> findIds(int id) {
		RecipeIngredientDAO dao = new RecipeIngredientDAO();
		return dao.findIds(id);
	}
	//GETTERS AND SETTERS
	public int getIdIngredient() {
		return idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	public int getIdRecipe() {
		return idRecipe;
	}
	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Ingredient getIngredient() {
		return ingredient;
	}
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	@Override
	public String toString() {
		return "RecipeIngredient_Server [idIngredient=" + idIngredient + ", idRecipe=" + idRecipe + ", quantity="
				+ quantity + ", ingredient=" + ingredient + ", recipe=" + recipe + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idIngredient, idRecipe, ingredient, quantity, recipe);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeIngredient other = (RecipeIngredient) obj;
		return idIngredient == other.idIngredient && idRecipe == other.idRecipe
				&& Objects.equals(ingredient, other.ingredient)
				&& Double.doubleToLongBits(quantity) == Double.doubleToLongBits(other.quantity)
				&& Objects.equals(recipe, other.recipe);
	}
}
