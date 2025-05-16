package base;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utility.PropertyFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


/**
 * Created by sajeekam on 5/15/2025
 */

public class BaseClass {

    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    public WebDriver driver;
    public Properties prop;

    //Cleaned the log file
    @BeforeSuite
    public void beforeSuite() {
        String logFilePath = "logs/automation.log";
        File logfile = new File(logFilePath);

        try {
            if (logfile.exists()) {
                FileUtils.write(logfile, "", false);
                logger.info("Log File Cleaned : " + logFilePath);
            } else {
                FileUtils.touch(logfile);
                logger.info("Log file created " + logFilePath);
            }
        } catch (IOException e) {
            logger.error("Error cleaning or creatig the log file : " + e.getMessage());
        }
    }

    //Open the Web Application
    @BeforeMethod
    public void openPage() throws IOException {

        //Data retrieve from property file
        PropertyFileReader propertyFileReader = new PropertyFileReader();
        String browser = propertyFileReader.getProperty("config", "browser");
        String appURL = propertyFileReader.getProperty("config", "App_Url");
        String implicit_wait = propertyFileReader.getProperty("config", "implicit_wait");


        //Cross Browser

        switch (browser.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("Browser Not Supported");
                return;
        }

        logger.info(("Test case Automating with :" + browser));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicit_wait)));
        driver.get(appURL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
