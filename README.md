# Flipkart Automation Framework

Selenium WebDriver + TestNG test automation framework in Java, using the Page Object Model.

## Tech stack
- Java 18, Maven
- Selenium WebDriver 4, TestNG
- Log4j2 for logging
- Extent Reports for HTML reporting

## Features
- Config-driven runs (`config.properties`): browser, base URL, headless mode, waits
- Thread-safe driver management (ThreadLocal)
- Explicit wait helpers and reusable base page actions
- Data-driven tests with TestNG DataProvider
- Screenshot on failure, embedded in the HTML report

## Project structure
src/test/java/com/framework
- base: BaseTest (browser setup and teardown)
- config: ConfigReader
- driver: DriverFactory
- pages: BasePage, HomePage, SearchResultsPage
- tests: test classes
- data: DataProviders
- listeners: TestListener (reporting and screenshots)
- reports: ExtentManager
- utils: WaitUtils

## Run the tests
mvn clean test

## Output
- HTML report: target/reports/index.html
- Logs: target/logs/test.log
- Failure screenshots: target/screenshots/

## Configuration
Edit src/test/resources/config.properties
- browser: chrome, firefox or edge
- isHeadless: true or false