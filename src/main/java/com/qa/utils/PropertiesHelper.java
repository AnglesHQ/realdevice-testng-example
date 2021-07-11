package com.qa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertiesHelper {
    private static final Logger logger = LogManager.getLogger(PropertiesHelper.class);

    public static java.util.Properties loadPropertiesFile(String propertiesFilePath) {
        java.util.Properties properties = new java.util.Properties();
        try {
            properties.load(Thread.currentThread().getClass().getResourceAsStream(propertiesFilePath));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return properties;
    }
}
