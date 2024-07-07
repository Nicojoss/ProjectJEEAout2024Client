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
		MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("name", obj.getName());
		paramsPost.add("type",  obj.getType().toString());

		try {
			 ClientResponse res = this.resource
	 	                .path("ingredient/create")
	 	                .accept(MediaType.APPLICATION_JSON)
	 	                .post(ClientResponse.class, paramsPost);
			
			if (res.getStatus() == 201) {
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
                    .path("ingredient/delete")
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class,paramsDelete);

            return res.getStatus() == 204;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

	@Override
    public boolean update(Ingredient obj) {
        MultivaluedMap<String, String> paramsPut = new MultivaluedMapImpl();
        paramsPut.add("id", String.valueOf(obj.getIdIngredient()));
        paramsPut.add("name", obj.getName());
        paramsPut.add("type", obj.getType().toString());
        try {
            ClientResponse res = this.resource
                    .path("ingredient/update")
                    .accept(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, paramsPut);

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
	                .path("ingredient2/get/" + id)
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
