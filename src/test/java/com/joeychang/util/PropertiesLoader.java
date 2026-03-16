package com.joeychang.util;

import com.joeychang.controller.RecipeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.*;

/**
 * @author Eric Knapp
 *
 */
public interface PropertiesLoader{
    static final Logger logger = LogManager.getLogger(RecipeController.class);
    default Properties loadProperties(String propertiesFilePath) throws Exception {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioException) {
            logger.error("Failed to process IO operation: {}", ioException.getMessage(), ioException);
            throw ioException;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: ", e);
            throw e;
        }
        return properties;
    }
}
