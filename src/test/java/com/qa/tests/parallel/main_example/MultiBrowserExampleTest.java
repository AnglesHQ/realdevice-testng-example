package com.qa.tests.parallel.main_example;

import com.github.angleshq.angles.api.models.screenshot.Screenshot;
import com.github.angleshq.angles.api.models.screenshot.ScreenshotDetails;
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
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

public class MultiBrowserExampleTest extends BrowserBaseTest {

    @Factory(dataProvider = "getBrowsers")
    public MultiBrowserExampleTest(HashMap<String, Object> deviceConfig) {
        super(deviceConfig);
    }


    /**
     * As resources are not limited we can setup/teardown browser for each test.
     */
    @BeforeMethod(alwaysRun = true)
    public void baseBeforeMethod(Method method) {
        Reporter.info("Running test " +  method.getName() + " against device [" + deviceName + "].");
        remoteWebDriver.set(instantiateRemoteWebDriver(method.getName(), deviceConfig));
    }

    @AfterMethod(alwaysRun = true)
    public void baseAfterMethod() {
        if (remoteWebDriver.get() != null) {
            Reporter.info("Tearing Down WebDriver");
            remoteWebDriver.get().close();
            remoteWebDriver.get().quit();
        }
    }
    @TestTags(phase = Phase.REGRESSION, devicetype = DeviceType.DESKTOP, platform = {Platform.WINDOWS}, tags = "github")
    @Test
    public void exampleSeleniumTest() {
        runTest();
    }

    @TestTags(phase = Phase.REGRESSION, devicetype = DeviceType.DESKTOP, platform = {Platform.WINDOWS}, tags = "github")
    @Test
    public void exampleSeleniumTest2() {
        runTest();
    }

    /**
     * Running same test twice, but in reality these would be two different tests.
     */
    private void runTest() {
        Reporter.info("Running");
        //visit home page
        HomePage homePage = new HomePage(remoteWebDriver.get(), baseProperties);
        homePage.navigateTo();
        //homePage.toggleMenu();

        //go to registration page.
        RegisterPage registerPage = new RegisterPage(remoteWebDriver.get(), baseProperties);
        registerPage.navigateTo();
        registerPage.waitforPageLoaded();

        Screenshot screenshot = TestUtils.takeScreenshot(remoteWebDriver.get(), "registration_page");
        // can only be used once a baseline is set.
        //Reporter.compareAgainstBaseline(screenshot, 1.0);

        Reporter.info("Current Url : " + remoteWebDriver.get().getCurrentUrl());

        //populate the fields
        registerPage.selectUserNameField();
        String randomUserName = RandomStringUtils.randomAlphabetic(10) + RandomStringUtils.randomNumeric(5);
        registerPage.populateUserNameField(randomUserName);
        registerPage.selectEmailField();
        registerPage.populateEmailField("user123@emailaddress.com");
        registerPage.selectPasswordField();
        registerPage.populatePasswordField("MyP@ssw0rd1s5up3rSecur3");

        // take random screenshot.
        Screenshot secondScreenshot = TestUtils.takeScreenshot(remoteWebDriver.get(), "registration_page_populated");

        // can only be used once a baseline is set.
        // Reporter.compareAgainstBaseline(secondScreenshot, 1.0);

        //assert the values
        Assert.assertEquals(registerPage.getUserNameField(), randomUserName);
        Assert.assertEquals(registerPage.getEmailField(), "user123@emailaddress.com");
        Assert.assertEquals(registerPage.isSignUpButtonEnabled(), true);
    }

}
