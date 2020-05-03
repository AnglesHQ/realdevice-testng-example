package com.qa.tests.parallel.example_02;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelBasicsOne extends BaseTest {

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

    @Test
    private void ParallelBasicsOneTest1() {
        Reporter.info("ParallelBasicsOne: ParallelBasicsOneTest1");
        pause();
    }

    @Test
    private void ParallelBasicsOneTest2() {
        Reporter.info("ParallelBasicsOne: ParallelBasicsOneTest2");
        pause();
    }

    @Test
    private void ParallelBasicsOneTest3() {
        Reporter.info("ParallelBasicsOne: ParallelBasicsOneTest3");
        pause();
    }


    @Test
    private void ParallelBasicsOneTest4() {
        Reporter.info("ParallelBasicsOne: ParallelBasicsOneTest4");
        pause();
    }

}
