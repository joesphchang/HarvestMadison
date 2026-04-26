package com.joeychang.controller;

import com.joeychang.persistence.SpoonacularDao;
import com.joeychang.entity.SpoonacularRecipe;
import com.joeychang.utilities.PropertiesLoader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * The type Spoonacular controller.
 */
@WebServlet(urlPatterns = {"/spoonacularDetails", "/globalRecipes"})
public class SpoonacularController extends HttpServlet implements PropertiesLoader {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        Properties properties = null;
        try {
            properties = loadProperties("/spoonacular.properties");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String apiKey = properties.getProperty("spoonacular.api.key");
        SpoonacularDao dao = new SpoonacularDao();

        if (path.equals("/globalRecipes")) {
            List<SpoonacularRecipe> randoms = dao.getRandomRecipes(apiKey);
            req.setAttribute("apiRecipes", randoms);
            req.getRequestDispatcher("/jsp/recipes/listOfGlobalRecipes.jsp").forward(req, resp);
        } else if (path.equals("/spoonacularDetails")) {
            int id = Integer.parseInt(req.getParameter("apiId"));
            SpoonacularRecipe recipe = dao.getSpoonacularRecipeById(id, apiKey);
            req.setAttribute("apiRecipe", recipe);
            req.getRequestDispatcher("/jsp/recipes/spoonacularDetails.jsp").forward(req, resp);
        }
    }
}