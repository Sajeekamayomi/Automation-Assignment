package stepdefinitions;


import functions.CommonFunctions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.LoginPage;
import utility.DriverFactory;
import utility.PropertyFileReader;

/**
 * Created by sajeekam on 5/29/2025
 */

public class SuspendedUserLogin {


    @When("the user attempts to login with suspended credentials")
    public void the_user_attempts_to_login_with_suspended_credentials() {
        String username = PropertyFileReader.getInstance().getProperty("testData", "suspend_user_username");
        String password = PropertyFileReader.getInstance().getProperty("testData", "suspend_user_password");
        CommonFunctions commonFunctions = new CommonFunctions();
        commonFunctions.login(username, password);
    }

    @Then("the system should show a account disabled message")
    public void the_system_should_show_a_account_disabled_message() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String expected = "Account is disabled, contact your administrator.";
        String actual = loginPage.getAccountDisabledMessage();
        Assert.assertEquals(actual, expected, "Account disabled message mismatch!");
    }
}
