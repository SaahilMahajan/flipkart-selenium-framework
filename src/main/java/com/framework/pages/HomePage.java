package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import com.framework.utils.WaitUtils;

public class HomePage extends BasePage {

    private static final By SEARCH_BOX =
            By.cssSelector("input[placeholder='Search for Products, Brands and More']");

    private static final By LOGIN_POPUP_CLOSE =
            By.xpath("//span[@role='button' and normalize-space()='✕']");

    public void closeLoginPopupIfPresent() {
        try {
            WaitUtils.waitForClickable(LOGIN_POPUP_CLOSE, 5).click();
            log.info("Login popup appeared and was closed");
            WaitUtils.waitForInvisible(LOGIN_POPUP_CLOSE, 3);
        } catch (TimeoutException e) {
            log.info("Login popup did not appear");
        }
    }

    public SearchResultsPage searchProduct(String product) {
        type(SEARCH_BOX, product);
        pressEnter(SEARCH_BOX);
        return new SearchResultsPage();
    }
}