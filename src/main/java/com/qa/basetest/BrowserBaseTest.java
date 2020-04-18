package com.qa.basetest;

import com.qa.utils.PropertiesHelper;
import com.qa.utils.Reporter;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class BrowserBaseTest extends BaseTest {

    //grab the required test properties
    protected Properties baseProperties = PropertiesHelper.loadPropertiesFile("/test.properties");
    protected Properties desiredCapsProperties = PropertiesHelper.loadPropertiesFile("/devices/sl_desiredcaps.properties");

    protected String deviceName;
    protected String deviceType;
    protected String platformName;
    protected URL sauceLabsURL;

    protected RemoteWebDriver remoteWebDriver;
    /**
     * Configure the device details for filtering.
     *
     * @param deviceConfig
     */
    public BrowserBaseTest(HashMap<String, Object> deviceConfig) {
        this.deviceName = deviceConfig.get("name").toString();
        this.platformName = deviceConfig.get("platformName").toString();
        String urlString = baseProperties.getProperty("saucelabs_endpoint");

        //grab url from config and created desired caps.
        try {
            sauceLabsURL = new URL(urlString);
        } catch (MalformedURLException e) {
            Reporter.error("The URL provided [" + urlString + "] is invalid due to [" + e.getMessage() + "]");
        }

        //created desired capabilities from 2 sources.
        DesiredCapabilities desiredCapabilities = TestUtils.createDesiredCaps(desiredCapsProperties, deviceConfig);

        //set test name (optional)
        desiredCapabilities.setCapability("name", this.getClass().getSimpleName());
        desiredCapabilities.setCapability("browserName", "chrome");

        //instantiate driver.
        remoteWebDriver = new RemoteWebDriver(sauceLabsURL, desiredCapabilities);

        //report the device it was run against and the report url.
        Reporter.info("Running tests against device [" + deviceName + "].");
    }

    /**
     *  This data provider can be adapted to return a single or a whole bunch of devices.
     *
     * @return
     */
    @DataProvider(name = "getBrowsers", parallel = true)
    public static Iterator<Object[]> getDevices() {
        Properties properties = PropertiesHelper.loadPropertiesFile("/test.properties");

        String deviceName = properties.getProperty("saucelabs_device_name");
        String platformName = properties.getProperty("saucelabs_platform_name");
        String platformVersion = properties.getProperty("saucelabs_platform_version");
        Reporter.info("Filtering arguments provided DeviceName [" + deviceName + "], PlatformName [" + platformName + "], PlatformVersion [" + platformVersion + "]");

        ArrayList<Object[]> filterMap = filterDevicesByArguments(deviceName, platformName, platformVersion, "Browsers");

        Reporter.info("Number of browsers selected from sheet [" + filterMap.size() + "]");
        return filterMap.iterator();
    }

}
