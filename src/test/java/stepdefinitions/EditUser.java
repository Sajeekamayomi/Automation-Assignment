package stepdefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.AddNewUserPage;
import pages.EditUserPage;
import pages.UserManagementPage;
import utility.DriverFactory;

/**
 * Created by sajeekam on 5/30/2025
 */

public class EditUser {

    @Given("the user searches for the user")
    public void the_user_searches_for_the_user() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());

        userManagementPage.clickUserManagementNavigationButton();
        userManagementPage.clickAddNewUserButton();
        userManagementPage.searchUserByUsername("sajeeka@wms.app");
    }

    @When("the user clicks the edit button for the user and navigates to the edit page")
    public void the_user_clicks_the_edit_button_for_the_user_and_navigates_to_the_edit_page() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.clickEditButton();
    }

    @When("the user updates the first name and status")
    public void the_user_updates_the_first_name_and_status() {
        EditUserPage editUserPage = new EditUserPage(DriverFactory.getDriver());
        editUserPage.updateFirstNameAndStatus();
    }

    @When("the user clicks the update button on edit page")
    public void the_user_clicks_the_update_button_on_edit_page() {
        EditUserPage editUserPage = new EditUserPage(DriverFactory.getDriver());
        editUserPage.clickUpdateButton();
    }

    @Then("the user's details should be updated successfully")
    public void the_user_s_details_should_be_updated_successfully() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.searchUserByUsername("sajeeka@wms.app");
        boolean isUpdated = userManagementPage.isFirstNameUpdated("SajeekaUpdated");
        Assert.assertTrue(isUpdated, "The user's first name was not updated successfully.");
    }

}
