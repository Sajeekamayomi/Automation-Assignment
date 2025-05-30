package base;


import com.aventstack.chaintest.service.ChainPluginService;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utility.DriverFactory;
import utility.PropertyFileReader;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static utility.DriverFactory.getDriver;


/**
 * Created by sajeekam on 5/15/2025
 */

public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    // Called once per suite via TestNG
    @BeforeSuite
    public void beforeSuite(){
        cleanScreenshots();
        cleanLogs();
    }

        //Add More details to the report
        //Executed pc name
        // ChainPluginService.getInstance().addSystemInfo("Tester", System.getProperty("user.name"));
        //Executed Browser
        //ChainPluginService.getInstance().addSystemInfo("Browser", "chrome");


    // Called before each scenario
   // @BeforeMethod(groups = {"smoke", "regression"})
//    public void openPage() {
//
//        //Data retrieve from property file
//        // PropertyFileReader propertyFileReader = new PropertyFileReader();
//        String browser = PropertyFileReader.getInstance().getProperty("config", "browser");
//        String appURL = PropertyFileReader.getInstance().getProperty("config", "App_Url");
//        long implicit_wait = Long.parseLong(PropertyFileReader.getInstance().getProperty("config", "implicit_wait"));
//
//        DriverFactory.initializeDriver(browser);
//        logger.info(("Test case Automating with : " + browser));
//
//        getDriver().manage().window().maximize();
//        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit_wait));
//        getDriver().get(appURL);
//    }

    // Called after each scenario
    //@AfterMethod(groups = {"smoke", "regression"})
    public void closeBrowser() {
        DriverFactory.quitDriver();
    }


    public byte[] takeScreenshot() {
       TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        return takesScreenshot.getScreenshotAs(OutputType.BYTES);

    }

    //Clean Screenshot
    private void cleanScreenshots(){
        try {
            String screenshotFolderPath = System.getProperty("user.dir") + "/test-output/chaintest/resources";
            FileUtils.cleanDirectory(new File(screenshotFolderPath));
            logger.info("Screenshot Folder Cleaned  : test-output/chaintest");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Clean Logs
    private void cleanLogs(){
        //Delete the existing log
        try {
            String logFilePath = "logs/automation.log";
            File logfile = new File(logFilePath);
            if (logfile.exists()) {
                FileUtils.write(logfile, "", false);
                logger.info("Log File Cleaned : " + logFilePath);
            } else {
                FileUtils.touch(logfile);
                logger.info("Log file created " + logFilePath);
            }
        } catch (IOException e) {
            logger.error("Error cleaning or creating the log file : " + e.getMessage());
        }
    }

}
