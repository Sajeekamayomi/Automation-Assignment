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


public class TC001_LoginUserTest_ClientAdmin extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC001_LoginUserTest_ClientAdmin.class);


    @Test(groups = "regression")
    public void LoginUserTest() {

        //Data retrieve from property file
        String username = PropertyFileReader.getInstance().getProperty("testData", "clientAdminUsername");
        String password = PropertyFileReader.getInstance().getProperty("testData", "ClientAdminPassword");

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
