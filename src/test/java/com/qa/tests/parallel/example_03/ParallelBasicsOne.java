package com.qa.tests.parallel.example_03;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelBasicsOne extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        logger.info("ParallelBasicsOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        logger.info("ParallelBasicsOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        logger.info("ParallelBasicsOne: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        logger.info("ParallelBasicsOne: @AfterMethod");
        pause();
    }

    @Test
    private void ParallelBasicsOneTest1() {
        logger.info("ParallelBasicsOne: ParallelBasicsOneTest1");
        pause();
    }

    @Test
    private void ParallelBasicsOneTest2() {
        logger.info("ParallelBasicsOne: ParallelBasicsOneTest2");
        pause();
    }
}
