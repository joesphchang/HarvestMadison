package com.joeychang.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Recipe.
 */
@Entity(name = "Recipe")
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "recipe_name")
    private String recipeName;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on", insertable = false, updatable = false)
    private Timestamp createdOn;

    @Column(name = "ingredients_text")
    private String ingredientsText;

    @Column(name = "image_url")
    private String imageURL;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "seasonal_ingredient_id")
    private SeasonalIngredient seasonalIngredient;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RecipeIngredient> recipeIngredients = new HashSet<>();

    /**
     * Instantiates a new Recipe.
     */
    public Recipe() {
    }


    /**
     * Instantiates a new Recipe.
     *
     * @param recipeName         the recipe name
     * @param description        the description
     * @param ingredientsText    the ingredients text
     * @param imageURL           the image url
     * @param user               the user
     * @param seasonalIngredient the seasonal ingredient
     */
    public Recipe(String recipeName, String description, String ingredientsText, String imageURL, User user, SeasonalIngredient seasonalIngredient) {
        this.recipeName = recipeName;
        this.description = description;
        this.ingredientsText = ingredientsText;
        this.imageURL = imageURL;
        this.user = user;
        this.seasonalIngredient = seasonalIngredient;
    }

    /**
     * Gets created on.
     *
     * @return the created on
     */
    public Timestamp getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets created on.
     *
     * @param createdOn the created on
     */
    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets recipe name.
     *
     * @return the recipe name
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Sets recipe name.
     *
     * @param recipeName the recipe name
     */
    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
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
     * Gets ingredients text.
     *
     * @return the ingredients text
     */
    public String getIngredientsText() {
        return ingredientsText;
    }

    /**
     * Sets ingredients text.
     *
     * @param ingredientsText the ingredients text
     */
    public void setIngredientsText(String ingredientsText) {
        this.ingredientsText = ingredientsText;
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

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets seasonal ingredient.
     *
     * @return the seasonal ingredient
     */
    public SeasonalIngredient getSeasonalIngredient() {
        return seasonalIngredient;
    }

    /**
     * Sets seasonal ingredient.
     *
     * @param seasonalIngredient the seasonal ingredient
     */
    public void setSeasonalIngredient(SeasonalIngredient seasonalIngredient) {
        this.seasonalIngredient = seasonalIngredient;
    }


    /**
     * Gets recipe ingredients.
     *
     * @return the recipe ingredients
     */
    public Set<RecipeIngredient> getRecipeIngredients() {
        return recipeIngredients;
    }

    /**
     * Sets recipe ingredients.
     *
     * @param recipeIngredients the recipe ingredients
     */
    public void setRecipeIngredients(Set<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    /**
     * Add recipe ingredient.
     *
     * @param recipeIngredient the recipe ingredient
     */
    public void addRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.add(recipeIngredient);
        recipeIngredient.setRecipe(this);
    }

    /**
     * Remove recipe ingredient.
     *
     * @param recipeIngredient the recipe ingredient
     */
    public void removeRecipeIngredient(RecipeIngredient recipeIngredient) {
        recipeIngredients.remove(recipeIngredient);
        recipeIngredient.setRecipe(null);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipeName='" + recipeName + '\'' +
                ", description='" + description + '\'' +
                ", createdOn=" + createdOn +
                ", ingredientsText='" + ingredientsText + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", user=" + user +
                ", seasonalIngredient=" + seasonalIngredient +
                '}';
    }
}
