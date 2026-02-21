package edu.matc.persistence;

import edu.matc.entity.SeasonalIngredient;
import edu.matc.util.Database;
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

}
