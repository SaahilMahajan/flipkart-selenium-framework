package com.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.framework.config.ConfigReader;

public final class ExtentManager {

    private static ExtentReports extent;

    private ExtentManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/reports/index.html");
            spark.config().setReportName("Flipkart Automation Report");
            spark.config().setDocumentTitle("Test Report");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Browser", ConfigReader.getBrowser());
            extent.setSystemInfo("Headless", String.valueOf(ConfigReader.isHeadless()));
        }
        return extent;
    }
}