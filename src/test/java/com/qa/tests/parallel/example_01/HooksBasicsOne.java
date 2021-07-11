package com.qa.tests.parallel.example_01;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.Assert;
import org.testng.annotations.*;

public class HooksBasicsOne extends BaseTest {

    @BeforeClass
    private void testBeforeClass() {
        logger.info("HooksBasicsOne: @BeforeClass");
        pause();
    }

    @AfterClass
    private void testAfterClass() {
        logger.info("HooksBasicsOne: @AfterClass");
        pause();
    }

    @BeforeMethod
    private void testBeforeMethod() {
        logger.info("HooksBasicsOne: @BeforeMethod");
        pause();
    }

    @AfterMethod
    private void testAfterMethod() {
        logger.info("HooksBasicsOne: @AfterMethod");
        pause();
    }

    @Test
    private void HooksBasicsOneTest1() {
        logger.info("HooksBasicsOne: HooksBasicsOneTest1");
        Assert.assertEquals("pass", "pass");
        pause();
    }

    @Test
    private void HooksBasicsOneTest2() {
        logger.info("HooksBasicsOne: HooksBasicsOneTest2");
        Assert.assertEquals("pass", "pass");
        pause();
    }
}
