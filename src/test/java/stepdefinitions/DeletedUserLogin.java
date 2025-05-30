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

public class DeletedUserLogin {

    @When("the user attempts to login with deleted credentials")
    public void the_user_attempts_to_login_with_deleted_credentials() {
        String username = PropertyFileReader.getInstance().getProperty("testData", "deleted_user_username");
        String password = PropertyFileReader.getInstance().getProperty("testData", "deleted_user_password");
        CommonFunctions commonFunctions = new CommonFunctions();
        commonFunctions.login(username, password);
    }

    @Then("the system should show an account deleted message")
    public void the_system_should_show_an_account_deleted_message() {
        LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
        String expected = "Invalid username or password.";
        String actual = loginPage.getAccountDisabledMessage();
        Assert.assertEquals(actual, expected, "Account deleted message mismatch!");
    }

}
