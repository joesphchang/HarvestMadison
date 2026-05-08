package com.joeychang.controller;

import com.joeychang.entity.Recipe;
import com.joeychang.entity.SpoonacularRecipe;
import com.joeychang.persistence.GenericDao;
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
 * Handles read-only recipe operations, listing all recipes, searching, and viewing a recipe's details.
 * Nothing here modifies any data — that's handled by Add, Edit, and Delete controllers.
 */
@WebServlet(urlPatterns = {
        "/recipes",
        "/search",
        "/recipeDetails"
})
public class RecipeController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RecipeController.class);
    private final String JSP = "/jsp/recipes/";

    /**
     * Routes to the right view based on the path:
     * - /recipes -> list of all recipes
     * - /search -> search results from both the database and Spoonacular
     * - /recipeDetails -> single recipe detail page
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Recipe> recipeDao = new GenericDao<>(Recipe.class);
        String path = req.getServletPath();
        String defaultView = "listOfRecipes.jsp";

        logger.debug("Entering doGet for path: {}", path);

        try {
            if (path.equals("/search")) {
                String searchTerm = req.getParameter("searchTerm");

                if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                    // Search our own database first, then hit the Spoonacular API
                    req.setAttribute("recipes", recipeDao.findByPropertyLike("recipeName", searchTerm));

                    SpoonacularService spoonacularService = new SpoonacularService();
                    List<SpoonacularRecipe> apiResults = spoonacularService.searchRecipes(searchTerm);
                    req.setAttribute("apiRecipes", apiResults);
                }

                defaultView = "searchRecipeResults.jsp";

            } else if (path.equals("/recipeDetails")) {
                String id = req.getParameter("id");
                int recipeId = Integer.parseInt(id);
                req.setAttribute("recipe", recipeDao.getById(recipeId));
                defaultView = "recipeDetails.jsp";

            } else {
                // /recipes — just load everything
                req.setAttribute("recipes", recipeDao.getAll());
            }

            req.getRequestDispatcher(JSP + defaultView).forward(req, resp);

        } catch (Exception e) {
            logger.error("Critical failure in RecipeController at path: {}", path, e);
            req.setAttribute("errorMessage", "Our chefs are having trouble in the kitchen.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }
}