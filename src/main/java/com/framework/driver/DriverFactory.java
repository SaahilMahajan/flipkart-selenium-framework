package com.framework.driver;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.framework.config.ConfigReader;

public final class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    private DriverFactory() {
    }

    public static void initDriver() {
        String browser = ConfigReader.getBrowser();
        boolean headless = ConfigReader.isHeadless();
        log.info("Starting browser: {} (headless={})", browser, headless);
        WebDriver driver;

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("-headless", "--width=1920", "--height=1080");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless=new", "--window-size=1920,1080");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigReader.getImplicitWait()));
        if (!headless) {
            driver.manage().window().maximize();
        }
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("Driver not initialised. Call initDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
        	log.info("Quitting driver");
            driver.quit();
            DRIVER.remove();
        }
    }
}