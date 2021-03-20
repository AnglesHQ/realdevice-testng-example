package com.qa.tests.parallel.main_example;

import com.github.angleshq.angles.api.models.screenshot.Screenshot;
import com.qa.basetest.MultiDeviceBaseTest;
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

public class MultiDeviceExampleTest extends MultiDeviceBaseTest {

    @Factory(dataProvider = "getDevices")
    public MultiDeviceExampleTest(HashMap<String, Object> deviceConfig) {
        super(deviceConfig);
    }

    @TestTags(phase = Phase.REGRESSION, devicetype = DeviceType.PHONE, platform = {Platform.ANDROID, Platform.IOS}, tags="github")
    @Test
    public void exampleAppiumTest() {
        runTest();
    }


    @TestTags(phase = Phase.REGRESSION, devicetype = DeviceType.PHONE, platform = {Platform.ANDROID, Platform.IOS}, tags="github")
    @Test
    public void exampleAppiumTest2() {
        runTest();
    }

    /**
     * Running same test twice, but in reality these would be two different tests.
     */
    private void runTest() {
        //visit home page
        HomePage homePage = new HomePage(appiumDriver, baseProperties);
        homePage.navigateTo();
        homePage.toggleMenu();

        //go to registration page.
        RegisterPage registerPage = new RegisterPage(appiumDriver, baseProperties);
        registerPage.navigateTo();
        registerPage.waitforPageLoaded();
        Screenshot screenshot = TestUtils.takeScreenshot(appiumDriver, "registration_page");
        Reporter.compareAgainstBaseline(screenshot, 1.0);
        Reporter.info("Current Url : " + appiumDriver.getCurrentUrl());

        //populate the fields
        registerPage.selectUserNameField();
        String randomUserName = RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5);
        registerPage.populateUserNameField(randomUserName);
        registerPage.selectEmailField();
        registerPage.populateEmailField("user123@emailaddress.com");
        registerPage.selectPasswordField();
        registerPage.populatePasswordField("MyP@ssw0rd1s5up3rSecur3");

        Screenshot secondScreenshot = TestUtils.takeScreenshot(appiumDriver, "registration_page_populated");
        Reporter.compareAgainstBaseline(secondScreenshot, 1.0);

        //assert the values
        Reporter.assertEquals(registerPage.getUserNameField(), randomUserName);
        Reporter.assertEquals(registerPage.getEmailField(), "user123@emailaddress.com");
        //Assert.assertEquals(registerPage.isSignUpButtonEnabled(), true);
    }


    @AfterClass
    public void afterClass() {
        appiumDriver.close();
        appiumDriver.quit();
    }

}
