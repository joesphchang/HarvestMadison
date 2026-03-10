package com.joeychang.controller;
import com.joeychang.entity.Recipe;
import com.joeychang.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;


/**
 * The type Recipe controller.
 */
@WebServlet(urlPatterns = {
        // Maps to List All
        "/recipes",
        // Maps to Search Results
        "/search",
        // Maps to Single Recipe
        "/recipeDetails"
})
public class RecipeController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RecipeController.class);
    private final String JSP = "/WEB-INF/jsp/recipes/";

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
                    logger.info("Searching for recipes with term: {}", searchTerm);
                    req.setAttribute("recipes", recipeDao.findByPropertyLike("recipeName", searchTerm));
                }
                defaultView = "searchRecipeResults.jsp";
            } else if (path.equals("/recipeDetails")) {
                String id = req.getParameter("id");
                try {
                    int recipeId = Integer.parseInt(id);
                    req.setAttribute("recipe", recipeDao.getById(recipeId));
                } catch (NumberFormatException e) {
                    logger.warn("User provided invalid Recipe ID format: '{}'", id);
                }
                defaultView = "recipeDetails.jsp";
            } else {
                logger.info("Retrieving all recipes");
                req.setAttribute("recipes", recipeDao.getAll());
            }
            req.getRequestDispatcher(JSP + defaultView).forward(req, resp);
        } catch (Exception exception) {
            logger.error("Critical failure in RecipeController at path: {}", path, exception);
            req.setAttribute("errorMessage", "Our chefs are having trouble in the kitchen. Please try again later.");
            req.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(req, resp);
        }
    }
}