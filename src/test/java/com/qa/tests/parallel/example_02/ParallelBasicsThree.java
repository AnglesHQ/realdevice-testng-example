package com.qa.tests.parallel.example_02;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelBasicsThree extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ParallelBasicsThree: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ParallelBasicsThree: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("ParallelBasicsThree: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ParallelBasicsThree: @AfterMethod");
        pause();
    }

    @Test
    private void ParallelBasicsThreeTest1() {
        Reporter.info("ParallelBasicsThree: ParallelBasicsThreeTest1");
        pause();
    }

    @Test
    private void ParallelBasicsThreeTest2() {
        Reporter.info("ParallelBasicsThree: ParallelBasicsThreeTest2");
        pause();
    }
}
