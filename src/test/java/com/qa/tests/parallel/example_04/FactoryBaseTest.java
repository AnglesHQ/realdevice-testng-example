package com.qa.tests.parallel.example_04;

import com.qa.tests.parallel.common.BaseTest;
import org.testng.annotations.DataProvider;

public class FactoryBaseTest extends BaseTest {

    @DataProvider
    public static Object[][] testProvider() {
        return new Object[][]{
                {"device1"},
                {"device2"},
                {"device3"}
        };
    }

}
