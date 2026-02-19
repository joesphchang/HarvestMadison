package edu.matc.persistence;

import edu.matc.entity.SeasonalIngredient;

public class SeasonalIngredientDao extends GenericDao<SeasonalIngredient> {

    public SeasonalIngredientDao() {
        super(SeasonalIngredient.class);
    }

    public SeasonalIngredient getIngredientByMonth(int month) {

    }
}
