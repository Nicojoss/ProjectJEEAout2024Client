package be.jossart.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.jossart.javabeans.RecipeIngredient;

public class RecipeIngredientDAO extends DAO<RecipeIngredient> {

    public RecipeIngredientDAO() {
    }

    @Override
    public boolean create(RecipeIngredient obj) {
        MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
        paramsPost.add("idRecipe", Integer.toString(obj.getIdRecipe()));
        paramsPost.add("idIngredient", Integer.toString(obj.getIdIngredient()));
        paramsPost.add("quantity", Double.toString(obj.getQuantity()));

        try {
            ClientResponse res = this.resource
                    .path("recipeIngredient/create")
                    .accept(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, paramsPost);

            return res.getStatus() == 201;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(RecipeIngredient obj) {
    	MultivaluedMap<String, String> paramsDelete = new MultivaluedMapImpl();
    	paramsDelete.add("idRecipe", String.valueOf(obj.getIdRecipe()));
    	paramsDelete.add("idIngredient", String.valueOf(obj.getIdIngredient()));

        try {
            ClientResponse res = this.resource
                    .path("recipeIngredient/delete")
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class,paramsDelete);

            return res.getStatus() == 204;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(RecipeIngredient obj) {
        MultivaluedMap<String, String> paramsPut = new MultivaluedMapImpl();
        paramsPut.add("idRecipe", Integer.toString(obj.getIdRecipe()));
        paramsPut.add("idIngredient", Integer.toString(obj.getIdIngredient()));
        paramsPut.add("quantity", Double.toString(obj.getQuantity()));

        try {
            ClientResponse res = this.resource
                    .path("recipeIngredient/update")
                    .accept(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, paramsPut);

            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public RecipeIngredient find(int id) {
    	// TODO Auto-generated method stub
        return null;
    }

    public RecipeIngredient find(int idRecipe, int idIngredient) {
        try {
            ClientResponse res = this.resource
                    .path("recipeIngredient2/get/" + idRecipe + "/" + idIngredient)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (res.getStatus() == 200) {
                String response = res.getEntity(String.class);
                JSONObject json = new JSONObject(response);

                int recipeId = json.getInt("idRecipe");
                int ingredientId = json.getInt("idIngredient");
                double quantity = json.getDouble("quantity");

                RecipeIngredient recipeIngredient = new RecipeIngredient(recipeId, ingredientId, quantity, null, null);

                return recipeIngredient;
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

    @Override
    public ArrayList<RecipeIngredient> findAll(Object obj) {
    	// TODO Auto-generated method stub
        return null;
    }

    public RecipeIngredient findId(RecipeIngredient recipeIngredient) {
        try {
            int idRecipe = recipeIngredient.getIdRecipe();
            double quantity = recipeIngredient.getQuantity();

            ClientResponse response = this.resource
                    .path("recipeIngredient2/getId/" + idRecipe + "/" + quantity)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONObject json = new JSONObject(responseJson);

                int retrievedIngredientId = json.getInt("idIngredient");
                return new RecipeIngredient(recipeIngredient.getIdRecipe(), retrievedIngredientId, recipeIngredient.getQuantity(), null, null);
            } else if (response.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe ingredient. Status: " + response.getStatus());
                return null;
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    public List<Integer> findIds(int recipeId) {
        try {
            ClientResponse response = this.resource
                    .path("recipeIngredient2/findIds/" + recipeId)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONArray jsonArray = new JSONArray(responseJson);

                List<Integer> ingredientIds = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    ingredientIds.add(jsonArray.getInt(i));
                }

                return ingredientIds;
            } else if (response.getStatus() == 404) {
                return Collections.emptyList();
            } else {
                System.out.println("Failed to retrieve recipe ingredient IDs. Status: " + response.getStatus());
                return Collections.emptyList();
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return Collections.emptyList();
        }
    }
}

