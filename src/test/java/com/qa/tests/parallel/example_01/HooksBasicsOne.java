package com.qa.tests.parallel.example_01;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class HooksBasicsOne extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("HooksBasicsOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("HooksBasicsOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("HooksBasicsOne: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("HooksBasicsOne: @AfterMethod");
        pause();
    }

    @Test
    private void HooksBasicsOneTest1() {
        Reporter.info("HooksBasicsOne: HooksBasicsOneTest1");
        pause();
    }

    @Test
    private void HooksBasicsOneTest2() {
        Reporter.info("HooksBasicsOne: HooksBasicsOneTest2");
        pause();
    }
}
