package stepdefinitions;


import functions.CommonFunctions;
import io.cucumber.java.en.Then;

/**
 * Created by sajeekam on 5/28/2025
 */

public class LogOutUser {

    CommonFunctions commonFunctions = new CommonFunctions();


    @Then("the user logs out")
    public void the_user_logs_out() {
        commonFunctions.logout();
    }
}
