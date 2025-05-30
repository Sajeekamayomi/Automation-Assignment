package utility;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by sajeekam on 5/23/2025
 */

public class DriverFactory {

    //Handle Thread Safety in Parallel Execution
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    //Get the Web Driver
    public static WebDriver getDriver() {
        if (webDriverThreadLocal.get() == null) {
            System.out.println("WebDriver is not Initialized");
            throw new IllegalStateException("WebDriver is not Initialized");
        }

        return webDriverThreadLocal.get();
    }

    public static void initializeDriver(String browser) {


        //Cross Browser
        switch (browser.toLowerCase()) {
            case "chrome":
                webDriverThreadLocal.set(new ChromeDriver());
                break;
            case "edge":
                webDriverThreadLocal.set(new EdgeDriver());
                break;
            case "firefox":
                webDriverThreadLocal.set(new FirefoxDriver());
                break;
            default:
                System.out.println("Browser Not Supported");
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

    }

    public static void quitDriver() {
        if (webDriverThreadLocal.get() != null) {
            webDriverThreadLocal.get().quit();
            webDriverThreadLocal.remove();
        }
    }
}
