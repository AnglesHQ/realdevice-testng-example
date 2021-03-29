package com.qa.tests.parallel.example_04;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelBasicsTwo extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        logger.info("ParallelBasicsTwo: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        logger.info("ParallelBasicsTwo: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        logger.info("ParallelBasicsTwo: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        logger.info("ParallelBasicsTwo: @AfterMethod");
        pause();
    }

    @Test
    private void ParallelBasicsTwoTest1() {
        logger.info("ParallelBasicsTwo: ParallelBasicsTwoTest1");
        pause();
    }

    @Test
    private void ParallelBasicsTwoTest2() {
        logger.info("ParallelBasicsTwo: ParallelBasicsTwoTest2");
        pause();
    }
}
