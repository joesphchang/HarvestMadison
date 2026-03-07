package com.joeychang.controller;

import com.joeychang.entity.Recipe;
import com.joeychang.entity.SeasonalIngredient;
import com.joeychang.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home", "/about"})
public class MainController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        // Handling "About" page logic to keep home page seperate
        if ("/about".equals(path)) {
            req.getRequestDispatcher("/about.jsp").forward(req, resp);
            return;
        }

        // Grabbing the Seasonal Ingredient Dao
        GenericDao<SeasonalIngredient> seasonalDao = new GenericDao<>(SeasonalIngredient.class);
        // Using java.time.LocalDate.now() to grab the month value
        int currentMonth = java.time.LocalDate.now().getMonthValue();

        // GetAll from GenericDao
        List<SeasonalIngredient> allSeasonal = seasonalDao.getAll();
        // Setting currentSeason to null
        SeasonalIngredient currentSeason = null;

        // looping through seasonal ingredients and matching up the month to the current month comparing
        // both start date (ex: 1/1/2026) to end date (ex: 1/31/2026)
        for (SeasonalIngredient seasonal : allSeasonal) {
            if (seasonal.getStartDate() > seasonal.getEndDate()) {
                if (currentMonth >= seasonal.getStartDate() || currentMonth < seasonal.getEndDate()) {
                    currentSeason = seasonal;
                    break;
                }
            } else if (currentMonth >= seasonal.getStartDate() && currentMonth < seasonal.getEndDate()) {
                currentSeason = seasonal;
                break;
            }
        }

        // IF fetch fails, still go to /home page
        if (currentSeason != null) {
            req.setAttribute("seasonalIngredient", currentSeason);
            req.setAttribute("seasonalRecipes", currentSeason.getRecipes());
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
