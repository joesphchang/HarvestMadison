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


@WebServlet(urlPatterns =
        {
                "/searchRecipes",
                "/listOfRecipes"
        })
public class SearchRecipes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Recipe> recipeDao = new GenericDao<>(Recipe.class);
        String destination;

        String searchTerm = req.getParameter("searchTerm");
        String submitAction = req.getParameter("submit");

        if ("search".equals(submitAction) && searchTerm != null && !searchTerm.trim().isEmpty()) {
            java.util.Map<String, Object> propertyMap = new java.util.HashMap<>();
            propertyMap.put("recipeName", searchTerm);

            req.setAttribute("recipes", recipeDao.findByPropertyLike(propertyMap));
            destination = "/searchRecipeResults.jsp";

        } else {
            req.setAttribute("recipes", recipeDao.getAll());
            destination = "/listOfRecipes.jsp";
        }

        req.getRequestDispatcher(destination).forward(req, resp);
    }
}
