package com.joeychang.persistence;

import com.joeychang.entity.Recipe;
import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.entity.User;
import com.joeychang.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Recipe dao test.
 */
public class RecipeDaoTest {

    /**
     * The User dao.
     */
    GenericDao<User> userDao;
    /**
     * The Recipe dao.
     */
    GenericDao<Recipe> recipeDao;
    /**
     * The Seasonal ingredient dao.
     */
    GenericDao<SeasonalIngredient> seasonalIngredientDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        userDao = new GenericDao<>(User.class);
        recipeDao = new GenericDao<>(Recipe.class);
        seasonalIngredientDao = new GenericDao<>(SeasonalIngredient.class);
    }

    /**
     * Gets recipe by id success.
     */
    @Test
    void getRecipeByIdSuccess() {
        Recipe retrievedRecipe = (Recipe)recipeDao.getById(1);
        assertNotNull(retrievedRecipe);
        assertEquals("Beet Salad", retrievedRecipe.getRecipeName());
        assertEquals(1, retrievedRecipe.getUser().getId());
    }

    /**
     * Update recipe success.
     */
    @Test
    void updateRecipeSuccess() {
        Recipe recipe = (Recipe)recipeDao.getById(6);
        recipe.setDescription("Excellent for breakfast, brunch, lunch, or dinner; can be made ahead and stored in the fridge for days.");
        recipeDao.update(recipe);

        Recipe retrievedRecipe = (Recipe)recipeDao.getById(6);
        assertEquals("Excellent for breakfast, brunch, lunch, or dinner; can be made ahead and stored in the fridge for days.", retrievedRecipe.getDescription());
    }

    /**
     * Insert recipe success.
     */
    @Test
    void insertRecipeSuccess() {
        User user = userDao.getById(1);
        SeasonalIngredient seasonalIngredient = seasonalIngredientDao.getById(3);

        Recipe newRecipe = new Recipe("Perfect Baked Potato", "A light, refreshing salad featuring Madison-grown microgreens and a citrus vinaigrette.", "Microgreens, radish, sunflower seeds, lemon vinaigrette", "https://i.imgur.com/9sTvjzt.jpeg", user, seasonalIngredient);

        int insertedRecipeId = recipeDao.insert(newRecipe);

        Recipe retrievedRecipe = (Recipe)recipeDao.getById(insertedRecipeId);
        newRecipe.setCreatedOn(retrievedRecipe.getCreatedOn());
        assertEquals(newRecipe.getId(), retrievedRecipe.getId());
    }

    /**
     * Delete recipe.
     */
    @Test
    void deleteRecipe() {
        User user = userDao.getById(1);
        SeasonalIngredient ingredient = seasonalIngredientDao.getById(1);

        Recipe recipeToInsert = new Recipe("Test Recipe", "Description", "Ingredients", "image.url", user, ingredient);
        int id = recipeDao.insert(recipeToInsert);

        Recipe retrievedRecipe = (Recipe)recipeDao.getById(id);
        recipeDao.delete(retrievedRecipe);

        assertNull(recipeDao.getById(id));
    }

    /**
     * Gets recipes by user.
     */
    @Test
    void getRecipesByUser() {
        User user = userDao.getById(1);

        Recipe retrievedRecipe = (Recipe)recipeDao.getById(2);

        assertEquals(user.getFirstName(), retrievedRecipe.getUser().getFirstName());
    }

    /**
     * Gets recipes by seasonal ingredient.
     */
    @Test
    void getRecipesBySeasonalIngredient() {
        SeasonalIngredient beets = seasonalIngredientDao.getById(12);

        List<Recipe> beetRecipes = recipeDao.findByPropertyEqual("seasonalIngredient", beets);

        assertNotNull(beetRecipes);
        assertEquals(3, beetRecipes.size());
    }

    /**
     * Gets all recipes.
     */
    @Test
    void getAllRecipes() {
        List<Recipe> recipes = recipeDao.getAll();
        assertEquals(10, recipes.size());
    }

    /**
     * Gets recipe by property equal.
     */
    @Test
    void getRecipeByPropertyEqual() {
        List<Recipe> recipes = recipeDao.findByPropertyEqual("recipeName", "Apple Crisp");
        assertEquals(1, recipes.size());
        assertEquals(5, recipes.get(0).getId());
    }

    /**
     * Delete recipe with user.
     */
    // @Test
//    void deleteRecipeWithUser() {
//        List<Recipe> recipes = recipeDao.getAll();
//        assertFalse(recipes.isEmpty(), "Dump is empty!");
//        Recipe recipeToDelete = recipes.get(0);
//
//        User user = recipeToDelete.getUser();
//        int userId = user.getId();
//        int recipeId = recipeToDelete.getId();
//
//        recipeDao.delete(recipeToDelete);
//
//        assertNull(recipeDao.getById(recipeId), "Recipe should be deleted");
//        assertNotNull(userDao.getById(userId), "User should still exist after recipe deletion");
//    }
}
