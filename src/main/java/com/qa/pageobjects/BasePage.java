package com.qa.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.qa.utils.TestUtils.pause;

public class BasePage {

    protected static final Logger logger = LogManager.getLogger(BasePage.class);
    protected RemoteWebDriver remoteWebDriver;
    protected WebDriverWait waitDriver;
    private final Integer DEFAULT_TIME_OUT = 30;
    //longer poll time than original 500 ms
    private final Long DEFAULT_POLL_TIME = new Long(1000);

    public BasePage(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
        waitDriver = new WebDriverWait(remoteWebDriver, DEFAULT_TIME_OUT, DEFAULT_POLL_TIME);
    }

    public BasePage(RemoteWebDriver remoteWebDriver, Integer timeout, long pollTime) {
        this.remoteWebDriver = remoteWebDriver;
        waitDriver = new WebDriverWait(remoteWebDriver, timeout, pollTime);
    }

    public void clickBy(By by){
        waitForElementToBeClickable(by);
        remoteWebDriver.findElement(by).click();
        logger.info("Clicked on button with by [" + by + "]");
    }

    protected void populateElementWithText(By by, String text) {
        WebElement element = remoteWebDriver.findElement(by);
        logger.info("Attempting to send the string [" + text + "] to element by [" + by + "]");
        element.sendKeys(text);
    }

    protected void waitForElementToBeSelected(By by) {
        WebElement element = remoteWebDriver.findElement(by);
        long startTime = System.currentTimeMillis();
        long endTime = DEFAULT_TIME_OUT * 1000;
        long elapsedTime = System.currentTimeMillis() - startTime;
        try {

            while(elapsedTime < endTime) {
                elapsedTime = System.currentTimeMillis() - startTime;
                pause(DEFAULT_POLL_TIME);
                if(element.getLocation().equals(remoteWebDriver.switchTo().activeElement().getLocation())) {
                    logger.info("Waited for element with by [" + by + "] to be selected");
                    return;
                }
            }
            throw new WebDriverException("Element was not selected after [" + DEFAULT_TIME_OUT + "] seconds");

        } catch (WebDriverException e) {
            logger.error("Unable to find and wait for element to be selected with by [" + by + "] due to [" + e.getMessage() + "]");
        }
    }

    protected void waitForElementToBeClickable(By by) {
        try {
            waitDriver.until(ExpectedConditions.and(
                                ExpectedConditions.presenceOfElementLocated(by),
                                ExpectedConditions.visibilityOfElementLocated(by),
                                ExpectedConditions.elementToBeClickable(by)));
        } catch (WebDriverException e) {
            logger.error("Unable to find and wait for element with by [" + by + "] due to [" + e.getMessage() + "]");
        }
        logger.info("Waited for element with by [" + by + "] to be clickable");
    }

    protected void waitForElementToBePresent(By by) {
        try {
            waitDriver.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (WebDriverException e) {
            logger.error("Unable to find and wait for element to be present with by [" + by + "] due to [" + e.getMessage() + "]");
        }
        logger.info("Waited for element presence with by [" + by + "] to be present");
    }
}
