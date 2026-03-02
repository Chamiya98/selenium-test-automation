package com.prodoscore;

import com.prodoscore.pages.LoginPage;
import com.prodoscore.utils.ConfigReader;
import com.prodoscore.utils.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;
import java.util.Objects;


public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    SoftAssert softAssert;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseUrl"));
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void testLogin(Map<String, String> data) {
        loginPage = new LoginPage(driver);

        loginPage.enterUsername(data.get("username"));
        loginPage.enterPassword(data.get("password"));
        loginPage.clickLoginButton();

        validateLoginResult(data.get("expectedResult"));

        softAssert.assertAll();

    }

    private void validateLoginResult(String expectedResult) {

        if ("success".equalsIgnoreCase(expectedResult)) {

            softAssert.assertTrue(
                    Objects.requireNonNull(driver.getCurrentUrl()).contains("inventory"),
                    "Login was expected to succeed but failed"
            );

        } else {

            softAssert.assertEquals(
                    loginPage.getErrorMessage(),
                    expectedResult,
                    "Error message does not match expected"
            );
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


}
