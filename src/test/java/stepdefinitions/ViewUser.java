package stepdefinitions;


import base.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.UserManagementPage;
import utility.DriverFactory;

/**
 * Created by sajeekam on 5/30/2025
 */

public class ViewUser {

    private String capturedUsername;

    @When("I search for the user and navigate to the view user page")
    public void i_search_for_the_user_and_navigate_to_the_view_user_page() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.searchUserByUsername("sajee");

        capturedUsername = userManagementPage.getFirstUsernameFromTable();
        System.out.println("Captured Username: " + capturedUsername);

        userManagementPage.clickViewButton();
    }

    @Then("I should see the user details are displayed")
    public void i_should_see_the_user_details_are_displayed() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        userManagementPage.waitForUserViewPage(capturedUsername);
        String displayedUsername = userManagementPage.getDisplayedUsernameInViewPage(capturedUsername);
        System.out.println("Displayed Username: " + displayedUsername);
        userManagementPage.waitForUserViewPage(capturedUsername);



        Assert.assertEquals(displayedUsername, capturedUsername, "Displayed user details do not match captured username.");
        userManagementPage.clickViewCloseButton();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BaseClass base = new BaseClass();
        byte[] screenshotBytes = base.takeScreenshot();


    }
}
