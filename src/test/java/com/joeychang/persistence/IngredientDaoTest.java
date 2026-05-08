package com.joeychang.persistence;

import com.joeychang.entity.Ingredient;
import com.joeychang.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Ingredient dao test.
 */
public class IngredientDaoTest {

    /**
     * The Ingredient dao.
     */
    GenericDao<Ingredient> ingredientDao;
    private static final Logger logger = LogManager.getLogger(IngredientDaoTest.class);
    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        ingredientDao = new GenericDao<>(Ingredient.class);
    }

    /**
     * Gets ingredient by id success.
     */
    @Test
    void getIngredientByIdSuccess() {
        Ingredient retrievedIngredient = ingredientDao.getById(1);
        assertNotNull(retrievedIngredient);
        assertEquals("Beets", retrievedIngredient.getName());
    }

    /**
     * Update ingredient success.
     */
    @Test
    void updateIngredientSuccess() {
        Ingredient ingredient = ingredientDao.getById(4);
        ingredient.setName("roasted garlic");
        ingredientDao.update(ingredient);

        Ingredient retrievedIngredient = ingredientDao.getById(4);
        assertEquals("roasted garlic", retrievedIngredient.getName());
    }

    /**
     * Insert ingredient success.
     */
    @Test
    void insertIngredientSuccess() {
        Ingredient newIngredient = new Ingredient("Fresh Basil");
        int insertedId = ingredientDao.insert(newIngredient);

        Ingredient retrievedIngredient = ingredientDao.getById(insertedId);
        assertNotNull(retrievedIngredient);
        assertEquals("Fresh Basil", retrievedIngredient.getName());
    }

    /**
     * Delete ingredient success.
     */
    @Test
    void deleteIngredientSuccess() {
        Ingredient newIngredient = new Ingredient("Temp Ingredient");
        int insertedId = ingredientDao.insert(newIngredient);

        Ingredient retrievedIngredient = ingredientDao.getById(insertedId);
        ingredientDao.delete(retrievedIngredient);

        assertNull(ingredientDao.getById(insertedId));
    }

    /**
     * Gets all ingredients.
     */
    @Test
    void getAllIngredients() {
        List<Ingredient> ingredients = ingredientDao.getAll();
        assertNotNull(ingredients);
        assertEquals(32, ingredients.size());
    }

    /**
     * Gets ingredient by property equal.
     */
    @Test
    void getIngredientByPropertyEqual() {
        List<Ingredient> results = ingredientDao.findByPropertyEqual("name", "Sourdough Bread");
        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(25, results.get(0).getId());
    }
}