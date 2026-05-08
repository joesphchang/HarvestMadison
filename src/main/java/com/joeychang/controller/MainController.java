package com.joeychang.controller;

import com.joeychang.entity.Recipe;
import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.entity.SpoonacularRecipe;
import com.joeychang.persistence.GenericDao;
import com.joeychang.service.SpoonacularService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Handles the home page and about page.
 * The home page figures out what's in season based on the current month
 * and loads both local and Spoonacular recipes for that ingredient.
 */
@WebServlet(urlPatterns = {"/home", "/about", ""})
public class MainController extends HttpServlet {

    private static final int MAX_HOME_RECIPES = 3;

    /**
     * Loads up the about page.
     * We fetch data from the seasonal ingredient collection so the JSP can hold
     * seasonal ingredient data and render recipes and the seasonal ingredient of the month.
     *
     * This also allows us to grab data from Spoonacular to load up global recipes using
     * the seasonal ingredient.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        // About page is simple. No seasonal logic needed
        if ("/about".equals(path)) {
            req.getRequestDispatcher("/about.jsp").forward(req, resp);
            return;
        }

        GenericDao<SeasonalIngredient> seasonalDao = new GenericDao<>(SeasonalIngredient.class);
        SpoonacularService spoonacularService = new SpoonacularService();

        // Grab the current month (1–12) to match against seasonal ingredient date ranges
        int currentMonth = java.time.LocalDate.now().getMonthValue();

        // Ask the database for a seasonal ingredient whose range covers the current month.
        // This handles normal ranges. The year-wrap case
        // needs a second query since a single range check can't handle the rollover.
        SeasonalIngredient currentSeason = null;
        List<SeasonalIngredient> normalMatches = seasonalDao.findByPropertyBetween("startDate", "endDate", currentMonth);

        if (!normalMatches.isEmpty()) {
            currentSeason = normalMatches.get(0);
        } else {
            // Check for ingredients that wrap around the new year.
            // Those have startDate > endDate
            List<SeasonalIngredient> wrapMatches = seasonalDao.findWraparoundByMonth(currentMonth);
            if (!wrapMatches.isEmpty()) {
                currentSeason = wrapMatches.get(0);
            }
        }

        // If we found a seasonal ingredient, load its recipes and pull global ones from the API.
        // Cap both lists at MAX_HOME_RECIPES so the home page doesn't get flooded.
        // If nothing matched or the API call fails, the home page still loads, just without that section.
        if (currentSeason != null) {
            List<Recipe> seasonalRecipes = currentSeason.getRecipes();
            List<SpoonacularRecipe> globalRecipes = spoonacularService.searchRecipes(currentSeason.getSeasonalIngredientName());

            req.setAttribute("seasonalIngredient", currentSeason);
            req.setAttribute("seasonalRecipes", seasonalRecipes.subList(0, Math.min(MAX_HOME_RECIPES, seasonalRecipes.size())));
            req.setAttribute("globalRecipes", globalRecipes.subList(0, Math.min(MAX_HOME_RECIPES, globalRecipes.size())));
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}