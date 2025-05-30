package stepdefinitions;


import functions.CommonFunctions;
import io.cucumber.java.en.Given;
import utility.PropertyFileReader;

/**
 * Created by sajeekam on 5/28/2025
 */

public class LoginUser  {



    @Given("the user logs in as {string}")
    public void the_user_logs_in_as(String role) {

        CommonFunctions commonFunctions = new CommonFunctions();

        String username = "";
        String password = "";

        // Read from property file based on role
        switch (role.toLowerCase()) {
            case "client admin":
                username = PropertyFileReader.getInstance().getProperty("testData", "clientadmin_username");
                password = PropertyFileReader.getInstance().getProperty("testData", "clientadmin_password");
                break;
            case "super admin":
                username = PropertyFileReader.getInstance().getProperty("testData", "superadmin_username");
                password = PropertyFileReader.getInstance().getProperty("testData", "superadmin_password");
                break;
            default:
                throw new IllegalArgumentException("Unsupported role: " + role);
        }


        commonFunctions.login(username, password);
        commonFunctions.handlingSession();
        commonFunctions.verifyLoginSuccess();

    }
}
