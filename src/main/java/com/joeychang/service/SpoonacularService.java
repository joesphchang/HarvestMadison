package com.joeychang.service;

import com.joeychang.entity.SpoonacularRecipe;
import com.joeychang.persistence.SpoonacularDao;
import com.joeychang.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Spoonacular service.
 */
public class SpoonacularService implements PropertiesLoader {

    private static final Logger logger = LogManager.getLogger(SpoonacularService.class);
    private final SpoonacularDao spoonacularDao;
    private final String apiKey;

    // --- Cache for random recipes ---
    private static List<SpoonacularRecipe> cachedRandomRecipes = null;
    private static long cacheTimestamp = 0;
    private static final long CACHE_TTL_MS = 24 * 60 * 60 * 1000L

    /**
     * Instantiates a new Spoonacular service.
     */
    public SpoonacularService() {
        this.spoonacularDao = new SpoonacularDao();
        String loadedKey = "";
        try {
            Properties properties = loadProperties("/spoonacular.properties");
            loadedKey = properties.getProperty("spoonacular.api.key");
        } catch (Exception e) {
            logger.error("Could not load Spoonacular API key", e);
        }
        this.apiKey = loadedKey;
    }

    /**
     * Search recipes list.
     *
     * @param searchTerm the search term
     * @return the list
     */
    public List<SpoonacularRecipe> searchRecipes(String searchTerm) {
        try {
            return spoonacularDao.searchRecipes(searchTerm, apiKey);
        } catch (Exception e) {
            logger.error("Failed to search Spoonacular recipes for term '{}'", searchTerm, e);
            return new ArrayList<>();
        }
    }

    /**
     * Gets recipe by id.
     *
     * @param recipeId the recipe id
     * @return the recipe by id
     */
    public SpoonacularRecipe getRecipeById(int recipeId) {
        try {
            return spoonacularDao.getSpoonacularRecipeById(recipeId, apiKey);
        } catch (Exception e) {
            logger.error("Failed to fetch Spoonacular recipe for ID {}", recipeId, e);
            return null;
        }
    }

    /**
     * Gets random recipes.
     *
     * @return the random recipes
     */
    public List<SpoonacularRecipe> getRandomRecipes() {
        long now = System.currentTimeMillis();
        if (cachedRandomRecipes != null && (now - cacheTimestamp) < CACHE_TTL_MS) {
            logger.info("Returning cached random recipes, skipping API call");
            return cachedRandomRecipes;
        }
        try {
            List<SpoonacularRecipe> recipes = spoonacularDao.getRandomRecipes(apiKey);
            cachedRandomRecipes = recipes;
            cacheTimestamp = now;
            return recipes;
        } catch (Exception e) {
            logger.error("Failed to fetch random Spoonacular recipes", e);
            return cachedRandomRecipes != null ? cachedRandomRecipes : new ArrayList<>();
        }
    }
}