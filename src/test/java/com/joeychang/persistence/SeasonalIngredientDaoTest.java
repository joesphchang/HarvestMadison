package com.joeychang.persistence;

import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeasonalIngredientDaoTest {

    GenericDao<SeasonalIngredient> seasonalIngredientDao;

    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        seasonalIngredientDao = new GenericDao<>(SeasonalIngredient.class);
    }

    @Test
    void getAllSeasonalIngredients() {
        List<SeasonalIngredient> seasonalIngredients = seasonalIngredientDao.getAll();
        assertEquals(12, seasonalIngredients.size());
    }

    @Test
    void getSeasonalIngredientByIdSuccess() {
        SeasonalIngredient retrievedIngredient = (SeasonalIngredient)seasonalIngredientDao.getById(8);
        assertNotNull(retrievedIngredient);
        assertEquals("Sweet Corn", retrievedIngredient.getSeasonalIngredientName());
    }

    @Test
    void getByPropertyByEqual() {
      List<SeasonalIngredient> seasonalIngredients = seasonalIngredientDao.findByPropertyEqual("seasonalIngredientName", "Microgreens");
      assertEquals(1, seasonalIngredients.size());
      assertEquals(3, seasonalIngredients.get(0).getId());

    }
}
