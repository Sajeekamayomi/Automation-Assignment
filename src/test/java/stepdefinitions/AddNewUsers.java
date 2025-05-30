package stepdefinitions;


import functions.CommonFunctions;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.UserManagementPage;
import utility.DriverFactory;
import utility.PropertyFileReader;
import utility.TestContext;

import java.time.Duration;

/**
 * Created by sajeekam on 5/28/2025
 */

public class AddNewUsers {

    private static final Logger logger = LogManager.getLogger(AddNewUsers.class);
   // private CommonFunctions commonFunctions;


    @When("the user navigates to the Add New User page as {string}")
    public void the_user_navigates_to_the_add_new_user_page_as(String role) {

//        if (commonFunctions == null) {
//            commonFunctions = new CommonFunctions(role);
//        }

        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());
        if (role.equalsIgnoreCase("super admin")) {
            userManagementPage.clickUserManagementNavigationButton();
        }

        userManagementPage.clickAddNewUserButton();
    }

    @When("the user fills user details from property file for {string}")
    public void the_user_fills_user_details_from_property_file_for(String role) {

//        if (commonFunctions == null) {
//            commonFunctions = new CommonFunctions(role);
//        }

        CommonFunctions commonFunctions = new CommonFunctions();
        //Data retrieve from property file
        String newUserUserName = PropertyFileReader.getInstance().getProperty("testData", "new_user_username");
        String employeeFirstName = PropertyFileReader.getInstance().getProperty("testData", "employee_firstName");
        String employeeLastName = PropertyFileReader.getInstance().getProperty("testData", "employee_lastName");
        String newUserPassword = PropertyFileReader.getInstance().getProperty("testData", "new_user_password");
        String newUserReEnterPassword = PropertyFileReader.getInstance().getProperty("testData", "new_user_re_enter_password");
        String newUserStatus = PropertyFileReader.getInstance().getProperty("testData", "new_user_status");
        String defaultWarehouse = PropertyFileReader.getInstance().getProperty("testData", "default_Warehouse");
        String userRole = PropertyFileReader.getInstance().getProperty("testData", "user_role");
        commonFunctions.addNewUserDetails(newUserUserName, employeeFirstName, employeeLastName, newUserPassword, newUserReEnterPassword, newUserStatus, defaultWarehouse, userRole);
    }

    @When("clicks the create button")
    public void clicks_the_create_button() {
        CommonFunctions commonFunctions = new CommonFunctions();
        commonFunctions.clickCreateButton();

    }

    @Then("the user should be successfully created")
    public void the_user_should_be_successfully_created() {
        UserManagementPage userManagementPage = new UserManagementPage(DriverFactory.getDriver());

        boolean successToastVisible = userManagementPage.isSuccessToastDisplayed("success");
        Assert.assertTrue(successToastVisible, "Expected success toast message was not displayed!");
    }



}
