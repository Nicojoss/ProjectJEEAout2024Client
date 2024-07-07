package be.jossart.javabeans;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Objects;

import be.jossart.dao.DAO;
import be.jossart.dao.PersonDAO;

public class Person implements Serializable {
	//Attributes
	private static final long serialVersionUID = -3448923763468846826L;
	private int idPerson;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private ArrayList<Recipe> recipeList; // 0..* Donc c'est au moment ou la person va creer une recette qu'il faut l'initialiser
	private static final DAO<Person> personDAO = new PersonDAO();
	//CTOR
	public Person(String firstnameParam, String lastnameParam, String usernameParam, String passwordParam) {
		super();
		this.firstname = firstnameParam;
		this.lastname = lastnameParam;
		this.username = usernameParam;
		this.password = passwordParam;
	}
	public Person(int idPerson, String firstname, String lastname, String username, String password) {
		super();
		this.idPerson = idPerson;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}
	public Person() {
	}
	//METHODS
	public boolean create() {
		return personDAO.create(this);
	}
	public static Person login(String usernameParam, String passwordParam) {
		PersonDAO dao = new PersonDAO();
		return dao.login(usernameParam, passwordParam);
	}
	public boolean updatePassword(String passwordParam) {
		PersonDAO dao = new PersonDAO();
		if(dao.updatePassword(this, passwordParam)) {
			this.setPassword(passwordParam);
			return true;
		}else {
			return false;
		}
		
	}
	public static Person find(int id) {
		return personDAO.find(id);
	}
	public static Person findId(Person person) {
		PersonDAO dao = new PersonDAO();
		return dao.findId(person);
	}
	
	@Override
	public String toString() {
		return "Person [idPerson=" + idPerson + ", firstname=" + firstname + ", lastname=" + lastname + ", username="
				+ username + ", password=" + password + ", recipeList=" + recipeList + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(firstname, idPerson, lastname, password, recipeList, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(firstname, other.firstname) && idPerson == other.idPerson
				&& Objects.equals(lastname, other.lastname) && Objects.equals(password, other.password)
				&& Objects.equals(recipeList, other.recipeList) && Objects.equals(username, other.username);
	}
	//GETTERS SETTERS
	public int getIdPerson() {
		return idPerson;
	}
	
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Recipe> getRecipeList() {
		return recipeList;
	}
	public void setRecipeList(ArrayList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
	
	
}
