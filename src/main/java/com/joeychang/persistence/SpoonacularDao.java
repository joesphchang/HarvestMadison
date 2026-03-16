package com.joeychang.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joeychang.entity.ExtendedIngredientsItem;
import com.joeychang.entity.SpoonacularRecipe;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class SpoonacularDao {

    private final Logger logger = LogManager.getLogger(this.getClass());

    SpoonacularRecipe getSpoonacularRecipeById(String apiKey) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://api.spoonacular.com/recipes/23" + "/information?apiKey=" + apiKey);

        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SpoonacularRecipe recipe = null;
        try {
            recipe = mapper.readValue(response, SpoonacularRecipe.class);
        } catch (JsonProcessingException e) {
            logger.error("Error parsing Spoonacular JSON for ID {}: {}", e.getMessage(), e);
        }
        return recipe;
    }
}
