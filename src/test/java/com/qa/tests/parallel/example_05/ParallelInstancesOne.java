package com.qa.tests.parallel.example_05;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelInstancesOne extends BaseTest {

    private String testName = "";

    @Factory(dataProvider = "testProvider")
    public ParallelInstancesOne(String test) {
        testName = test;
    }

    @DataProvider
    public static Object[][] testProvider() {
        return new Object[][]{
                {"Device 1"},
                {"Device 2"},
                {"Device 3"},
                {"Device 4"},
        };
    }

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ParallelInstancesOne [" + testName + "]: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ParallelInstancesOne [" + testName + "]: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("ParallelInstancesOne [" + testName + "]: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ParallelInstancesOne [" + testName + "]: @AfterMethod");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest1() {
        Reporter.info("[" + testName + "]: Test 1");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest2() {
        Reporter.info("[" + testName + "]: Test 2");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest3() {
        Reporter.info("[" + testName + "]: Test 3");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest4() {
        Reporter.info("[" + testName + "]: Test 4");
        pause();
    }

}
