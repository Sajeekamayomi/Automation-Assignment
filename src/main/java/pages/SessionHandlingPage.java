package pages;


import org.openqa.selenium.By;
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

    WebDriver driver;

    //constructor
    public SessionHandlingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Locate the element using page factory.
    @FindBy(name = "continueSession")
    public WebElement continueButton;

    //By locator used for safe presence check and avoid NoSuchElementException.
    private final By continueButtonBy = By.name("continueSession");


    //Session handling page - Click the continue button to proceed and terminate any existing sessions.
    public void handleSessionIfPresent() {

        //findElements - check if the button is present
        List<WebElement> buttons = driver.findElements(continueButtonBy);

        if (!buttons.isEmpty()) {
            //Wait until the button is clickable.
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
            System.out.println("Session Conflict Detected - Clicked 'Continue' Button");
        } else {
            System.out.println("No Session Conflict");
        }


    }
}
