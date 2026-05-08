package com.joeychang.controller;

import com.joeychang.entity.*;
import com.joeychang.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Handles everything related to adding a new recipe.
 * GET loads the empty form, POST either adds an ingredient row or saves the recipe.
 */
@WebServlet(urlPatterns = {"/addRecipe"})
public class AddRecipeController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(AddRecipeController.class);
    private final String JSP = "/jsp/recipes/";

    /**
     * Loads the add recipe form with a fresh set of ingredient rows and
     * the list of seasonal ingredients for the dropdown.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            GenericDao<SeasonalIngredient> ingredientDao = new GenericDao<>(SeasonalIngredient.class);
            req.setAttribute("ingredients", ingredientDao.getAll());
            req.setAttribute("ingredientRowCount", 3);
            req.getRequestDispatcher(JSP + "addRecipeForm.jsp").forward(req, resp);
        } catch (Exception e) {
            logger.error("Failed to load add recipe form", e);
            req.setAttribute("errorMessage", "Our chefs are having trouble in the kitchen.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    /**
     * Two things can happen here:
     * 1. User clicked "+ Add Ingredient Row" — re-render the form with one more row, preserving what they typed.
     * 2. User submitted the form — save the new recipe and its ingredients, then redirect to /recipes.
     *
     * If the user isn't logged in, they get kicked to the login page.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        if (sessionUser == null) {
            resp.sendRedirect("login");
            return;
        }

        // User wants another ingredient row — re-render the form without saving anything
        if ("addRow".equals(req.getParameter("addIngredient"))) {
            int currentCount = 3;
            try {
                currentCount = Integer.parseInt(req.getParameter("ingredientRowCount"));
            } catch (NumberFormatException e) {
                // keep default
            }

            // Carry over everything the user already typed so they don't lose their work
            req.setAttribute("ingredientRowCount", currentCount + 1);
            req.setAttribute("quantities", req.getParameterValues("quantity"));
            req.setAttribute("units", req.getParameterValues("unit"));
            req.setAttribute("ingredientNames", req.getParameterValues("ingredientName"));
            req.setAttribute("recipeName", req.getParameter("recipeName"));
            req.setAttribute("imageURL", req.getParameter("image-url"));
            req.setAttribute("description", req.getParameter("description"));

            GenericDao<SeasonalIngredient> seasonalIngredientDao = new GenericDao<>(SeasonalIngredient.class);
            req.setAttribute("ingredients", seasonalIngredientDao.getAll());

            req.getRequestDispatcher(JSP + "addRecipeForm.jsp").forward(req, resp);
            return;
        }

        // Save the new recipe
        try {
            String recipeName = req.getParameter("recipeName");
            String imageURL = req.getParameter("image-url");
            String description = req.getParameter("description");
            int seasonalId = Integer.parseInt(req.getParameter("ingredientId"));

            String[] quantities = req.getParameterValues("quantity");
            String[] units = req.getParameterValues("unit");
            String[] ingredientNames = req.getParameterValues("ingredientName");

            GenericDao<Recipe> recipeDao = new GenericDao<>(Recipe.class);
            GenericDao<SeasonalIngredient> seasonalDao = new GenericDao<>(SeasonalIngredient.class);
            GenericDao<Ingredient> ingredientDao = new GenericDao<>(Ingredient.class);
            GenericDao<RecipeIngredient> riDao = new GenericDao<>(RecipeIngredient.class);

            Recipe recipe = new Recipe();
            recipe.setUser(sessionUser);
            recipe.setRecipeName(recipeName);
            recipe.setImageURL(imageURL);
            recipe.setDescription(description);
            recipe.setSeasonalIngredient(seasonalDao.getById(seasonalId));

            recipeDao.insert(recipe);

            // Loop through ingredient rows — skip any that were left blank
            if (ingredientNames != null) {
                for (int i = 0; i < ingredientNames.length; i++) {
                    String name = ingredientNames[i].trim();
                    if (!name.isEmpty()) {
                        // Reuse an existing ingredient if we already have it, otherwise create a new one
                        List<Ingredient> existing = ingredientDao.findByPropertyEqual("name", name);
                        Ingredient baseIngredient;
                        if (existing.isEmpty()) {
                            baseIngredient = new Ingredient();
                            baseIngredient.setName(name);
                            ingredientDao.insert(baseIngredient);
                        } else {
                            baseIngredient = existing.get(0);
                        }

                        // Link the ingredient to this recipe with its quantity and unit
                        RecipeIngredient ri = new RecipeIngredient();
                        ri.setRecipe(recipe);
                        ri.setIngredient(baseIngredient);
                        ri.setUnit(units[i]);
                        try {
                            ri.setQuantity(Integer.parseInt(quantities[i]));
                        } catch (NumberFormatException nfe) {
                            // default to 1 if they left it blank or typed something weird
                            ri.setQuantity(1);
                        }
                        riDao.insert(ri);
                    }
                }
            }

            logger.info("New recipe '{}' created by user {}", recipeName, sessionUser.getId());
            resp.sendRedirect("recipes");

        } catch (Exception e) {
            logger.error("Failed to save new recipe", e);
            req.setAttribute("errorMessage", "Our chefs are having trouble in the kitchen.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}