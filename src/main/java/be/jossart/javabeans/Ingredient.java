package be.jossart.javabeans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import be.jossart.dao.DAO;
import be.jossart.dao.IngredientDAO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ingredient implements Serializable{
	//ATTRIBUTES
	private static final long serialVersionUID = 634419802561898936L;
	private int idIngredient;
	private String name;
	private IngredientType type;
	private ArrayList<Recipe> recipeList;
	private static final DAO<Ingredient> ingredientDAO = new IngredientDAO();
	//CTOR
	public Ingredient() {
	}
	public Ingredient(int idIngredient, String name, IngredientType type,
			ArrayList<Recipe> recipeList) {
		this.idIngredient = idIngredient;
		this.name = name;
		this.type = type;
		this.recipeList = recipeList;
	}
	//METHODS
	public boolean create() {
		return ingredientDAO.create(this);
	}
	public boolean delete() {
		return ingredientDAO.delete(this);
	}
	public boolean update() {
		return ingredientDAO.update(this);
	}
	public static Ingredient find(int id) {
		return ingredientDAO.find(id);
	}
	public static Ingredient findId(Ingredient ingredient) {
		IngredientDAO dao = new IngredientDAO();
		return dao.findId(ingredient);
	}
	//GETTERS SETTERS
	public int getIdIngredient() {
		return idIngredient;
	}
	public void setIdIngredient(int idIngredient) {
		this.idIngredient = idIngredient;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IngredientType getType() {
		return type;
	}
	public void setType(IngredientType type) {
		this.type = type;
	}
	public ArrayList<Recipe> getRecipeList() {
		return recipeList;
	}
	public void setRecipeList(ArrayList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
	@Override
	public String toString() {
		return "Ingredient [idIngredient=" + idIngredient + ","
				+ " name=" + name + ", type=" + type + ", recipeList=" + recipeList
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idIngredient, name, recipeList, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		return idIngredient == other.idIngredient && Objects.equals(name, other.name)
				&& Objects.equals(recipeList, other.recipeList) && type == other.type;
	}
}
