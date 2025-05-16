package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.PropertyFileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * Created by sajeekam on 5/15/2025
 */

public class BaseClass {

    public WebDriver driver;
    public Properties prop;

    @BeforeMethod
    public void openPage() throws IOException {

//        //Data retrieve from property file
//        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir" +
//                "\\src\\test\\resources\\config.properties"));
//        prop = new Properties();
//        prop.load(fileInputStream);

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
        }


        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(implicit_wait)));
        driver.get(appURL);
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
