package com.qa.utils;

import org.apache.log4j.Logger;
import org.testng.TestNGException;

public class Reporter {

    private static Logger logger = Logger.getLogger("Reporter");

    public static void debug(String message) {
        /* Add your thread id to the logger to highlight which thread is doing the action. */
        logger.debug("Thread [" + Thread.currentThread().getId() + "] " + message);
    }

    public static void info(String message) {
        logger.info("Thread [" + Thread.currentThread().getId() + "] " + message);
    }

    public static void error(String message) {
        logger.error("Thread [" + Thread.currentThread().getId() + "] " + message);
        throw new TestNGException(message);
    }
}
