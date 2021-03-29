package com.qa.basetest;

import com.qa.utils.PropertiesHelper;
import com.qa.utils.Reporter;
import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class MultiDeviceBaseTest extends BaseTest {

    //grab the required test properties
    protected Properties baseProperties = PropertiesHelper.loadPropertiesFile("/test.properties");
    protected Properties desiredCapsProperties = PropertiesHelper.loadPropertiesFile("/devices/desiredcaps.properties");
    protected String deviceName;
    protected String platformName;
    protected URL testObjectURL;
    protected String deviceType;
    // doesn't have to be thread local as each instance will only have 1 appium driver.
    protected AppiumDriver<WebElement> appiumDriver;

    /**
     *  This data provider can be adapted to return a single or a whole bunch of devices.
     *
     * @return
     */
    @DataProvider(name = "getDevices", parallel = true)
    public static Iterator<Object[]> getDevices() {
        Properties properties = PropertiesHelper.loadPropertiesFile("/test.properties");
        String deviceName = properties.getProperty("testobject_device_name");
        String platformName = properties.getProperty("testobject_platform_name");
        String platformVersion = properties.getProperty("testobject_platform_version");
        String supported = properties.getProperty("supported_devices");
        logger.info("Filtering arguments provided DeviceName [" + deviceName + "], PlatformName [" + platformName + "], PlatformVersion [" + platformVersion + "], Supported [" + supported + "]");
        ArrayList<Object[]> filterMap = filterDevicesByArguments(deviceName, platformName, platformVersion, supported);
        logger.info("Number of device selected from sheet [" + filterMap.size() + "]");
        return filterMap.iterator();
    }

    /**
     * Configure the device details for filtering.
     *
     * @param deviceConfig
     */
    public MultiDeviceBaseTest(HashMap<String, Object> deviceConfig) {
        this.deviceType = deviceConfig.get("deviceType").toString();
        this.deviceName = deviceConfig.get("name").toString();
        this.platformName = deviceConfig.get("platformName").toString();
        String urlString = baseProperties.getProperty("testobject_endpoint");

        //grab url from config and created desired caps.
        try {
            testObjectURL = new URL(urlString);
        } catch (MalformedURLException e) {
            logger.error("The URL provided [" + urlString + "] is invalid due to [" + e.getMessage() + "]");
        }

        appiumDriver = instantiateAppiumDriver(deviceConfig);

        //report the device it was run against and the report url.
        logger.info("Running tests against device [" + appiumDriver.getCapabilities().getCapability("testobject_device").toString() + "]. Results can be found here: [" + appiumDriver.getCapabilities().getCapability("testobject_test_report_url").toString() + "]");
    }

    private AppiumDriver instantiateAppiumDriver(HashMap<String, Object> deviceConfig) {
        //created desired capabilities from 2 sources (common and device specific).
        DesiredCapabilities desiredCapabilities = TestUtils.createDesiredCaps(desiredCapsProperties, deviceConfig);

        //set test name (optional)
        desiredCapabilities.setCapability("testobject_test_name", this.getClass().getSimpleName());

        //instantiate driver.
        if (deviceConfig.get("platformName").toString().equalsIgnoreCase("IOS")) {
            return new IOSDriver<>(testObjectURL, desiredCapabilities);
        } else {
            return new AndroidDriver<>(testObjectURL, desiredCapabilities);
        }
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getPlatformName() {
        return this.platformName;
    }

    private static ArrayList<Object[]> filterDevicesByArguments(String deviceName, String platformName, String platformVersion, String supported) {
        return filterDevicesByArguments(deviceName, platformName, platformVersion, Boolean.valueOf(supported), "RealDevices");
    }
}
