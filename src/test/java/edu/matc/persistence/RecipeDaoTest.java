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

    GenericDao<Recipe> recipeDao;
    GenericDao<User> userDao;
    GenericDao<SeasonalIngredient> ingredientDao;

    @BeforeEach
    void setUp() {
        Database.getInstance().runSQL("cleanDB.sql");
        recipeDao = new GenericDao<>(Recipe.class);
        userDao = new GenericDao<>(User.class);
        ingredientDao = new GenericDao<>(SeasonalIngredient.class);
    }

    @Test
    void getRecipeByIdSuccess() {

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

    }

    @Test
    void getRecipeByPropertyEqual() {

    }
}
