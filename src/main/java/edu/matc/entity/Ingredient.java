package edu.matc.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/**
 * The type Ingredient.
 */
@Entity(name = "Ingredient")
@Table(name = "ingredient")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "ingredient_name")
    private String ingredientName;

    /**
     * Instantiates a new Ingredient.
     */
    public Ingredient() {}

    /**
     * Instantiates a new Ingredient.
     *
     * @param id             the id
     * @param ingredientName the ingredient name
     * @param description    the description
     */
    public Ingredient(int id, String ingredientName, String description) {
        this.id = id;
        this.ingredientName = ingredientName;
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
     * Gets ingredient name.
     *
     * @return the ingredient name
     */
    public String getIngredientName() {
        return ingredientName;
    }

    /**
     * Sets ingredient name.
     *
     * @param ingredientName the ingredient name
     */
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", ingredientName='" + ingredientName + '\'' +
                '}';
    }
}
