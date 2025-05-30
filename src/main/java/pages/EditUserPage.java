package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by sajeekam on 5/30/2025
 */

public class EditUserPage {


    //driver variable for this class
    WebDriver driver;

    //constructor
    public EditUserPage(WebDriver driver) {
        this.driver = driver;

        //Page Factory
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    public WebElement employeeFirstNameText;

    @FindBy(xpath = "//div[@id='status']")
    public WebElement statusDropDown;

    @FindBy(xpath = "//button[normalize-space()='Update']")
    public WebElement updateButton;

    public void updateFirstNameAndStatus() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for and update First Name
        wait.until(ExpectedConditions.visibilityOf(employeeFirstNameText));
        employeeFirstNameText.click();
        employeeFirstNameText.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        employeeFirstNameText.sendKeys(Keys.BACK_SPACE);
        employeeFirstNameText.sendKeys("SajeekaUpdated");

        // Ensure status dropdown is clickable and click to open it
        wait.until(ExpectedConditions.elementToBeClickable(statusDropDown)).click();

        // Wait and select "Inactive" from the dropdown options
        WebElement statusOption = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[normalize-space()='Inactive']"))
        );
        statusOption.click();
    }

    public void clickUpdateButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(updateButton)).click();
    }
}
