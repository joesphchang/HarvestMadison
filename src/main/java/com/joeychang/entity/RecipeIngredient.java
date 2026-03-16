package com.joeychang.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * The type Recipe ingredient.
 */
@Entity(name = "RecipeIngredient")
@Table(name = "recipe_ingredient")
public class RecipeIngredient {

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit")
    private String unit;

    @ManyToMany
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToMany
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    /**
     * Instantiates a new Recipe ingredient.
     */
    public RecipeIngredient() {
    }

    /**
     * Instantiates a new Recipe ingredient.
     *
     * @param quantity   the quantity
     * @param unit       the unit
     * @param recipe     the recipe
     * @param ingredient the ingredient
     */
    public RecipeIngredient(int quantity, String unit, Recipe recipe, Ingredient ingredient) {
        this.quantity = quantity;
        this.unit = unit;
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Gets recipe.
     *
     * @return the recipe
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * Sets recipe.
     *
     * @param recipe the recipe
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    /**
     * Gets ingredient.
     *
     * @return the ingredient
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Sets ingredient.
     *
     * @param ingredient the ingredient
     */
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "RecipeIngredient{" +
                "quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", recipe=" + recipe +
                ", ingredient=" + ingredient +
                '}';
    }
}
