package com.framework.data;

import org.testng.annotations.DataProvider;

public class SearchData {

    @DataProvider(name = "products")
    public static Object[][] products() {
        return new Object[][] {
            { "iphone" },
            { "laptop" },
            { "headphones" }
        };
    }
}