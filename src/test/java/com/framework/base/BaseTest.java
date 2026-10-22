package com.framework.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.framework.config.ConfigReader;
import com.framework.driver.DriverFactory;

public abstract class BaseTest {
	
	protected final Logger log = LogManager.getLogger(getClass());

	@BeforeMethod
	public void setUp(Method method) {
	    log.info("=== Starting test: {} ===", method.getName());
	    DriverFactory.initDriver();
	    String url = ConfigReader.getBaseUrl();
	    log.info("Opening: {}", url);
	    DriverFactory.getDriver().get(url);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
	    log.info("=== Finished test: {} ===", result.getName());
	    DriverFactory.quitDriver();
	}

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}