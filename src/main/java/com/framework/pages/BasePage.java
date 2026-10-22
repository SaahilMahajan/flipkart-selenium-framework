package com.framework.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.framework.driver.DriverFactory;
import com.framework.utils.WaitUtils;

public abstract class BasePage {
	
	protected final Logger log = LogManager.getLogger(getClass());

    protected void click(By locator) {
        log.info("Clicking: {}", locator);
        WaitUtils.waitForClickable(locator).click();
    }

    protected void type(By locator, String text) {
        log.info("Typing '{}' into: {}", text, locator);
        WebElement element = WaitUtils.waitForVisible(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        String text = WaitUtils.waitForVisible(locator).getText();
        log.info("Text of {}: {}", locator, text);
        return text;
    }

    // Returns false instead of throwing when the element is absent
    protected boolean isDisplayed(By locator) {
        return !DriverFactory.getDriver().findElements(locator).isEmpty()
                && DriverFactory.getDriver().findElement(locator).isDisplayed();
    }

    protected String getTitle() {
        return DriverFactory.getDriver().getTitle();
    }

    protected String getCurrentUrl() {
        return DriverFactory.getDriver().getCurrentUrl();
    }
    protected void pressEnter(By locator) {
        log.info("Pressing Enter on: {}", locator);
        WaitUtils.waitForVisible(locator).sendKeys(Keys.ENTER);
    }
    protected int count(By locator) {
        return DriverFactory.getDriver().findElements(locator).size();
    }
}