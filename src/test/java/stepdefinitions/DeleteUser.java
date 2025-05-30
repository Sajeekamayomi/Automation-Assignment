package stepdefinitions;


import functions.CommonFunctions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.PopUpPage;
import pages.UserManagementPage;
import utility.DriverFactory;
import utility.TestContext;

import java.time.Duration;

/**
 * Created by sajeekam on 5/28/2025
 */

public class DeleteUser {

    private String capturedUsername;
    private WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), Duration.ofSeconds(10));

    UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());

    @When("the user searches for an existing active user")
    public void the_user_searches_for_an_existing_active_user() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.searchUserByUsername("sajee");

        capturedUsername = userManagementPage.getFirstUsernameFromTable();
        System.out.println("Captured Username: " + capturedUsername);

    }

    @When("the user selects the user and clicks the delete button")
    public void the_user_selects_the_user_and_clicks_the_delete_button() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.clickDeleteButton();
        PopUpPage popUpPage = new PopUpPage(DriverFactory.getDriver());
        popUpPage.clickPopUpYesButton();
    }

    @Then("the user account should be removed from the list")
    public void the_user_account_should_be_removed_from_the_list() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.searchUserByUsername(capturedUsername);

        boolean isUserStillPresent = userManagementPage.isUsernamePresentInTable(capturedUsername);
        Assert.assertFalse(isUserStillPresent, "User was not deleted successfully and still appears in the list.");
    }

}

