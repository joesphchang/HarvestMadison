package com.joeychang.controller;

import com.joeychang.entity.SpoonacularRecipe;
import com.joeychang.service.SpoonacularService;
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
 * Handles Spoonacular-specific pages that pull directly from the external API.
 * - /globalRecipes -> a list of random recipes from Spoonacular
 * - /spoonacularDetails -> detail view for a single Spoonacular recipe by API ID
 */
@WebServlet(urlPatterns = {"/spoonacularDetails", "/globalRecipes"})
public class SpoonacularController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(SpoonacularController.class);

    /**
     * Loads either a list of random global recipes or the detail page for a specific one,
     * depending on the path. API key handling is delegated to SpoonacularService.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        SpoonacularService spoonacularService = new SpoonacularService();

        try {
            if (path.equals("/globalRecipes")) {
                // Pull a random batch of recipes to show on the global recipes page
                List<SpoonacularRecipe> randoms = spoonacularService.getRandomRecipes();
                req.setAttribute("apiRecipes", randoms);
                req.getRequestDispatcher("/jsp/recipes/listOfGlobalRecipes.jsp").forward(req, resp);

            } else if (path.equals("/spoonacularDetails")) {
                // Load a single Spoonacular recipe by its API ID (not our internal ID)
                int id = Integer.parseInt(req.getParameter("apiId"));
                SpoonacularRecipe recipe = spoonacularService.getRecipeById(id);
                req.setAttribute("apiRecipe", recipe);
                req.getRequestDispatcher("/jsp/recipes/spoonacularDetails.jsp").forward(req, resp);
            }

        } catch (Exception e) {
            logger.error("Critical failure in SpoonacularController at path: {}", path, e);
            req.setAttribute("errorMessage", "Our chefs are having trouble in the kitchen.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}