package com.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Properties;

public class HomePage extends BasePage {

    private Properties baseProperties;

    //define page elements
    private By toggleMenuButton = By.className("mt-1");

    public HomePage(RemoteWebDriver remoteWebDriver, Properties baseProperties) {
        super(remoteWebDriver);
        this.baseProperties = baseProperties;
    }

    public void navigateTo() {
        String url = baseProperties.get("url").toString();
        remoteWebDriver.get(url);
        logger.info("Navigated to [" + url + "]");
    }

    public void toggleMenu() {
        clickBy(toggleMenuButton);
    }

}
