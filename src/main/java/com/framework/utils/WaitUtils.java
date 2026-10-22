package com.framework.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.config.ConfigReader;
import com.framework.driver.DriverFactory;

public final class WaitUtils {

    private WaitUtils() {
    }

    // Built on every call so it always uses the current thread's driver
    private static WebDriverWait getWait(int seconds) {
        return new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(seconds));
    }

    private static int defaultTimeout() {
        return ConfigReader.getInt("explicitWait");
    }

    public static WebElement waitForVisible(By locator) {
        return getWait(defaultTimeout()).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(By locator) {
        return getWait(defaultTimeout()).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForClickable(By locator, int seconds) {
        return getWait(seconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForInvisible(By locator, int seconds) {
        return getWait(seconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean waitForTitleContains(String text) {
        return getWait(defaultTimeout()).until(ExpectedConditions.titleContains(text));
    }

    public static boolean waitForUrlContains(String text) {
        return getWait(defaultTimeout()).until(ExpectedConditions.urlContains(text));
    }
}