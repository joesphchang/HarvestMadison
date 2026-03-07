package com.joeychang.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Seasonal ingredient.
 */
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

    @Column(name = "image_url")
    private String imageURL;

    @OneToMany(mappedBy = "seasonalIngredient", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Recipe> recipes = new ArrayList<>();

    /**
     * Instantiates a new Seasonal ingredient.
     */
    public SeasonalIngredient() {}

    /**
     * Instantiates a new Seasonal ingredient.
     *
     * @param id                     the id
     * @param seasonalIngredientName the seasonal ingredient name
     * @param startDate              the start date
     * @param endDate                the end date
     */
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


    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets seasonal ingredient name.
     *
     * @return the seasonal ingredient name
     */
    public String getSeasonalIngredientName() {
        return seasonalIngredientName;
    }

    /**
     * Sets seasonal ingredient name.
     *
     * @param seasonalIngredientName the seasonal ingredient name
     */
    public void setSeasonalIngredientName(String seasonalIngredientName) {
        this.seasonalIngredientName = seasonalIngredientName;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public int getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public int getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets image url.
     *
     * @return the image url
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets image url.
     *
     * @param imageURL the image url
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "SeasonalIngredient{" +
                "id=" + id +
                ", seasonalIngredientName='" + seasonalIngredientName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", imageURL='" + imageURL + '\'' +
                ", recipes=" + recipes +
                '}';
    }
}
