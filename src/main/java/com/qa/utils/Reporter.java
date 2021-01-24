package com.qa.utils;

import com.github.angleshq.angles.AnglesReporter;
import com.github.angleshq.angles.api.models.build.Artifact;
import com.github.angleshq.angles.api.models.screenshot.ImageCompareResponse;
import com.github.angleshq.angles.api.models.screenshot.Screenshot;
import com.github.angleshq.angles.api.models.screenshot.ScreenshotDetails;
import org.apache.log4j.Logger;
import org.testng.TestNGException;

import java.util.Properties;

public class Reporter {

    private static AnglesReporter anglesReporter;
    private static Logger logger = Logger.getLogger("Reporter");
    private static Properties anglesProperties;
    private static Boolean anglesEnabled;

    static {
        anglesProperties = PropertiesHelper.loadPropertiesFile("/angles.properties");
        anglesEnabled = Boolean.valueOf(anglesProperties.getProperty("angles_enabled", "false"));

        if (anglesEnabled)
            anglesReporter = AnglesReporter.getInstance(anglesProperties.getProperty("angles_host") +  "/rest/api/v1.0/");
    }

    public static void startBuild() {
        logger.info("Thread [" + Thread.currentThread().getId() + "] Starting build with angles enabled [" + anglesEnabled + "]");

        if (anglesEnabled) {
            anglesReporter.startBuild(anglesProperties.getProperty("angles_run_name", "Test Run"),
                    anglesProperties.getProperty("angles_environment"),
                    anglesProperties.getProperty("angles_team"),
                    anglesProperties.getProperty("angles_component"));

//            // upload artifacts, here you could make your own calls to determine versions of SUT.
//            Artifact[] artifacts = new Artifact[]{
//                    new Artifact("com.example", "angles-ui", "1.0.0-BETA4"),
//                    new Artifact("com.example", "angles", "1.0.0-BETA4")
//            };
//            anglesReporter.storeArtifacts(artifacts);
        }
    }

    public static void startTest(String suiteName, String testName) {
        logger.info("Thread [" + Thread.currentThread().getId() + "] Starting Test: " + suiteName + " - " + testName);

        if (anglesEnabled)
            anglesReporter.startTest(suiteName, testName);
    }

    public static void startAction(String action) {
        if (anglesEnabled)
            anglesReporter.startAction(action);
    }

    public static void saveTest() {
        if (anglesEnabled)
            anglesReporter.saveTest();
    }

    public static void debug(String message) {
        logger.debug("Thread [" + Thread.currentThread().getId() + "] " + message);

        if (anglesEnabled)
            anglesReporter.debug(message);
    }

    public static void info(String message) {
        logger.info("Thread [" + Thread.currentThread().getId() + "] " + message);

        if (anglesEnabled)
            anglesReporter.info(message);
    }

    public static void info(String message, String screenshotId) {
        logger.info("Thread [" + Thread.currentThread().getId() + "] " + message);

        if (anglesEnabled)
            anglesReporter.info(message, screenshotId);
    }

    public static void pass(String name, String expected, String actual, String info) {
        logger.info("Thread [" + Thread.currentThread().getId() + "] " + name);
        if (anglesEnabled)
            anglesReporter.pass(name, expected, actual, info);
    }

    public static void fail(String name, String expected, String actual, String info) {
        logger.info("Thread [" + Thread.currentThread().getId() + "] " + name);
        if (anglesEnabled)
            anglesReporter.fail(name, expected, actual, info);
    }

    public static void error(String message) {
        logger.info("Thread [" + Thread.currentThread().getId() + "] " + message);
        if (anglesEnabled)
            anglesReporter.error(message);
        throw new TestNGException(message);
    }

    public static Screenshot storeScreenshot(ScreenshotDetails details) {
        if (anglesEnabled)
            return anglesReporter.storeScreenshot(details);

        return null;
    }

    public static void compareAgainstBaseline(Screenshot screenshot, Double acceptableMismatch) {
        ImageCompareResponse compareResponse = anglesReporter.compareScreenshotAgainstBaseline(screenshot.getId());
        if (compareResponse.getMisMatchPercentage() > acceptableMismatch) {
            anglesReporter.fail("Image compare [" + screenshot.getView() + "]", "Mismatch below ["  + acceptableMismatch + "]%", "Mismatch was [" + compareResponse.getMisMatchPercentage() + "]", "");
        } else {
            anglesReporter.pass("Image compare [" + screenshot.getView() + "]\"", "Mismatch below [" + acceptableMismatch + "]%", "Mismatch was [" + compareResponse.getMisMatchPercentage() + "]", "");

        }
    }
}
