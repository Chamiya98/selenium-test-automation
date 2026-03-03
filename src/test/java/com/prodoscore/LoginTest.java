package com.prodoscore;


import com.prodoscore.listeners.ExtentListener;
import com.prodoscore.pages.LoginPage;
import com.prodoscore.pages.models.LoginData;
import com.prodoscore.pages.utils.ConfigReader;
import com.prodoscore.dataprovider.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Objects;

@Listeners(ExtentListener.class)
public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;
    SoftAssert softAssert;


    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(ConfigReader.getProperty("baseUrl"));
        softAssert = new SoftAssert();
    }

    @Test(dataProvider = "loginData", dataProviderClass = TestData.class)
    public void testLogin(LoginData data) {
        loginPage = new LoginPage(driver);

        loginPage.login(data.getUsername(), data.getPassword());

        validateLoginResult(data.getExpectedResult());

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
