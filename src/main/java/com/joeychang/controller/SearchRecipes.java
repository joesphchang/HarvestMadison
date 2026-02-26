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


@WebServlet(
        urlPatterns = {"/searchRecipes"}
)
public class SearchRecipes extends HttpServlet {

    GenericDao<Recipe> recipeDao;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        recipeDao = new GenericDao<>(Recipe.class);

        if ("search".equals(req.getParameter("submit"))) {
            String searchTerm = req.getParameter("searchTerm");
            req.setAttribute("recipes", recipeDao.findByPropertyEqual("recipeName", searchTerm));
        } else {
            req.setAttribute("recipes", recipeDao.getAll());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchRecipeResults.jsp");
        dispatcher.forward(req, resp);
    }
}
