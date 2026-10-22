package com.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.base.BaseTest;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageTitle() {
        String title = getDriver().getTitle();
        Assert.assertTrue(title.contains("Flipskart"), "Unexpected title: " + title);
    }
}