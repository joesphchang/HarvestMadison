package edu.matc.persistence;

import edu.matc.entity.Recipe;
import edu.matc.entity.SeasonalIngredient;
import edu.matc.entity.User;
import edu.matc.util.Database;
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

    }

    @Test
    void insertRecipeSuccess() {

    }

    @Test
    void deleteRecipe() {

    }

    @Test
    void getRecipesByUser() {

    }

    @Test
    void getRecipesBySeasonalIngredient() {

    }

    @Test
    void getAllRecipes() {
        List<Recipe> recipes = recipeDao.getAll();
        assertEquals(10, recipes.size());
    }

    @Test
    void getRecipeByPropertyEqual() {

    }
}
