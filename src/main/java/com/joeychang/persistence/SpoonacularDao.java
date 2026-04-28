package com.joeychang.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeychang.entity.ExtendedIngredientsItem;
import com.joeychang.entity.SpoonacularRecipe;
import com.joeychang.entity.SpoonacularSearchResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.*;

/**
 * The type Spoonacular dao.
 */
public class SpoonacularDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Gets spoonacular recipe by id.
     *
     * @param recipeId the recipe id
     * @param apiKey   the api key
     * @return the spoonacular recipe by id
     */
    public SpoonacularRecipe getSpoonacularRecipeById(int recipeId, String apiKey) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.spoonacular.com/recipes/" + recipeId + "/information?apiKey=" + apiKey);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            return mapper.readValue(response, SpoonacularRecipe.class);
        } catch (JsonProcessingException e) {
            logger.error("Error parsing Spoonacular JSON for ID {}: {}", recipeId, e.getMessage());
            return null;
        }
    }

    /**
     * Search recipes list.
     *
     * @param searchTerm the search term
     * @param apiKey     the api key
     * @return the list
     */
    public List<SpoonacularRecipe> searchRecipes(String searchTerm, String apiKey) {
        Client client = ClientBuilder.newClient();
        String url = "https://api.spoonacular.com/recipes/complexSearch?query=" + searchTerm + "&addRecipeInformation=true&apiKey=" + apiKey;

        String responseJson = client.target(url).request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            SpoonacularSearchResponse response = mapper.readValue(responseJson, SpoonacularSearchResponse.class);
            return response.getResults();
        } catch (Exception e) {
            logger.error("Search mapping failed", e);
            return new java.util.ArrayList<>();
        }
    }

    /**
     * Gets random recipes.
     *
     * @param apiKey the api key
     * @return the random recipes
     */
    public List<SpoonacularRecipe> getRandomRecipes(String apiKey) {
        Client client = ClientBuilder.newClient();
        String url = "https://api.spoonacular.com/recipes/random?number=10&apiKey=" + apiKey;
        String responseJson = client.target(url).request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try {
            SpoonacularSearchResponse response = mapper.readValue(responseJson, SpoonacularSearchResponse.class);
            return response.getRecipes();
        } catch (Exception e) {
            logger.error("Error mapping random recipes", e);
            return new java.util.ArrayList<>();
        }
    }
}
