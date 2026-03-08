package com.joeychang.controller;

import com.joeychang.utilities.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * The type Login.
 */
@WebServlet(
        urlPatterns = {"/login"}
)
/**
 * Begins the authentication process using AWS Cognito
 *
 */
public class Login extends HttpServlet implements PropertiesLoader {
    /**
     * The Properties.
     */
    Properties properties;
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The constant CLIENT_ID.
     */
    public static String CLIENT_ID;
    /**
     * The constant LOGIN_URL.
     */
    public static String LOGIN_URL;
    /**
     * The constant REDIRECT_URL.
     */
    public static String REDIRECT_URL;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties props = (Properties) getServletContext().getAttribute("cognitoProperties");

        String clientId = props.getProperty("client.id");
        String loginUrl = props.getProperty("loginURL");
        String redirectUri = props.getProperty("redirectURL");

        if (props.isEmpty()) {
            logger.error("Cognito properties not found in ServletContext.");
            resp.sendRedirect("error.jsp");
            return;
        }

        String url = loginUrl + "?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri;
        resp.sendRedirect(url);
    }
}
