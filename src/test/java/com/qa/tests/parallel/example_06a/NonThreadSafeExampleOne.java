package com.qa.tests.parallel.example_06a;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NonThreadSafeExampleOne extends BaseTest {

    //this class variable will be shared across the threads.
    private String testName;

    @BeforeClass
    private void testBeforeClass() {
        logger.info("ThreadSafeExampleOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        logger.info("ThreadSafeExampleOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod(Method method) {
        testName = method.getName();
        logger.info("ThreadSafeExampleOne: @BeforeMethod" + "[ " + testName + "]");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        logger.info("ThreadSafeExampleOne: @AfterMethod" + "[ " + testName + "]");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest1() {
        logger.info("ThreadSafeExampleOne: ParallelInstancesOneTest1" + "[ " + testName + "]");
    }

    @Test
    private void ParallelInstancesOneTest2() {
        logger.info("ThreadSafeExampleOne: ParallelInstancesOneTest2" + "[ " + testName + "]");
    }

    @Test
    private void ParallelInstancesOneTest3() {
        logger.info("ThreadSafeExampleOne: ParallelInstancesOneTest3" + "[ " + testName + "]");
    }
}
