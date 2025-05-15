package testcases;


import functions.CommonFunctions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.SessionHandlingPage;

import java.time.Duration;

/**
 * Created by sajeekam on 2/27/2025
 */

public class TC001_LoginUserTest {

    WebDriver driver;

    @BeforeMethod
    public void openPage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://qa.knocen.com/keycloak/realms/mdmsp/protocol/openid-connect/auth?client_id=knocen-frontend&redirect_uri=https%3A%2F%2Fqa.knocen.com&state=aafbb46a-7299-4018-99ea-bc798cdb0476&response_mode=fragment&response_type=code&scope=openid&nonce=36f60ab1-416a-4e65-89cc-f3d214a98472");
    }

    @Test
    public void LoginUserTest() {
        CommonFunctions commonFunctions = new CommonFunctions(driver);
        commonFunctions.login("johnd@eurokool.com", "Abcd1234");
        commonFunctions.handlingSession();
        commonFunctions.verifyLoginSuccess();
        commonFunctions.logout();

    }


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }


}
