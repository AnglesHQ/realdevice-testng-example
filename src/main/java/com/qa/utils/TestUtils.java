package com.qa.utils;

import com.github.angleshq.angles.api.models.Platform;
import com.github.angleshq.angles.api.models.screenshot.Screenshot;
import com.github.angleshq.angles.api.models.screenshot.ScreenshotDetails;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class TestUtils {

    private static String defaultScreenshotDirectory = "target" + File.separator + "qa-logs" + File.separator + "screenshots" + File.separator;

    public static DesiredCapabilities createDesiredCaps(Properties defaultProperties, HashMap<String, Object> deviceConfigMap) {
        //setup desired caps for native app chrome beta.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        for (String key : defaultProperties.stringPropertyNames()) {
            capabilities.setCapability(key, defaultProperties.getProperty(key));
        }

        for (String key: deviceConfigMap.keySet()){
            if (deviceConfigMap.get(key) != null && !deviceConfigMap.get(key).toString().isEmpty() && !deviceConfigMap.get(key).toString().equalsIgnoreCase("name")) {
                capabilities.setCapability(key, deviceConfigMap.get(key));
            }
        }
        return capabilities;
    }

    public static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch(InterruptedException e) {

        }
    }

    public static Screenshot takeScreenshot(RemoteWebDriver remoteWebDriver) {
        return takeScreenshot(remoteWebDriver, null);
    }

    public static Screenshot takeScreenshot(RemoteWebDriver remoteWebDriver, String view) {

        // Take the Screen shot and store it in a file
        WebDriver augmentedDriver = new Augmenter().augment(remoteWebDriver);
        File screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);

        // Generate the name of the file
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss.SS");
        String currentDate = sdf.format(new Date());
        String filename = defaultScreenshotDirectory + currentDate + "_" + "thread_" + Thread.currentThread().getId() + "_screenshot.png";
        try {
            FileUtils.copyFile(screenshot, new File(filename));
            ScreenshotDetails details = getScreenshotDetails(remoteWebDriver, filename, view);
            Screenshot storedScreenshot = Reporter.storeScreenshot(details);
            Reporter.info("Took screenshot", storedScreenshot.getId());
            return storedScreenshot;
        } catch (IOException e) {
            Reporter.error("Could not take Screenshot: " + e.getMessage());
            return null;
        }
    }

    private static ScreenshotDetails getScreenshotDetails(RemoteWebDriver remoteWebDriver, String fileName, String view) {
        ScreenshotDetails details = new ScreenshotDetails();
        if (view != null) { details.setView(view); };
        details.setPath(fileName);
        Platform platform = new Platform();
        Capabilities capabilities = remoteWebDriver.getCapabilities();
        platform.setPlatformName(capabilities.getPlatform().toString());
        if (capabilities.getBrowserName() != null) {
            platform.setBrowserName(capabilities.getBrowserName());
        }
        if (capabilities.getVersion() != null) {
            platform.setBrowserVersion(capabilities.getVersion());
        }
        details.setPlatform(platform);
        return details;
    }

}
