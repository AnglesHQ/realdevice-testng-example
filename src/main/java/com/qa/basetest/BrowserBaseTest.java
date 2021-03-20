package com.qa.basetest;

import com.qa.utils.PropertiesHelper;
import com.qa.utils.Reporter;
import com.qa.utils.TestUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

public class BrowserBaseTest extends BaseTest {

    // these variables are set on instantiation of the class or in the constructor and are shared across the threads.
    protected Properties baseProperties = PropertiesHelper.loadPropertiesFile("/test.properties");
    protected Properties desiredCapsProperties = PropertiesHelper.loadPropertiesFile("/devices/sl_desiredcaps.properties");
    protected String deviceName;
    protected String platformName;
    protected HashMap<String, Object> deviceConfig;

    // has to be thread local as we will have multiple threads per class (e.g. running methods in parallel).
    protected ThreadLocal<RemoteWebDriver> remoteWebDriver = new ThreadLocal<>();

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
        String supported = properties.getProperty("supported_devices");
        Reporter.info("Filtering arguments provided DeviceName [" + deviceName + "], PlatformName [" + platformName + "], PlatformVersion [" + platformVersion + "], Supported [" + supported + "]");
        ArrayList<Object[]> filterMap = filterDevicesByArguments(deviceName, platformName, platformVersion, Boolean.valueOf(supported), "Browsers");
        Reporter.info("Number of browsers selected from sheet [" + filterMap.size() + "]");
        return filterMap.iterator();
    }

    @BeforeMethod(alwaysRun = true)
    @Override
    public synchronized void before(Method method) {
        Reporter.startTest(method.getClass().getSimpleName() + " - " + deviceName , method.getName());
        Reporter.info("### Starting test [" + method.getName() + "] ###");
    }

    /**
     * Configure the device details for filtering.
     *
     * @param deviceConfig
     */
    public BrowserBaseTest(HashMap<String, Object> deviceConfig) {
        //set base properties in constructur or beforeclass
        this.deviceConfig = deviceConfig;
        this.deviceName = deviceConfig.get("name").toString();
        this.platformName = deviceConfig.get("platformName").toString();
    }

    protected RemoteWebDriver instantiateRemoteWebDriver(String testName, HashMap<String, Object> deviceConfig) {
        //created desired capabilities from 2 sources.
        DesiredCapabilities desiredCapabilities = TestUtils.createDesiredCaps(desiredCapsProperties, deviceConfig);

        //set test name (optional)
        desiredCapabilities.setCapability("name", testName);
        desiredCapabilities.setCapability("browserName", "chrome");

        String urlString = baseProperties.getProperty("saucelabs_endpoint");
        URL sauceLabsURL = null;
        RemoteWebDriver webDriver = null;
        try {
            sauceLabsURL = new URL(urlString);
            webDriver = new RemoteWebDriver(sauceLabsURL, desiredCapabilities);
        } catch (Exception exception) {
            Reporter.info(exception.getMessage());
            // trying again, probably worth adding better handling
            webDriver = new RemoteWebDriver(sauceLabsURL, desiredCapabilities);
        }
        return webDriver;
    }
}
