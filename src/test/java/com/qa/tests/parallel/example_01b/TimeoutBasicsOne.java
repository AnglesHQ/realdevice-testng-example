package com.qa.tests.parallel.example_01b;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class TimeoutBasicsOne extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ParallelBasicsOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ParallelBasicsOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("ParallelBasicsOne: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ParallelBasicsOne: @AfterMethod");
        pause();
    }

    @Test(timeOut = 10000)
    private void ParallelBasicsOneTest2() {
        Reporter.info("ParallelBasicsOne: ParallelBasicsOneTest2");
        pause();
    }
}