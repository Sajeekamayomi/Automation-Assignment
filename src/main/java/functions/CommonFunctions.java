package functions;


import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.LandingPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.SessionHandlingPage;

/**
 * Created by sajeekam on 5/14/2025
 */

public class CommonFunctions extends BaseClass {

    private static final Logger logger = LogManager.getLogger(CommonFunctions.class);

    //WebDriver driver;

//    public CommonFunctions(WebDriver driver) {
//        this.driver = driver;
//    }

    public void login(String userName, String password) {
        LoginPage loginPage = new LoginPage( getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }

    public void handlingSession() {
        SessionHandlingPage sessionHandlingPage = new SessionHandlingPage( getDriver());
        sessionHandlingPage.handleSessionIfPresent();
    }


    public void verifyLoginSuccess() {
        logger.info("Verify Login Success");
        LandingPage landingpage = new LandingPage( getDriver());
        String actualSubTitle = landingpage.loginSuccess();
        Assert.assertTrue(actualSubTitle.contains("jjj"), "Login Failed");
    }


    public void logout() {
        LogoutPage logoutPage = new LogoutPage( getDriver());
        logoutPage.clickLogOutButton();
    }
}
