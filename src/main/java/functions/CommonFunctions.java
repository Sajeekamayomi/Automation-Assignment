package functions;


import base.BaseClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.*;
import utility.DriverFactory;

import java.util.UUID;

import static utility.DriverFactory.getDriver;

/**
 * Created by sajeekam on 5/14/2025
 */

public class CommonFunctions extends BaseClass {

    private static final Logger logger = LogManager.getLogger(CommonFunctions.class);

    private String userRole; //
    private String generatedUsername;

    //private final AddNewUserPage addNewUserPage;

//    public CommonFunctions(String userRole) {
//
//        this.userRole = userRole;
//        this.addNewUserPage = new AddNewUserPage(getDriver());
//    }
//
//    public void setUserRole(String userRole) {
//
//        this.userRole = userRole;
//    }

//    public String getGeneratedUsername() {
//        return generatedUsername;
//    }

    public void login(String userName, String password) {

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }

    public void handlingSession() {
        SessionHandlingPage sessionHandlingPage = new SessionHandlingPage(getDriver());
        sessionHandlingPage.handleSessionIfPresent();
    }

    public void verifyLoginSuccess() {
        logger.info("Verify Login Success for role :" + userRole);
        LandingPage landingpage = new LandingPage(getDriver());
        String actualTitle = landingpage.getPageTitle();

        logger.info("Page Title After Login for role " + userRole + " : " + actualTitle);
        Assert.assertTrue(actualTitle.contains("Warehouse Management System"), "Login Failed for role [" + userRole + "] - Incorrect Page Title");
    }

    public void addNewUserDetails(String userName, String EmployeeFirstName, String EmployeeLastName, String Password, String ReEnterPassword, String newUserStatus, String DefaultWarehouse, String UserRole) {

        String randomUsername = "Sajee" + UUID.randomUUID().toString().replaceAll("[^a-zA-Z0-9]", "").substring(0, 5);
        this.generatedUsername = randomUsername;
        AddNewUserPage addNewUserPage = new AddNewUserPage(DriverFactory.getDriver());
        addNewUserPage.setUserName(randomUsername);
        addNewUserPage.setEmployeeFirstName(EmployeeFirstName);
        addNewUserPage.setEmployeeLastName(EmployeeLastName);
        addNewUserPage.setPassword(Password);
        addNewUserPage.setReEnterPassword(ReEnterPassword);
        addNewUserPage.selectStatus(newUserStatus);
        // addNewUserPage.selectDefaultWarehouse(DefaultWarehouse);
        // addNewUserPage.selectUserRole(UserRole);
    }


    public void clickCreateButton() {
        AddNewUserPage addNewUserPage = new AddNewUserPage(DriverFactory.getDriver());
        addNewUserPage.clickCreateButton();
    }


    public void logout() {
        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LogoutPage logoutPage = new LogoutPage(getDriver());
        logoutPage.clickLogOutButton();

        try {
            Thread.sleep(2000); // 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }}
