package com.qa.tests.parallel.example_02b;

import com.qa.tests.parallel.common.BaseTest;
import com.qa.utils.Reporter;
import org.testng.annotations.*;

public class ParallelBasicsOne extends BaseTest {

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

    /*
     * By default a data provider does not run in parallel,
     * but if parallel is set the "default" data-provider-thread-count = 10.
     */
    @DataProvider(parallel = true)
    public static Object[][] testProvider() {
        return new Object[][]{
                {"device1"},
                {"device2"},
                {"device3"},
                {"device4"},
                {"device5"},
                {"device6"},
                {"device7"},
                {"device8"},
                {"device9"},
                {"device10"},
                {"device11"},
                {"device12"},
                {"device13"},
                {"device14"},
                {"device15"}
        };
    }

    @Test(dataProvider = "testProvider")
    private void ParallelInstancesTwoTestData(String test) {
        Reporter.info("ParallelBasicsTwo: ParallelBasicsTwoTestData [" + test + "]");
        pause();
    }

}
