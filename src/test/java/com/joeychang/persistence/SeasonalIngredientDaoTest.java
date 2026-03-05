package com.joeychang.persistence;

import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Seasonal ingredient dao test.
 */
public class SeasonalIngredientDaoTest {

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
        seasonalIngredientDao = new GenericDao<>(SeasonalIngredient.class);
    }

    /**
     * Gets all seasonal ingredients.
     */
    @Test
    void getAllSeasonalIngredients() {
        List<SeasonalIngredient> seasonalIngredients = seasonalIngredientDao.getAll();
        assertEquals(12, seasonalIngredients.size());
    }

    /**
     * Gets seasonal ingredient by id success.
     */
    @Test
    void getSeasonalIngredientByIdSuccess() {
        SeasonalIngredient retrievedIngredient = (SeasonalIngredient)seasonalIngredientDao.getById(8);
        assertNotNull(retrievedIngredient);
        assertEquals("Sweet Corn", retrievedIngredient.getSeasonalIngredientName());
    }

    /**
     * Gets by property by equal.
     */
    @Test
    void getByPropertyByEqual() {
      List<SeasonalIngredient> seasonalIngredients = seasonalIngredientDao.findByPropertyEqual("seasonalIngredientName", "Microgreens");
      assertEquals(1, seasonalIngredients.size());
      assertEquals(3, seasonalIngredients.get(0).getId());
    }

    /**
     * Gets beets by start month.
     */
    @Test
    void getBeetsByStartMonth() {
        int december = 12;

        List<SeasonalIngredient> results = seasonalIngredientDao.findByPropertyEqual("startDate", 12);
        assertEquals(1, results.size());
        assertEquals("Beets", results.get(0).getSeasonalIngredientName());
        assertEquals(1, results.get(0).getEndDate());
    }

    /**
     * Gets ingredients by current month success.
     */
    @Test
    void getIngredientsByCurrentMonthSuccess() {
        int testCurrentMonth = 10;

        List<SeasonalIngredient> seasonalIngredients = seasonalIngredientDao.findByPropertyEqual("startDate", testCurrentMonth);
        assertFalse(seasonalIngredients.isEmpty());
        assertEquals("Pumpkins", seasonalIngredients.get(0).getSeasonalIngredientName());
    }
}
