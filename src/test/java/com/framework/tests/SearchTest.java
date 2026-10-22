package com.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.base.BaseTest;
import com.framework.data.SearchData;
import com.framework.pages.HomePage;
import com.framework.pages.SearchResultsPage;

public class SearchTest extends BaseTest {

    @Test(dataProvider = "products", dataProviderClass = SearchData.class)
    public void verifySearchShowsResults(String product) {
        log.info("Searching for: {}", product);

        HomePage homePage = new HomePage();
        homePage.closeLoginPopupIfPresent();

        SearchResultsPage results = homePage.searchProduct(product);

        int count = results.getResultCount();
        Assert.assertTrue(count > 0, "Expected results for '" + product + "' but found " + count);
    }
}