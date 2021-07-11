package com.qa.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.Properties;

public class RegisterPage extends BasePage {

    private Properties baseProperties;
    //define page elements
    private By emailField = By.id("user_email");
    private By userNameField = By.id("user_login");
    private By passwordField = By.id("user_password");
    private By signUpButton = By.id("signup_button");

    public RegisterPage(RemoteWebDriver remoteWebDriver, Properties baseProperties) {
        super(remoteWebDriver);
        this.baseProperties = baseProperties;
    }

    public void navigateTo() {
        String url = baseProperties.get("url").toString();
        remoteWebDriver.get(url + "/join");
        logger.info("Navigated to [" + url + "]");
    }

    public void waitforPageLoaded() {
        waitForElementToBePresent(userNameField);
    }

    public void populateEmailField(String email) {
        populateElementWithText(emailField, email);
    }

    public void selectEmailField() {
        clickBy(emailField);
        waitForElementToBeSelected(emailField);
    }

    public void populateUserNameField(String name) {
        populateElementWithText(userNameField, name);
    }

    public void selectUserNameField() {
        clickBy(userNameField);
        waitForElementToBeSelected(userNameField);
    }

    public void populatePasswordField(String password) {
        populateElementWithText(passwordField, password);
    }

    public void selectPasswordField() {
        clickBy(passwordField);
        waitForElementToBeSelected(passwordField);
    }

    public String getEmailField() {
        return remoteWebDriver.findElement(emailField).getAttribute("value");
    }

    public String getUserNameField() {
        return remoteWebDriver.findElement(userNameField).getAttribute("value");
    }

    public boolean isSignUpButtonEnabled() {
        return remoteWebDriver.findElement(signUpButton).isEnabled();
    }
}
