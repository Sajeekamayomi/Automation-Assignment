package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sajeekam on 5/9/2025
 */

public class LandingPage {

    WebDriver driver;

    //constructor
    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //------------Locate the elements-------------

    @FindBy(xpath = "//h6[contains(text(), 'Knocen Knowledge Management System')]")
    public WebElement subTitle;


    //Verify the login success
    public String loginSuccess() {
        return subTitle.getText();
    }


}
