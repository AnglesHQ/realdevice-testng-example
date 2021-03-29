package com.qa.tests.parallel.example_01;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.Assert;
import org.testng.annotations.*;

public class HooksBasicsTwo extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        logger.info("HooksBasicsTwo: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        logger.info("HooksBasicsTwo: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        logger.info("HooksBasicsTwo: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        logger.info("HooksBasicsTwo: @AfterMethod");
        pause();
    }

    @Test
    private void HooksBasicsTwoTest1() {
        logger.info("Running HooksBasicsTwoTest1");
        Assert.assertEquals("pass", "pass");
        pause();
    }

    @Test
    private void HooksBasicsTwoTest2() {
        logger.info("Running HooksBasicsTwoTest2");
        Assert.assertEquals("pass", "pass");
        pause();
    }
}
