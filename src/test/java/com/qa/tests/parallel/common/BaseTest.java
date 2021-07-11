package com.qa.tests.parallel.common;

import com.github.angleshq.angles.basetest.testng.AnglesTestngBaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Random;

public abstract class BaseTest extends AnglesTestngBaseTest {

    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    public static final int MIN_WAIT = 100;
    public static final int MAX_WAIT = 300;
    private Random random = new Random();

    @AfterClass(alwaysRun = true)
    protected void baseAfterClass() {
        logger.info("BaseTest: @AfterClass");
        pause();
    }

    @BeforeSuite(alwaysRun = true)
    protected void baseBeforeSuite() {
        logger.info("BaseTest: @BeforeSuite");
        pause();
    }

    @AfterSuite(alwaysRun = true)
    protected void baseAfterSuite() {
        logger.info("BaseTest: @AfterSuite");
        pause();
    }

    @BeforeMethod(alwaysRun = true)
    protected void baseBeforeMethod(Method method) {
        logger.info("BaseTest: @BeforeMethod");
        pause();
    }

    @AfterMethod(alwaysRun = true)
    protected void baseAfterMethod() {
        logger.info("BaseTest: @AfterMethod");
        pause();
    }

    protected void pause() {
        int randomWait = random.nextInt(MAX_WAIT-MIN_WAIT) + MIN_WAIT;
        pause(randomWait);
    }

    protected void pause(Integer msSecondsToSleep) {
        try {
            Thread.sleep(msSecondsToSleep);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
