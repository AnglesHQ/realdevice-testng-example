package com.qa.tests.parallel.example_03;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelInstancesOne extends BaseTest {

    @DataProvider
    public Object[][] testProvider() {
        return new Object[][]{
                {"test1"},
                {"test2"},
                {"test3"}
        };
    }

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ParallelInstancesOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ParallelInstancesOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("ParallelInstancesOne: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ParallelInstancesOne: @AfterMethod");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest1() {
        Reporter.info("ParallelInstancesOne: ParallelInstancesOneTest1");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest2() {
        Reporter.info("ParallelInstancesOne: ParallelInstancesOneTest2");
        pause();
    }

    @Test(dataProvider = "testProvider")
    private void ParallelInstancesOneTestData(String test) {
        Reporter.info("ParallelInstancesOne: ParallelInstancesOneTestData [" + test + "]");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest3() {
        Reporter.info("ParallelInstancesOne: ParallelInstancesOneTest3");
        pause();
    }
}
