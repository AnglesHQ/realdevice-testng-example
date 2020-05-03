package com.qa.tests.parallel.example_01;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class HooksBasicsTwo extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        Reporter.info("HooksBasicsTwo: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        Reporter.info("HooksBasicsTwo: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        Reporter.info("HooksBasicsTwo: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        Reporter.info("HooksBasicsTwo: @AfterMethod");
        pause();
    }

    @Test
    private void HooksBasicsTwoTest1() {
        Reporter.info("Running HooksBasicsTwoTest1");
        pause();
    }

    @Test
    private void HooksBasicsTwoTest2() {
        Reporter.info("Running HooksBasicsTwoTest2");
        pause();
    }
}
