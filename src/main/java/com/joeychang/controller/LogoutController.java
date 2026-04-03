package com.joeychang.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Logout controller.
 */
@WebServlet(urlPatterns = {"/logout"})
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties props = (Properties) getServletContext().getAttribute("cognitoProperties");

        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        String logoutURL = props.getProperty("logoutURL");
        String clientId = props.getProperty("client.id");
        String returnHomeURL = props.getProperty("returnHomeURL");

        String url = logoutURL + "?client_id=" + clientId + "&logout_uri=" + returnHomeURL;

        resp.sendRedirect(url);
    }
}