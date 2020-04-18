package com.qa.tests.parallel.main_example;

import com.qa.basetest.BrowserBaseTest;
import com.qa.basetest.TestTags;
import com.qa.basetest.tags.DeviceType;
import com.qa.basetest.tags.Phase;
import com.qa.basetest.tags.Platform;
import com.qa.pageobjects.HomePage;
import com.qa.pageobjects.RegisterPage;
import com.qa.utils.Reporter;
import com.qa.utils.TestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.HashMap;

public class MultiBrowserExampleTest extends BrowserBaseTest {

    @Factory(dataProvider = "getBrowsers")
    public MultiBrowserExampleTest(HashMap<String, Object> deviceConfig) {
        super(deviceConfig);
    }

    @TestTags(phase = Phase.REGRESSION, devicetype = DeviceType.DESKTOP, platform = {Platform.WINDOWS}, tags="github")
    @Test
    public void exampleSeleniumTest() {
      Reporter.info("Running");
        //visit home page
        HomePage homePage = new HomePage(remoteWebDriver, baseProperties);
        homePage.navigateTo();
        //homePage.toggleMenu();

        //go to registration page.
        RegisterPage registerPage = new RegisterPage(remoteWebDriver, baseProperties);
        registerPage.navigateTo();
        registerPage.waitforPageLoaded();
        TestUtils.takeScreenshot(remoteWebDriver);
        Reporter.info("Current Url : " + remoteWebDriver.getCurrentUrl());

        //populate the fields
        registerPage.selectUserNameField();
        String randomUserName = RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5);
        registerPage.populateUserNameField(randomUserName);
        registerPage.selectEmailField();
        registerPage.populateEmailField("user123@emailaddress.com");
        registerPage.selectPasswordField();
        registerPage.populatePasswordField("MyP@ssw0rd1s5up3rSecur3");

        //assert the values
        Assert.assertEquals(registerPage.getUserNameField(), randomUserName);
        Assert.assertEquals(registerPage.getEmailField(), "user123@emailaddress.com");
        Assert.assertEquals(registerPage.isSignUpButtonEnabled(), true);

        TestUtils.takeScreenshot(remoteWebDriver);
    }

    @AfterClass
    public void afterClass() {
        remoteWebDriver.close();
        remoteWebDriver.quit();
    }

}