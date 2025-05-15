package testcases;


import base.BaseClass;
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

public class TC001_LoginUserTest extends BaseClass {


    @Test
    public void LoginUserTest() {
        CommonFunctions commonFunctions = new CommonFunctions(driver);
        commonFunctions.login("johnd@eurokool.com", "Abcd1234");
        commonFunctions.handlingSession();
        commonFunctions.verifyLoginSuccess();
        commonFunctions.logout();

    }


}
