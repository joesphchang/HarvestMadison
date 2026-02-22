package com.joeychang.persistence;

import com.joeychang.entity.SeasonalIngredient;

public class SeasonalIngredientDao extends GenericDao<SeasonalIngredient> {

    public SeasonalIngredientDao() {
        super(SeasonalIngredient.class);
    }

    public SeasonalIngredient getIngredientByMonth(int month) {
        return null;
    }
}
