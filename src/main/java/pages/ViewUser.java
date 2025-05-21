package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by sajeekam on 5/18/2025
 */

public class ViewUser {


    WebDriver driver;

    //constructor
    public ViewUser(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //------------Locate the elements-------------

    @FindBy(id = "navMenuItemUserMgmt")
    public WebElement viewUserButton;


    //Click User Management Button
    public void viewUsers() {
        viewUserButton.click();


    }
}
