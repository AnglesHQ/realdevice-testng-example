package com.qa.tests.parallel.common;

import com.qa.utils.Reporter;
import org.testng.annotations.*;

import java.util.Random;

public abstract class BaseTest {

    public static final int MIN_WAIT = 100;
    public static final int MAX_WAIT = 300;
    private Random random = new Random();

    /* methods have to be protected or public to be inherited by any test */
    /* added random pause to make tests more realistic */
    @BeforeClass(alwaysRun = true)
    protected void baseBeforeClass() {
        Reporter.info("BaseTest: @BeforeClass");
        pause();
    }

    @AfterClass(alwaysRun = true)
    protected void baseAfterClass() {
        Reporter.info("BaseTest: @AfterClass");
        pause();
    }

    @BeforeSuite(alwaysRun = true)
    protected void baseBeforeSuite() {
        Reporter.info("BaseTest: @BeforeSuite");
        pause();
    }

    @AfterSuite(alwaysRun = true)
    protected void baseAfterSuite() {
        Reporter.info("BaseTest: @AfterSuite");
        pause();
    }

    @BeforeMethod(alwaysRun = true)
    protected void baseBeforeMethod() {
        Reporter.info("BaseTest: @BeforeMethod");
        pause();
    }

    @AfterMethod(alwaysRun = true)
    protected void baseAfterMethod() {
        Reporter.info("BaseTest: @AfterMethod");
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
            Reporter.error(e.getMessage());
        }
    }

}
