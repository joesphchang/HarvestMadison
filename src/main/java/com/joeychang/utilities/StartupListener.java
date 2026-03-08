package com.joeychang.utilities;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebListener
public class StartupListener implements ServletContextListener, PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        try {
            // Load Cognito properties for auth
            Properties cognitoProps = loadProperties("/cognito.properties");
            context.setAttribute("cognitoProperties", cognitoProps);

            // Load Database/API properties for RDS
            Properties dbProps = loadProperties("/database.properties");
            context.setAttribute("databaseProperties", dbProps);

            logger.info("Application properties successfully loaded into ServletContext.");
        } catch (Exception e) {
            logger.error("Startup Failure: Could not load properties. App may malfunction.", e);
        }
    }
}