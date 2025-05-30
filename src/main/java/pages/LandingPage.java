package pages;


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

    //Return page title after login
    public String getPageTitle (){
        return driver.getTitle();
    }


}
