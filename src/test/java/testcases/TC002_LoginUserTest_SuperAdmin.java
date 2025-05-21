package testcases;


import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import functions.CommonFunctions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import utility.PropertyFileReader;
import utility.RetryAnalyzer;

/**
 * Created by sajeekam on 2/27/2025
 */


public class TC002_LoginUserTest_SuperAdmin extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC002_LoginUserTest_SuperAdmin.class);


    @Test(groups = "regression")
    public void LoginUserTest() {

        //Data retrieve from property file
        String username = PropertyFileReader.getInstance().getProperty("testData", "SuperAdminUsername");
        String password = PropertyFileReader.getInstance().getProperty("testData", "SuperAdminPassword");

        logger.info("---------Starting TC001----------");

        CommonFunctions commonFunctions = new CommonFunctions();
        ChainTestListener.embed(takeScreenshot(), "image/png");
        commonFunctions.login(username, password);
        ChainTestListener.embed(takeScreenshot(), "image/png");
        commonFunctions.handlingSession();
        ChainTestListener.embed(takeScreenshot(), "image/png");
        commonFunctions.verifyLoginSuccess();
        ChainTestListener.embed(takeScreenshot(), "image/png");
        commonFunctions.logout();

        logger.info("----------Ending TC001----------");

    }


}
