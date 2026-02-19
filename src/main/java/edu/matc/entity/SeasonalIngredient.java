package edu.matc.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "SeasonalIngredient")
@Table(name = "seasonal_ingredient")
public class SeasonalIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "s_ingredient_name")
    private String seasonalIngredientName;

    @Column(name = "start_date")
    private int startDate;

    @Column(name = "end_date")
    private int endDate;

    @OneToMany(mappedBy = "seasonalIngredient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Recipe> recipes = new ArrayList<>();

    public SeasonalIngredient() {}

    public SeasonalIngredient(int id, String seasonalIngredientName, int startDate, int endDate) {
        this.id = id;
        this.seasonalIngredientName = seasonalIngredientName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Add recipe.
     *
     * @param recipe the recipe
     */
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        recipe.setSeasonalIngredient(this);
    }

    /**
     * Remove recipe.
     *
     * @param recipe the recipe
     */
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        recipe.setSeasonalIngredient(null);
    }

    /**
     * Gets recipes.
     *
     * @return the recipes
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Sets recipes.
     *
     * @param recipes the recipes
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeasonalIngredientName() {
        return seasonalIngredientName;
    }

    public void setSeasonalIngredientName(String seasonalIngredientName) {
        this.seasonalIngredientName = seasonalIngredientName;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "SeasonalIngredient{" +
                "id=" + id +
                ", seasonalIngredientName='" + seasonalIngredientName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
