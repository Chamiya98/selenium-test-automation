package com.prodoscore.utils;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return JsonDataReader.getLoginData();
    }

}
