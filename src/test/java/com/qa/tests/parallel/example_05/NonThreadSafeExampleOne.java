package com.qa.tests.parallel.example_05;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class NonThreadSafeExampleOne extends BaseTest {

    private String testName;

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ThreadSafeExampleOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ThreadSafeExampleOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod(Method method) {
        testName = method.getName();
        Reporter.info("ThreadSafeExampleOne: @BeforeMethod" + "[ " + testName + "]");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ThreadSafeExampleOne: @AfterMethod" + "[ " + testName + "]");
        pause();
    }

    @Test (timeOut = 100000)
    private void ParallelInstancesOneTest1() {
        Reporter.info("ThreadSafeExampleOne: ParallelInstancesOneTest1" + "[ " + testName + "]");
    }

    @Test
    private void ParallelInstancesOneTest2() {
        Reporter.info("ThreadSafeExampleOne: ParallelInstancesOneTest2" + "[ " + testName + "]");
    }

    @Test
    private void ParallelInstancesOneTest3() {
        Reporter.info("ThreadSafeExampleOne: ParallelInstancesOneTest3" + "[ " + testName + "]");
    }
}
