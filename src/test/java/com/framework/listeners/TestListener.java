package com.framework.listeners;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.driver.DriverFactory;
import com.framework.reports.ExtentManager;

public class TestListener implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);
    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String name = result.getName();
        Object[] params = result.getParameters();
        if (params.length > 0) {
            name = name + " [" + params[0] + "]";
        }
        TEST.set(ExtentManager.getInstance().createTest(name));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        TEST.get().pass("Test passed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        TEST.get().skip("Test skipped");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {} - {}", result.getName(), result.getThrowable().getMessage());
        saveScreenshotFile(result.getName());

        try {
            String base64 = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
            TEST.get().fail(result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64, "Failure screenshot").build());
        } catch (Exception e) {
            TEST.get().fail(result.getThrowable());
            log.error("Could not attach screenshot to report", e);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

    private void saveScreenshotFile(String testName) {
        try {
            File src = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
            Path dir = Paths.get("target", "screenshots");
            Files.createDirectories(dir);
            Path target = dir.resolve(testName + ".png");
            Files.copy(src.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
            log.info("Screenshot saved: {}", target);
        } catch (Exception e) {
            log.error("Could not capture screenshot", e);
        }
    }
}