package com.prodoscore.dataprovider;

import com.prodoscore.pages.utils.JsonDataReader;
import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "loginData")
    public static Object[][] loginData() {
        return JsonDataReader.getLoginData();
    }

}
