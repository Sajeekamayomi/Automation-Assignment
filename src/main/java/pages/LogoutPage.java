package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sajeekam on 5/14/2025
 */

public class LogoutPage {

    //driver variable for this class
    WebDriver driver;

    public LogoutPage(WebDriver driver){
        this.driver = driver;

        //Page Factory
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class='MuiBox-root css-3jv294']")
    public WebElement logOutButton;

    //Logout from the application
    public void clickLogOutButton(){
        logOutButton.click();
    }
}
