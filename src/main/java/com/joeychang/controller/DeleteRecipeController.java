package com.joeychang.controller;

import com.joeychang.entity.Recipe;
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

/**
 * Handles recipe deletion. POST only — there's no page to load here.
 * Always redirects back to /recipes when done, whether the delete worked or not.
 */
@WebServlet(urlPatterns = {"/deleteRecipe"})
public class DeleteRecipeController extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(DeleteRecipeController.class);

    /**
     * Deletes a recipe by ID, but only if the logged-in user actually owns it.
     * If someone tries to delete a recipe that isn't theirs, we log it and move on.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User sessionUser = (User) req.getSession().getAttribute("user");
        if (sessionUser == null) {
            resp.sendRedirect("login");
            return;
        }

        try {
            int deleteId = Integer.parseInt(req.getParameter("recipeId"));
            GenericDao<Recipe> recipeDao = new GenericDao<>(Recipe.class);
            Recipe recipeToDelete = recipeDao.getById(deleteId);

            if (recipeToDelete != null && recipeToDelete.getUser().getId() == sessionUser.getId()) {
                recipeDao.delete(recipeToDelete);
                logger.info("Recipe {} deleted by user {}", deleteId, sessionUser.getId());
            } else {
                // Recipe either doesn't exist or belongs to someone else
                logger.warn("User {} attempted to delete recipe {} without permission", sessionUser.getId(), deleteId);
            }

        } catch (Exception e) {
            logger.error("Error deleting recipe", e);
        }

        resp.sendRedirect("recipes");
    }
}