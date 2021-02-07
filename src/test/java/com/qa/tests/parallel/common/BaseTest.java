package com.qa.tests.parallel.common;

import com.github.angleshq.angles.AnglesReporter;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Random;

public abstract class BaseTest {

    public static final int MIN_WAIT = 100;
    public static final int MAX_WAIT = 300;
    private Random random = new Random();

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
    protected void baseBeforeMethod(Method method) {
        Reporter.startTest(method.getDeclaringClass().getSimpleName(), method.getName());
        Reporter.startAction("before");
        Reporter.info("BaseTest: @BeforeMethod");
        pause();
    }

    @AfterMethod(alwaysRun = true)
    protected void baseAfterMethod() {
        Reporter.startAction("after");
        Reporter.info("BaseTest: @AfterMethod");
        pause();
        Reporter.saveTest();
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
