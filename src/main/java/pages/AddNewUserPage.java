package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Created by sajeekam on 5/27/2025
 */

public class AddNewUserPage {

    //driver variable for this class
    WebDriver driver;

    //constructor
    public AddNewUserPage(WebDriver driver) {
        this.driver = driver;

        //Page Factory
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "username")
    public WebElement userNameText;

    @FindBy(id = "firstName")
    public WebElement employeeFirstNameText;

    @FindBy(id = "lastName")
    public WebElement employeeLastNameText;

    @FindBy(id = "password")
    public WebElement passwordText;

    @FindBy(id = "passwordCom")
    public WebElement reEnterPasswordText;

    @FindBy(xpath = "//div[@id='status']")
    public WebElement statusDropDown;

    @FindBy(id = "//div[@id='defaultWarehouse']")
    public WebElement defaultWarehouseDropDown;

    @FindBy(xpath = "(//input[@id='roleGroups'])[10]")
    public WebElement userRoleCheckBox;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    public WebElement createButton;


    //Enter Username
    public void setUserName(String userName) {
        userNameText.sendKeys(userName);
    }

    //Enter Employee First Name
    public void setEmployeeFirstName(String EmployeeFirstName) {
        employeeFirstNameText.sendKeys(EmployeeFirstName);
    }

    //Enter Employee Last Name
    public void setEmployeeLastName(String EmployeeLastName) {
        employeeLastNameText.sendKeys(EmployeeLastName);
    }

    //Enter Password
    public void setPassword(String password) {
        passwordText.sendKeys(password);
    }

    //Enter Re Enter Password
    public void setReEnterPassword(String ReEnterPassword) {
        reEnterPasswordText.sendKeys(ReEnterPassword);
    }

    //Select Status
    public void selectStatus(String newUserStatus) {

        // Click the dropdown to show the options
        statusDropDown.click();

        // Wait for the dropdown options to be visible and select the one with matching text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//li[normalize-space()='" + newUserStatus + "']")));

        // Click the option
        option.click();
    }

    //Select Default Warehouse
    public void selectDefaultWarehouse(String defaultWarehouse) {

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", defaultWarehouseDropDown);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//body//li[normalize-space()='" + defaultWarehouse + "']")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
        option.click();
    }


    //Select User Role
    public void selectUserRole(String UserRole) {
        userRoleCheckBox.click();
    }


    //Click Create Button
    public void clickCreateButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(createButton)).click();
    }


}
