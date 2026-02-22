package com.joeychang.persistence;

import com.joeychang.entity.Recipe;
import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.entity.User;
import com.joeychang.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeDaoTest {

    GenericDao<User> userDao;
    GenericDao<Recipe> recipeDao;
    GenericDao<SeasonalIngredient> seasonalIngredientDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        userDao = new GenericDao<>(User.class);
        recipeDao = new GenericDao<>(Recipe.class);
        seasonalIngredientDao = new GenericDao<>(SeasonalIngredient.class);
    }

    @Test
    void getRecipeByIdSuccess() {
        Recipe retrievedRecipe = (Recipe)recipeDao.getById(1);
        assertNotNull(retrievedRecipe);
        assertEquals("Beet Salad", retrievedRecipe.getRecipeName());
        assertEquals(1, retrievedRecipe.getUser().getId());
    }

    @Test
    void updateRecipeSuccess() {
        Recipe recipe = (Recipe)recipeDao.getById(6);
        recipe.setDescription("Excellent for breakfast, brunch, lunch, or dinner; can be made ahead and stored in the fridge for days.");
        recipeDao.update(recipe);

        Recipe retrievedRecipe = (Recipe)recipeDao.getById(6);
        assertEquals("Excellent for breakfast, brunch, lunch, or dinner; can be made ahead and stored in the fridge for days.", retrievedRecipe.getDescription());
    }

    @Test
    void insertRecipeSuccess() {
        User user = userDao.getById(3);
        SeasonalIngredient seasonalIngredient = seasonalIngredientDao.getById(2);

        Recipe recipe = new Recipe("Perfect Baked Potato", "A perfect baked potato is hard to beat. The outside is brown and crisp, coated in a crust of sea salt.", "Russet Potatoes, Extra Virgin Olive Oil, Sea Salt", user, seasonalIngredient);

        int insertedRecipeId = recipeDao.insert(recipe);

        Recipe retrievedRecipe = (Recipe)recipeDao.getById(insertedRecipeId);

        assertNotNull(retrievedRecipe);
        assertEquals(recipe.getDescription(), retrievedRecipe.getDescription());
        assertEquals("Alice", recipe.getUser().getFirstName());
    }

    @Test
    void deleteRecipe() {
        recipeDao.delete(recipeDao.getById(10));
        assertNull(recipeDao.getById(10));
    }

    @Test
    void getRecipesByUser() {
        User user = userDao.getById(1);

        Recipe retrievedRecipe = (Recipe)recipeDao.getById(2);

        assertEquals(user.getFirstName(), retrievedRecipe.getUser().getFirstName());
    }

    @Test
    void getRecipesBySeasonalIngredient() {
        SeasonalIngredient beets = seasonalIngredientDao.getById(12);

        List<Recipe> beetRecipes = recipeDao.findByPropertyEqual("seasonalIngredient", beets);

        assertNotNull(beetRecipes);
        assertEquals(3, beetRecipes.size());
    }

    @Test
    void getAllRecipes() {
        List<Recipe> recipes = recipeDao.getAll();
        assertEquals(10, recipes.size());
    }

    @Test
    void getRecipeByPropertyEqual() {
        List<Recipe> recipes = recipeDao.findByPropertyEqual("recipeName", "Apple Crisp");
        assertEquals(1, recipes.size());
        assertEquals(5, recipes.get(0).getId());
    }
}
