package com.qa.tests.parallel.example_04;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelBasicsTwo extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ParallelBasicsTwo: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ParallelBasicsTwo: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("ParallelBasicsTwo: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ParallelBasicsTwo: @AfterMethod");
        pause();
    }

    @Test
    private void ParallelBasicsTwoTest1() {
        Reporter.info("ParallelBasicsTwo: ParallelBasicsTwoTest1");
        pause();
    }

    @Test
    private void ParallelBasicsTwoTest2() {
        Reporter.info("ParallelBasicsTwo: ParallelBasicsTwoTest2");
        pause();
    }
}
