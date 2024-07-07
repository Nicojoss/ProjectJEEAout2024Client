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

import be.jossart.javabeans.Recipe;
import be.jossart.javabeans.RecipeStep;

public class RecipeStepDAO extends DAO<RecipeStep> {

    public RecipeStepDAO() {
    }

    @Override
    public boolean create(RecipeStep obj) {
        MultivaluedMap<String, String> paramsPost = new MultivaluedMapImpl();
        paramsPost.add("instruction", obj.getInstruction());
        paramsPost.add("recipeId", Integer.toString(obj.getRecipe().getIdRecipe()));

        try {
            ClientResponse res = this.resource
                    .path("recipeStep/create")
                    .accept(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, paramsPost);

            return res.getStatus() == 201;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(RecipeStep obj) {
    	MultivaluedMap<String, String> paramsDelete = new MultivaluedMapImpl();
    	paramsDelete.add("id", String.valueOf(obj.getIdRecipeStep()));

        try {
            ClientResponse res = this.resource
                    .path("recipeStep/delete")
                    .accept(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class,paramsDelete);

            return res.getStatus() == 204;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(RecipeStep obj) {
        MultivaluedMap<String, String> paramsPut = new MultivaluedMapImpl();
        paramsPut.add("id", String.valueOf(obj.getIdRecipeStep()));
        paramsPut.add("instruction", obj.getInstruction());
        paramsPut.add("recipeId", Integer.toString(obj.getRecipe().getIdRecipe()));

        try {
            ClientResponse res = this.resource
                    .path("recipeStep/update")
                    .accept(MediaType.APPLICATION_JSON)
                    .put(ClientResponse.class, paramsPut);

            return res.getStatus() == 200;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public RecipeStep find(int id) {
        try {
            ClientResponse res = this.resource
                    .path("recipeStep2/get/" + id)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (res.getStatus() == 200) {
                String response = res.getEntity(String.class);
                JSONObject json = new JSONObject(response);

                int recipeStepId = json.getInt("idRecipeStep");
                String instruction = json.getString("instruction");
                int recipeId = json.getInt("recipeId");

                Recipe recipe = new Recipe(recipeId, null, null, null, null, null);

                return new RecipeStep(recipeStepId, instruction, recipe);
            } else if (res.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe step. Status: " + res.getStatus());
                return null;
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<RecipeStep> findAll(Object obj) {
    	// TODO Auto-generated method stub
        return null;
    }

    public RecipeStep findId(RecipeStep recipeStep) {
        try {
            String instruction = recipeStep.getInstruction();
            int recipeId = recipeStep.getRecipe().getIdRecipe();

            ClientResponse response = this.resource
                    .path("recipeStep2/getId/" + recipeId + "/" + instruction)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONObject json = new JSONObject(responseJson);

                int recipeStepId = json.getInt("idRecipeStep");

                Recipe retrievedRecipe = new Recipe(recipeStep.getRecipe().getIdRecipe(), null, null, null, null, null);

                return new RecipeStep(recipeStepId, recipeStep.getInstruction(), retrievedRecipe);
            } else if (response.getStatus() == 404) {
                return null;
            } else {
                System.out.println("Failed to retrieve recipe step. Status: " + response.getStatus());
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
                    .path("recipeStep2/findIds/" + recipeId)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() == 200) {
                String responseJson = response.getEntity(String.class);
                JSONArray jsonArray = new JSONArray(responseJson);

                List<Integer> stepIds = new ArrayList<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    stepIds.add(jsonArray.getInt(i));
                }

                return stepIds;
            } else if (response.getStatus() == 404) {
                return Collections.emptyList();
            } else {
                System.out.println("Failed to retrieve recipe step IDs. Status: " + response.getStatus());
                return Collections.emptyList();
            }
        } catch (JSONException ex) {
            System.out.println(ex.getMessage());
            return Collections.emptyList();
        }
     }
}

