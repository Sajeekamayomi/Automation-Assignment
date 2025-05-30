package pages;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Created by sajeekam on 5/9/2025
 */

public class SessionHandlingPage {


    private static final Logger logger = LogManager.getLogger(SessionHandlingPage.class);


    WebDriver driver;

    // Use By locator instead of @FindBy to delay locating until needed
    private final By continueButtonBy = By.xpath("//button[normalize-space()='CONTINUE']");

    //constructor
    public SessionHandlingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    //Locate the element using page factory.
//    @FindBy(xpath = "//button[normalize-space()='CONTINUE']")
//    public WebElement continueButton;


    //Session handling page - Click the continue button to proceed and terminate any existing sessions.
    public void handleSessionIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Check if the CONTINUE button appears within 5 seconds
            WebElement continueButton = wait.until(ExpectedConditions.presenceOfElementLocated(continueButtonBy));

            // Wait for it to be visible and clickable
            wait.until(ExpectedConditions.visibilityOf(continueButton));
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));

            continueButton.click();

            logger.info("Session Conflict Detected - Clicked 'Continue' Button");

        } catch (TimeoutException e) {
            logger.info("No Session Conflict Detected");

        } catch (Exception ex) {
            logger.error("Error handling session conflict: " + ex.getMessage(), ex);
        }
    }
}
