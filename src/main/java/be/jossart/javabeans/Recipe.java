package be.jossart.javabeans;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.jossart.dao.DAO;
import be.jossart.dao.RecipeDAO;

public class Recipe implements Serializable{

	//ATTRIBUTES
	private static final long serialVersionUID = -7287441285222249732L;
	private int idRecipe;
	private String name;
	private Person person;
	private RecipeGender recipeGender;
	private HashMap<Double, Ingredient> recipeIngredientList;
	private ArrayList<RecipeStep> recipeStepList;
	private static final DAO<Recipe> recipeDAO = new RecipeDAO();
	
	//CTOR
	public Recipe() {
		recipeIngredientList = new HashMap<Double, Ingredient>();
		recipeStepList = new ArrayList<>();
	}
	public Recipe(int idRecipe, String name, Person person, RecipeGender recipeGender,
			HashMap<Double, Ingredient> recipeIngredientList
			, ArrayList<RecipeStep> recipeStepList) {
		super();
		this.idRecipe = idRecipe;
		this.name = name;
		this.person = person;
		this.recipeGender = recipeGender;
		this.recipeIngredientList = recipeIngredientList;
		this.recipeStepList = recipeStepList;
	}
	public Recipe(String name, RecipeGender recipeGender, Person person) {
		super();
		this.idRecipe = 0;
		this.name = name;
		this.person = person;
		this.recipeGender = recipeGender;
		recipeIngredientList = new HashMap<Double,Ingredient>();
		recipeStepList = new ArrayList<>();
	}
	//METHODS
	public boolean create() {
		return recipeDAO.create(this);
	}
	public boolean delete() {
		return recipeDAO.delete(this);
	}
	public boolean update() {
		return recipeDAO.update(this);
	}
	public static Recipe find(int id) {
		return recipeDAO.find(id);
	}
	public static Recipe findId(Recipe recipe) {
		RecipeDAO dao = new RecipeDAO();
		return dao.findId(recipe);
	}
	public static List<Integer> findIds(int id) {
		RecipeDAO dao = new RecipeDAO();
		return dao.findIds(id);
	}
	public static boolean createRecipeIngredient(int recipeId, int ingredientId, double quantity) {
		RecipeDAO dao = new RecipeDAO();
		return dao.createRecipeIngredient(recipeId, ingredientId, quantity);
	}
	public static boolean deleteRecipeIngredient(int recipeId, int ingredientId) {
		RecipeDAO dao = new RecipeDAO();
		return dao.deleteRecipeIngredient(recipeId, ingredientId);
	}
	public static boolean updateRecipeIngredient(int recipeId, int ingredientId, double quantity) {
		RecipeDAO dao = new RecipeDAO();
		return dao.updateRecipeIngredient(recipeId, ingredientId, quantity);
	}
	public static Recipe findRecipeIngredient(int recipeId, int ingredientId) {
		RecipeDAO dao = new RecipeDAO();
		return dao.findRecipeIngredient(recipeId, ingredientId);
	}
	@JsonIgnore
	public Ingredient getFirstIngredientFromRecipeIngredientList() {
		for(Ingredient i : this.getRecipeIngredientList().values()){
			return i;
		}
		return null;
	}
	@JsonIgnore
	public double getFirstQuantityFromRecipeIngredientList() {
		for(double i : this.getRecipeIngredientList().keySet()){
			return i;
		}
		return 0;
	}
	//GETTERS SETTERS
	public int getIdRecipe() {
		return idRecipe;
	}
	public void setIdRecipe(int idRecipe) {
		this.idRecipe = idRecipe;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public RecipeGender getRecipeGender() {
		return recipeGender;
	}
	public void setRecipeGender(RecipeGender recipeGender) {
		this.recipeGender = recipeGender;
	}
	@JsonIgnore
	public HashMap<Double, Ingredient> getRecipeIngredientList() {
		return recipeIngredientList;
	}
	@JsonIgnore
	public void setRecipeIngredientList(HashMap<Double, Ingredient> recipeIngredientList) {
		this.recipeIngredientList = recipeIngredientList;
	}
	public ArrayList<RecipeStep> getRecipeStepList() {
		return recipeStepList;
	}
	public void setRecipeStepList(ArrayList<RecipeStep> recipeStepList) {
		this.recipeStepList = recipeStepList;
	}
	@Override
	public String toString() {
		return "Recipe [idRecipe=" + idRecipe + ", name=" + name + ", person=" + person + ", recipeGender="
				+ recipeGender + ", recipeIngredientList=" + recipeIngredientList + ", recipeStepList=" + recipeStepList + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(idRecipe, recipeIngredientList, name, person, recipeGender, recipeStepList);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recipe other = (Recipe) obj;
		return idRecipe == other.idRecipe && Objects.equals(recipeIngredientList, other.recipeIngredientList)
				&& Objects.equals(name, other.name) && Objects.equals(person, other.person)
				&& recipeGender == other.recipeGender && Objects.equals(recipeStepList, other.recipeStepList);
	}
	public static List<Recipe> findRecipe(String recherche){
		RecipeDAO dao = new RecipeDAO();
		return dao.findRecipe(recherche);
	}
}
