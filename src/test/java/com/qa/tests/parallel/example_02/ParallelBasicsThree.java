package com.qa.tests.parallel.example_02;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelBasicsThree extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        logger.info("ParallelBasicsThree: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        logger.info("ParallelBasicsThree: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        logger.info("ParallelBasicsThree: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        logger.info("ParallelBasicsThree: @AfterMethod");
        pause();
    }

    @Test
    private void ParallelBasicsThreeTest1() {
        logger.info("ParallelBasicsThree: ParallelBasicsThreeTest1");
        pause();
    }

    @Test
    private void ParallelBasicsThreeTest2() {
        logger.info("ParallelBasicsThree: ParallelBasicsThreeTest2");
        pause();
    }
}
