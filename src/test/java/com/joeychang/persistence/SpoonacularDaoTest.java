package com.joeychang.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joeychang.entity.SpoonacularRecipe;
import com.joeychang.util.PropertiesLoader;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpoonacularDaoTest implements PropertiesLoader {

    @Test
    void getRecipeByIdSuccess() throws Exception {
        Properties properties = loadProperties("/spoonacular.properties");
        String apiKey = properties.getProperty("spoonacular.api.key");

        SpoonacularDao dao = new SpoonacularDao();
        SpoonacularRecipe recipe = dao.getSpoonacularRecipeById(apiKey);

        assertNotNull(recipe);
        assertEquals("Cauliflower, Anchovies and Tomatoes", recipe.getTitle());
    }
}