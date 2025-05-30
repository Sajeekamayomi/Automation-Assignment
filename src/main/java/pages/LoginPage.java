package pages;


//import org.checkerframework.checker.signature.qual.FieldDescriptor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by sajeekam on 5/9/2025
 */

public class LoginPage {

    //driver variable for this class
    WebDriver driver;

    //constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;

        //Page Factory
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "username")
    public WebElement userNameText;

    @FindBy(id = "password")
    public WebElement passwordText;

    @FindBy(id = "kc-login")
    public WebElement loginButton;

    @FindBy(xpath = "//div[@class='wms-login-error']")
    public WebElement accountDisabledMessage;


    //div[@class='wms-login-error']

    public boolean isLoginPageDisplayed() {
        return userNameText.isDisplayed();
    }

    //Enter Username
    public void setUserName(String userName) {
        userNameText.sendKeys(userName);
    }


    //Enter Password
    public void setPassword(String password) {
        passwordText.sendKeys(password);
    }

    //Click Login Button
    public void clickLoginButton() {
        loginButton.click();
    }


    public String getAccountDisabledMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(accountDisabledMessage));
        return accountDisabledMessage.getText().trim();
    }

    public String getAccountDeletedMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(accountDisabledMessage));
        return accountDisabledMessage.getText().trim();
    }


}

