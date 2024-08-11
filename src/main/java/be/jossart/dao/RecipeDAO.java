package be.jossart.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.core.MultivaluedMap;
import org.json.JSONException;
import org.json.JSONObject;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.jossart.javabeans.Ingredient;
import be.jossart.javabeans.Person;
import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeGender;

public class RecipeDAO extends DAO<Recipe> {
    public RecipeDAO() {
    }
    @Override
    public boolean create(Recipe obj) {
        try {
            JSONObject json = new JSONObject();
            json.put("name", obj.getName());
            json.put("gender", obj.getRecipeGender().toString());
            json.put("idPerson", obj.getPerson().getIdPerson());

            ClientResponse res = this.resource
                    .path("recipe")
                    .type(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, json.toString());

            if (res.getStatus() == 201) {
                String response = res.getEntity(String.class);
                JSONObject jsonResponse = new JSONObject(response);
                obj.setIdRecipe(jsonResponse.getInt("idRecipe"));
                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        return false;
    }

    public boolean Delete(int recipe_id) {
    	System.out.println("id recipe " + recipe_id);
    	MultivaluedMap<String, String> paramsDelete = new MultivaluedMapImpl();
    	paramsDelete.add("id", String.valueOf(recipe_id));
        try {
            ClientResponse res = this.resource
                    .path("recipe/"+recipe_id)
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class,paramsDelete);
            System.out.println("resultat api = " + res.getStatus()) ;
            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    @Override
    public boolean delete(Recipe obj) {
    	MultivaluedMap<String, String> paramsDelete = new MultivaluedMapImpl();
    	paramsDelete.add("id", String.valueOf(obj.getIdRecipe()));
        try {
            ClientResponse res = this.resource
                    .path("recipe/"+obj.getIdRecipe())
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class,paramsDelete);
            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean update(Recipe obj) {
        JSONObject json = new JSONObject();
        json.put("idRecipe", obj.getIdRecipe());
        json.put("name", obj.getName());
        json.put("recipeGender", obj.getRecipeGender().toString());
        json.put("idPerson", obj.getPerson().getIdPerson());

        try {
            ClientResponse res = this.resource
                    .path("recipe")
                    .type(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, json.toString());

            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
	public List<Recipe> findRecipe(String recherche){
		String responseJSON = this.resource.path("recipe/search/"+String.valueOf(recherche)).accept(MediaType.APPLICATION_JSON).get(String.class);
		List<Recipe> recipes = new ArrayList<Recipe>();
		System.out.println("json : " + responseJSON);
		JSONArray array = new JSONArray(responseJSON);
		ObjectMapper mapper = new ObjectMapper();
		for(int i =0;i<array.length();i++) {
			String personJSON = array.get(i).toString();
			try {
				recipes.add(mapper.readValue(personJSON,Recipe.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recipes;
	}
	public List<Recipe> findUserRecipe(int personId){
		String responseJSON = this.resource.path("recipe/personRecipes/"+Integer.valueOf(personId)).accept(MediaType.APPLICATION_JSON).get(String.class);
		List<Recipe> recipes = new ArrayList<Recipe>();
		System.out.println("json : " + responseJSON);
		JSONArray array = new JSONArray(responseJSON);
		ObjectMapper mapper = new ObjectMapper();
		for(int i =0;i<array.length();i++) {
			String personJSON = array.get(i).toString();
			try {
				recipes.add(mapper.readValue(personJSON,Recipe.class));
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recipes;
	}

    @Override
    public Recipe find(int id) {
        try {
            ClientResponse res = this.resource
                    .path("recipe/" + id)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (res.getStatus() == 200) {
                String response = res.getEntity(String.class);
                JSONObject json = new JSONObject(response);

                int recipeId = json.getInt("idRecipe");
                String name = json.getString("name");
                String gender = json.getString("recipeGender");
                int idPerson = json.getInt("idPerson");

                Person person = new Person(idPerson, null, null, null, null);
                RecipeGender recipeGender = RecipeGender.valueOf(gender);

                Recipe recipe = new Recipe(recipeId, name, person, recipeGender, null, null);

                return recipe;
            } else if (res.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe. Status: " + res.getStatus());
                return null;
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Recipe> findAll(Object obj) {
    	// TODO Auto-generated method stub
        return null;
    }

    public Recipe findId(Recipe recipe) {
        try {
            String name = recipe.getName();
            String gender = recipe.getRecipeGender().toString();
            int idPerson = recipe.getPerson().getIdPerson();

            ClientResponse response = this.resource
                    .path("recipe2/getId/" + name + "/" + gender + "/" + idPerson)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONObject json = new JSONObject(responseJson);

                int recipeId = json.getInt("idRecipe");

                return new Recipe(recipeId, null, null, null, null, null);
            } else if (response.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe. Status: " + response.getStatus());
                return null;
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    public List<Integer> findIds(int idPerson) {
        try {
            ClientResponse response = this.resource
                    .path("recipe2/findIds/" + idPerson)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONArray jsonArray = new JSONArray(responseJson);

                List<Integer> recipeIds = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    recipeIds.add(jsonArray.getInt(i));
                }

                return recipeIds;
            } else if (response.getStatus() == 404) {
                return Collections.emptyList();
            } else {
                System.out.println("Failed to retrieve recipe IDs. Status: " + response.getStatus());
                return Collections.emptyList();
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return Collections.emptyList();
        }
    }

    public boolean createRecipeIngredient(int recipeId, int ingredientId, double quantity) {
    	JSONObject json = new JSONObject();
        json.put("recipeId", recipeId);
        json.put("ingredientId", ingredientId);
        json.put("quantity", quantity);

        try {
            ClientResponse res = this.resource
                    .path("recipeIngredient")
                    .type(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, json.toString());

            return res.getStatus() == 201;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean deleteRecipeIngredient(int recipeId, int ingredientId) {
        try {
            ClientResponse res = this.resource
                    .path("recipeIngredient/"+recipeId+"/"+
                    		ingredientId)
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class);

            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateRecipeIngredient(int recipeId, int ingredientId, double quantity) {
        JSONObject json = new JSONObject();
        json.put("recipeId", recipeId);
        json.put("ingredientId", ingredientId);
        json.put("quantity", quantity);

        try {
            ClientResponse res = this.resource
                    .path("recipeIngredient")
                    .type(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, json.toString());

            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public Recipe findRecipeIngredient(int idRecipe, int idIngredient) {
        try {
        	HashMap<Double,Ingredient> recipeIngredientList 
        							   = new HashMap<Double,Ingredient>();
            ClientResponse res = this.resource
                    .path("recipeIngredient/" + idRecipe + "/" + idIngredient)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (res.getStatus() == 200) {
                String response = res.getEntity(String.class);
                JSONObject json = new JSONObject(response);

                int recipeId = json.getInt("idRecipe");
                int ingredientId = json.getInt("idIngredient");
                double quantity = json.getDouble("quantity");
                
                recipeIngredientList.put(quantity, 
                		new Ingredient(ingredientId,null,null,null));
                Recipe recipe = new Recipe(recipeId,null,null,null,
                		recipeIngredientList,null);

                return recipe;
            } else if (res.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe ingredient. Status: " + res.getStatus());
                return null;
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
