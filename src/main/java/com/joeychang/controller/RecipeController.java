package com.joeychang.controller;
import com.joeychang.entity.Recipe;
import com.joeychang.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(urlPatterns = {
        // Maps to List All
        "/recipes",
        // Maps to Search Results
        "/search",
        // Maps to Single Recipe
        "/recipeDetails"
})
public class RecipeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Recipe> recipeDao = new GenericDao<>(Recipe.class);
        String path = req.getServletPath();
        String destination = "/WEB-INF/jsp/recipes/listOfRecipes.jsp";

        if (path.equals("/search")) {
            String searchTerm = req.getParameter("searchTerm");
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                java.util.Map<String, Object> propertyMap = new java.util.HashMap<>();
                propertyMap.put("recipeName", searchTerm);
                req.setAttribute("recipes", recipeDao.findByPropertyLike(propertyMap));
            }
            destination = "/WEB-INF/jsp/recipes/searchRecipeResults.jsp";

        } else if (path.equals("/recipeDetails")) {
            String id = req.getParameter("id");
            if (id != null) {
                int recipeId = Integer.parseInt(id);
                req.setAttribute("recipe", recipeDao.getById(recipeId));
            }
            destination = "/WEB-INF/jsp/recipes/recipeDetails.jsp";

        } else {
            req.setAttribute("recipes", recipeDao.getAll());
        }

        req.getRequestDispatcher(destination).forward(req, resp);
    }
}