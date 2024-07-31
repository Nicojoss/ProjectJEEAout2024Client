package be.jossart.dao;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.jossart.javabeans.Ingredient;
import be.jossart.javabeans.IngredientType;

public class IngredientDAO extends DAO<Ingredient>{
	
	public IngredientDAO() {
		
	}
	@Override
	public boolean create(Ingredient obj) {
	    JSONObject json = new JSONObject();
	    json.put("name", obj.getName());
	    json.put("type", obj.getType().toString());

	    try {
	        ClientResponse res = this.resource
	                .path("ingredient")
	                .type(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .post(ClientResponse.class, json.toString());

	        if (res.getStatus() == 201) {
	            String response = res.getEntity(String.class);
	            JSONObject jsonResponse = new JSONObject(response);

	            obj.setIdIngredient(jsonResponse.getInt("idIngredient"));
	            return true;
	        }
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	        return false;
	    }
	    return false;
	}

	@Override
    public boolean delete(Ingredient obj) {
		MultivaluedMap<String, String> paramsDelete = new MultivaluedMapImpl();
		paramsDelete.add("id", String.valueOf(obj.getIdIngredient()));
		
        try {
            ClientResponse res = this.resource
                    .path("ingredient/"+obj.getIdIngredient())
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class,paramsDelete);

            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

	@Override
	public boolean update(Ingredient obj) {
	    JSONObject json = new JSONObject();
	    json.put("idIngredient", obj.getIdIngredient());
	    json.put("name", obj.getName());
	    json.put("type", obj.getType().toString());

	    try {
	        ClientResponse res = this.resource
	                .path("ingredient")
	                .type(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .put(ClientResponse.class, json.toString());

	        return res.getStatus() == 200;
	    } catch (Exception ex) {
	        System.out.println(ex.getMessage());
	        return false;
	    }
	}

	@Override
	public Ingredient find(int id) {
	    try {
	        ClientResponse res = this.resource
	                .path("ingredient/" + id)
	                .accept(MediaType.APPLICATION_JSON)
	                .get(ClientResponse.class);

	        if (res.getStatus() == 200) {
	            String response = res.getEntity(String.class);
	            JSONObject json = new JSONObject(response);

	            int ingredientId = json.getInt("idIngredient");
	            String name = json.getString("name");
	            String type = json.getString("type");

	            Ingredient ingredient = new Ingredient(ingredientId, name, IngredientType.valueOf(type), null);

	            return ingredient;
	        } else if (res.getStatus() == 404) {
	            return null;
	        } else {
	            System.out.println("Failed to retrieve ingredient. Status: " + res.getStatus());
	            return null;
	        }
	    } catch (JSONException ex) {
	        System.out.println(ex.getMessage());
	        return null;
	    }
	}

	@Override
	public ArrayList<Ingredient> findAll(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Ingredient findId(Ingredient ingredient) {
	    try {
	        String name = ingredient.getName();
	        String type = ingredient.getType().toString();

	        ClientResponse response = this.resource
	                .path("ingredient2/getId/" + name + "/" + type)
	                .accept(MediaType.APPLICATION_JSON)
	                .get(ClientResponse.class);

	        if (response.getStatus() == 200) {
	            String responseJson = response.getEntity(String.class);
	            JSONObject json = new JSONObject(responseJson);

	            int ingredientId = json.getInt("idIngredient");

	            return new Ingredient(ingredientId, name, IngredientType.valueOf(type), null);
	        } else if (response.getStatus() == 404) {
	            return null;
	        } else {
	            System.out.println("Failed to retrieve ingredient. Status: " + response.getStatus());
	            return null;
	        }
	    } catch (JSONException ex) {
	        System.out.println(ex.getMessage());
	        return null;
	    }
	}
}
