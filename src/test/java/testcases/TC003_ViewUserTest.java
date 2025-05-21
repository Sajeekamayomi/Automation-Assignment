package testcases;


import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import functions.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import pages.ViewUser;
import utility.PropertyFileReader;
import utility.RetryAnalyzer;

/**
 * Created by sajeekam on 5/18/2025
 */

public class TC003_ViewUserTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC003_ViewUserTest.class);


    @Test(groups = "smoke")
    public void viewUserTest() {

        //Data retrieve from property file
        String username = PropertyFileReader.getInstance().getProperty("testData", "SuperAdminUsername");
        String password = PropertyFileReader.getInstance().getProperty("testData", "SuperAdminPassword");

        logger.info("---------Starting TC002----------");

        CommonFunctions commonFunctions = new CommonFunctions();
        ChainTestListener.embed(takeScreenshot(), "image/png");
        commonFunctions.login(username, password);
        ChainTestListener.embed(takeScreenshot(), "image/png");
        commonFunctions.handlingSession();
        ViewUser viewUser = new ViewUser(getDriver());
        viewUser.viewUsers();
        ChainTestListener.embed(takeScreenshot(), "image/png");
        commonFunctions.logout();

        logger.info("----------Ending TC002----------");
    }


}
