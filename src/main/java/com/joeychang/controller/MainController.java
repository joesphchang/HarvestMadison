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

@WebServlet(urlPatterns = {"/about", "/contact", "/home"})
public class MainController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        String dest = "/WEB-INF/jsp/index.jsp";

        if (path.equals("/about")) {
            dest = "/WEB-INF/jsp/about.jsp";
        }

        req.getRequestDispatcher(dest).forward(req, resp);
    }
}
