package com.joeychang.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * The type User.
 */
@Entity(name = "User")
@Table(name = "user")
public class User {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "cognito_sub")
    private String cognitoSub;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Recipe> recipes = new ArrayList<>();

    /**
     * Instantiates a new User.
     */
    public User() {}

    /**
     * Instantiates a new User.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param userName  the user name
     * @param email     the email for user
     */
    public User(String firstName, String lastName, String userName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
    }

    /**
     * Add recipe.
     *
     * @param recipe the recipe
     */
    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        recipe.setUser(this);
    }


    /**
     * Remove recipe.
     *
     * @param recipe the recipe
     */
    public void removeRecipe(Recipe recipe) {
        recipes.remove(recipe);
        recipe.setUser(null);
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets cognito sub.
     *
     * @return the cognito sub
     */
    public String getCognitoSub() {
        return cognitoSub;
    }

    /**
     * Sets cognito sub.
     *
     * @param cognitoSub the cognito sub
     */
    public void setCognitoSub(String cognitoSub) {
        this.cognitoSub = cognitoSub;
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

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", cognitoSub='" + cognitoSub + '\'' +
                ", id=" + id +
                ", recipes=" + recipes +
                '}';
    }
}
