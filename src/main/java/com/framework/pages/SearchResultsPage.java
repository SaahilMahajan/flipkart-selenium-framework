package com.framework.pages;

import org.openqa.selenium.By;

import com.framework.utils.WaitUtils;

public class SearchResultsPage extends BasePage {

    private static final By PRODUCT_LINKS = By.cssSelector("a[href*='/p/itm']");

    public int getResultCount() {
        WaitUtils.waitForVisible(PRODUCT_LINKS);
        int total = count(PRODUCT_LINKS);
        log.info("Search results found: {}", total);
        return total;
    }
}