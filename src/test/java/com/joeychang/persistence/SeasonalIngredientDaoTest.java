package com.joeychang.persistence;

import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.util.Database;
import org.junit.jupiter.api.BeforeEach;

public class SeasonalIngredientDaoTest {

    GenericDao<SeasonalIngredient> seasonalIngredientDao;


    @BeforeEach
    void setUp() {
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        seasonalIngredientDao = new GenericDao<>(SeasonalIngredient.class);
    }



}
