package com.joeychang.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The type Spoonacular search response.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SpoonacularSearchResponse {

    @JsonProperty("results")
    private List<SpoonacularRecipe> results;

    @JsonProperty("recipes")
    private List<SpoonacularRecipe> recipes;

    /**
     * Gets results.
     *
     * @return the results
     */
    public List<SpoonacularRecipe> getResults() { return results; }

    /**
     * Sets results.
     *
     * @param results the results
     */
    public void setResults(List<SpoonacularRecipe> results) { this.results = results; }

    /**
     * Gets recipes.
     *
     * @return the recipes
     */
    public List<SpoonacularRecipe> getRecipes() { return recipes; }

    /**
     * Sets recipes.
     *
     * @param recipes the recipes
     */
    public void setRecipes(List<SpoonacularRecipe> recipes) { this.recipes = recipes; }
}