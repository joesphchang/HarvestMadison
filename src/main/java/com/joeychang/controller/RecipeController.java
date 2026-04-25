package com.joeychang.controller;

import com.joeychang.entity.Ingredient;
import com.joeychang.entity.Recipe;
import com.joeychang.entity.RecipeIngredient;
import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.entity.User;
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
 * The type Recipe controller.
 */
@WebServlet(urlPatterns = {
        "/recipes",
        "/search",
        "/recipeDetails",
        "/addRecipe",
        "/editRecipe",
        "/saveRecipe",
        "/deleteRecipe"
})
public class RecipeController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(RecipeController.class);
    private final String JSP = "/jsp/recipes/";

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
                    req.setAttribute("recipes", recipeDao.findByPropertyLike("recipeName", searchTerm));
                }
                defaultView = "searchRecipeResults.jsp";

            } else if (path.equals("/recipeDetails")) {
                String id = req.getParameter("id");
                int recipeId = Integer.parseInt(id);
                req.setAttribute("recipe", recipeDao.getById(recipeId));
                defaultView = "recipeDetails.jsp";

            } else if (path.equals("/addRecipe")) {
                GenericDao<SeasonalIngredient> ingredientDao = new GenericDao<>(SeasonalIngredient.class);
                req.setAttribute("ingredients", ingredientDao.getAll());
                defaultView = "recipeForm.jsp";

            } else if (path.equals("/editRecipe")) {
                String id = req.getParameter("id");
                int recipeId = Integer.parseInt(id);
                Recipe recipeToEdit = recipeDao.getById(recipeId);
                User sessionUser = (User) req.getSession().getAttribute("user");

                if (recipeToEdit != null && sessionUser != null &&
                        recipeToEdit.getUser().getId() == sessionUser.getId()) {
                    req.setAttribute("recipe", recipeToEdit);
                    GenericDao<SeasonalIngredient> ingredientDao = new GenericDao<>(SeasonalIngredient.class);
                    req.setAttribute("ingredients", ingredientDao.getAll());
                    defaultView = "recipeForm.jsp";
                } else {
                    resp.sendRedirect("recipes");
                    return;
                }
            } else {
                req.setAttribute("recipes", recipeDao.getAll());
            }
            req.getRequestDispatcher(JSP + defaultView).forward(req, resp);

        } catch (Exception exception) {
            logger.error("Critical failure in RecipeController at path: {}", path, exception);
            req.setAttribute("errorMessage", "Our chefs are having trouble in the kitchen.");
            req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User sessionUser = (User) request.getSession().getAttribute("user");
        String path = request.getServletPath();
        if (sessionUser == null) {
            response.sendRedirect("login");
            return;
        }

        // Delete a Recipe
        if (path.equals("/deleteRecipe")) {
            try {
                int deleteId = Integer.parseInt(request.getParameter("recipeId"));
                GenericDao<Recipe> recipeDao = new GenericDao<>(Recipe.class);
                Recipe recipeToDelete = recipeDao.getById(deleteId);

                if (recipeToDelete != null && recipeToDelete.getUser().getId() == sessionUser.getId()) {
                    recipeDao.delete(recipeToDelete);
                    logger.info("Recipe {} deleted by user {}", deleteId, sessionUser.getId());
                }
            } catch (Exception e) {
                logger.error("Error deleting recipe", e);
            }
            response.sendRedirect("recipes");
            return;
        }

        String recipeId = request.getParameter("recipeId");
        String recipeName = request.getParameter("recipeName");
        String imageURL = request.getParameter("image-url");
        String description = request.getParameter("description");
        int seasonalId = Integer.parseInt(request.getParameter("ingredientId"));

        String[] quantities = request.getParameterValues("quantity");
        String[] units = request.getParameterValues("unit");
        String[] ingredientNames = request.getParameterValues("ingredientName");

        GenericDao<Recipe> recipeDao = new GenericDao<>(Recipe.class);
        GenericDao<SeasonalIngredient> seasonalDao = new GenericDao<>(SeasonalIngredient.class);
        GenericDao<Ingredient> ingredientDao = new GenericDao<>(Ingredient.class);
        GenericDao<RecipeIngredient> riDao = new GenericDao<>(RecipeIngredient.class);

        Recipe recipe;
        boolean isUpdate = (recipeId != null && !recipeId.isEmpty());

        // Update an existing recipe
        if (!isUpdate) {
            recipe = new Recipe();
            recipe.setUser(sessionUser);
        } else {
            recipe = recipeDao.getById(Integer.parseInt(recipeId));
            if (recipe.getUser().getId() != sessionUser.getId()) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }

            for (RecipeIngredient oldRi : recipe.getRecipeIngredients()) {
                riDao.delete(oldRi);
            }
        }

        recipe.setRecipeName(recipeName);
        recipe.setImageURL(imageURL);
        recipe.setDescription(description);
        recipe.setSeasonalIngredient(seasonalDao.getById(seasonalId));

        if (!isUpdate) {
            recipeDao.insert(recipe);
        } else {
            recipeDao.update(recipe);
        }

        // add a recipe to database
        if (ingredientNames != null) {
            for (int i = 0; i < ingredientNames.length; i++) {
                String name = ingredientNames[i].trim();
                if (!name.isEmpty()) {
                    List<Ingredient> existing = ingredientDao.findByPropertyEqual("name", name);
                    Ingredient baseIngredient;
                    if (existing.isEmpty()) {
                        baseIngredient = new Ingredient();
                        baseIngredient.setName(name);
                        ingredientDao.insert(baseIngredient);
                    } else {
                        baseIngredient = existing.get(0);
                    }

                    RecipeIngredient ri = new RecipeIngredient();
                    ri.setRecipe(recipe);
                    ri.setIngredient(baseIngredient);
                    ri.setUnit(units[i]);
                    try {
                        ri.setQuantity(Integer.parseInt(quantities[i]));
                    } catch (NumberFormatException nfe) {
                        ri.setQuantity(1);
                    }
                    riDao.insert(ri);
                }
            }
        }

        response.sendRedirect("recipes");
    }
}