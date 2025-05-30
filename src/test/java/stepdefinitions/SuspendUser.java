package stepdefinitions;


import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.PopUpPage;
import pages.UserManagementPage;
import utility.DriverFactory;

/**
 * Created by sajeekam on 5/28/2025
 */

public class SuspendUser {

    private String capturedUsername;

    @When("search user to suspend")
    public void search_user_to_suspend() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.searchUserByUsername("sajee");

        capturedUsername = userManagementPage.getFirstUsernameFromTable();
        System.out.println("Captured Username: " + capturedUsername);
    }

    @When("clicks the suspend button")
    public void clicks_the_suspend_button() {

        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.clickSuspendButton();
        PopUpPage popUpPage = new PopUpPage(DriverFactory.getDriver());
        popUpPage.clickPopUpYesButton();
    }

    @Then("check whether user is suspended")
    public void check_whether_user_is_suspended() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        String status = userManagementPage.isUserSuspended();
        Assert.assertEquals(status, "Inactive", "User was not suspended as expected.");

    }
}

