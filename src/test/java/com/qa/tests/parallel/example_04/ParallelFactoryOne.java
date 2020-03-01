package com.qa.tests.parallel.example_04;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelFactoryOne extends FactoryBaseTest {

    private String testName = "";

    @Factory(dataProvider = "testProvider")
    public ParallelFactoryOne(String test) {
        testName = test;
    }

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("ParallelInstancesOne: @BeforeClass [" + testName + "]");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("ParallelInstancesOne: @AfterClass [" + testName + "]");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("ParallelInstancesOne: @BeforeMethod [" + testName + "]");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("ParallelInstancesOne: @AfterMethod [" + testName + "]");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest1() {
        Reporter.info("ParallelInstancesOne: ParallelInstancesOneTest1 [" + testName + "]");
        pause();
    }

    @Test
    private void ParallelInstancesOneTest2() {
        Reporter.info("ParallelInstancesOne: ParallelInstancesOneTest2 [" + testName + "]");
        pause();
    }
}
