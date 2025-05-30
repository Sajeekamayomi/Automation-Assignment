package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sajeekam on 5/30/2025
 */

public class PopUpPage {

    //driver variable for this class
    WebDriver driver;

    public PopUpPage(WebDriver driver) {
        this.driver = driver;

        //Page Factory
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[normalize-space()='Yes']")
    public WebElement yesButton;


    public void clickPopUpYesButton() {
        yesButton.click();
    }

}
