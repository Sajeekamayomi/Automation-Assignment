package pages;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by sajeekam on 5/27/2025
 */

public class UserManagementPage {


    //driver variable for this class
    WebDriver driver;
    private WebDriverWait wait;

    //constructor
    public UserManagementPage(WebDriver driver) {
        this.driver = driver;

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Page Factory
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//button[normalize-space()='Add New User']")
    public WebElement addNewUserButton;

    @FindBy(xpath = "//a[@class='MuiButtonBase-root MuiListItemButton-root MuiListItemButton-gutters MuiListItemButton-root MuiListItemButton-gutters css-1frk1bx']")
    public WebElement userManagementNavigationButton;

    @FindBy(xpath = "//input[@id='search']")
    public WebElement searchInputText;

    @FindBy(xpath = "//div[@role='alert']")
    public WebElement toastMessage;

    @FindBy(xpath = "//tbody/tr[1]/td[4]/button[2]")
    public WebElement suspendButton;

    @FindBy(xpath = "//tbody/tr[1]/td[4]/button[1]")
    public WebElement deleteButton;

    @FindBy(xpath = "//tbody/tr[1]/td[4]/button[3]")
    public WebElement editButton;

    @FindBy(xpath = "//th[4]")
    public WebElement statusColumn;

    public By editBtn = By.xpath("//button[@aria-label='Edit']");
    public By deleteBtn = By.xpath("//button[@aria-label='Delete']");
    public By suspendBtn = By.xpath("//button[@aria-label='Deactivate']");
    public By viewBtn = By.xpath("//button[@aria-label='View']");
    public By viewCloseBtn = By.xpath("//button[@aria-label='Close']");

    public By noRecordFoundMessage = By.xpath("//p[@class='MuiTypography-root MuiTypography-body1 css-1etoowk']");


    //p[@class='MuiTypography-root MuiTypography-body1 css-1etoowk']



    public By searchInputTxt = By.xpath("//input[@id='search']");

    public By firstName = By.xpath("//td[normalize-space()='Sajeeka Mayomir4']");


    //td[normalize-space()='Sajeeka Mayomir4']


    //Click Add New User Button
    public void clickAddNewUserButton() {
        addNewUserButton.click();
    }

    //Click user management navigation button
    public void clickUserManagementNavigationButton() {
        userManagementNavigationButton.click();
    }

    //Search using username
    public void searchUserByUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By searchLocator = By.xpath("//input[@id='search']");
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchLocator));


        searchBox.sendKeys(Keys.CONTROL + "a");
        searchBox.sendKeys(Keys.DELETE);

        searchBox.sendKeys(username);
        searchBox.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void searchUser() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            wait.until(ExpectedConditions.elementToBeClickable(searchInputText));
            searchInputText.clear();
            searchInputText.sendKeys("sajee");
            searchInputText.sendKeys(Keys.ENTER);

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table tbody tr")));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Search failed", e);
        }
    }

    public boolean isUserDisplayed(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(driver -> {
            List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
            System.out.println("Looking for username: " + username);
            System.out.println("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (!cells.isEmpty()) {
                    String cellText = cells.get(0).getText().trim();
                    System.out.println("Checking row - found username: " + cellText);
                    if (cellText.equalsIgnoreCase(username)) {
                        System.out.println("Match found!");
                        return true;
                    }
                } else {
                    System.out.println("Row has no <td> elements.");
                }
            }

            System.out.println("No matching username found.");
            return false;
        });

    }

    public String findFirstActiveUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table tbody tr")));

        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() < 4) continue; // Skip malformed rows

            String username = cells.get(0).getText();
            String status = cells.get(3).getText();

            if (username.toLowerCase().startsWith("sajee") && status.equalsIgnoreCase("Active")) {
                return username;
            }
        }
        return null;
    }

    public void suspendUserByUsername(String username) {
        List<WebElement> rows = driver.findElements(By.cssSelector("table tbody tr"));

        for (WebElement row : rows) {
            String currentUsername = row.findElement(By.xpath(".//td[1]")).getText();
            if (currentUsername.equalsIgnoreCase(username)) {
                // Try the most reliable way to locate the "Deactivate" button
                try {
                    WebElement suspendButton = row.findElement(By.xpath(".//button[@aria-label='Deactivate']"));
                    suspendButton.click();
                } catch (Exception e) {
                    throw new RuntimeException("Deactivate button not found for user: " + username, e);
                }
                return;
            }
        }
        throw new RuntimeException("Could not find user row for: " + username);
    }


    public void deleteUserByUsername(String username) {
        String xpath = "//table//tr[td[1][text()='" + username + "']]//button[@aria-label='Delete']";
        try {
            WebElement deleteButton = driver.findElement(By.xpath(xpath));
            deleteButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Confirm']")));
            confirmButton.click();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("User not found for deletion: " + username, e);
        }
    }


    public boolean isSuccessToastDisplayed(String expectedKeyword) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(toastMessage));
            String toastText = toastMessage.getText();
            System.out.println("Toast message: " + toastText);
            return toastText.toLowerCase().contains(expectedKeyword.toLowerCase());
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickSuspendButtonR() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dynamicSuspendButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tbody/tr[1]/td[4]/button[2]")
        ));

        dynamicSuspendButton.click();
    }

    public void clickDeleteButtonR() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement dynamicSuspendButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tbody/tr[1]/td[4]/button[1]")
        ));

        dynamicSuspendButton.click();
    }

    public boolean statusInactive() {
        WebElement statusCell = driver.findElement(By.cssSelector("table tbody tr:first-child td:nth-child(4)"));
        return statusCell.getText().trim().equalsIgnoreCase("Inactive");
    }

    public void clickEditButtonForUser(String userEmail) {
        try {

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

            String xpath = "//tr[td[contains(text(), '" + userEmail + "')]]/td[last()]/button[3]";
            WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            editButton.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstName")));
        } catch (Exception e) {
            System.out.println("Failed to click edit button for user: " + userEmail);
            e.printStackTrace();
            throw new RuntimeException("Edit user failed", e);
        }
    }


    public boolean verifyUserDetails() {
        String userEmail = "sajeeka@wms.app";
        String expectedFirstName = "SajeekaUpdated";
        String expectedStatus = "Inactive";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Refresh search
        searchUserByUsername(userEmail);

        WebElement updatedRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[td[contains(text(), '" + userEmail + "')]]")
        ));

        String actualFirstName = updatedRow.findElement(By.xpath("td[2]")).getText();
        String actualStatus = updatedRow.findElement(By.xpath("td[4]")).getText();

        return actualFirstName.equals(expectedFirstName) && actualStatus.equals(expectedStatus);
    }


    public void clickEditButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();
    }

    public void clickDeleteButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();
    }

    public void clickSuspendButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(suspendBtn)).click();
    }

    public void clickViewButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(viewBtn)).click();

    }

    public void clickViewCloseButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(viewCloseBtn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputTxt));

    }

    public boolean isFirstNameUpdated(String expectedFirstName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[starts-with(normalize-space(), '" + expectedFirstName + "')]")
            ));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getFirstUsernameFromTable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By firstUsernameLocator = By.xpath("//table[@class='MuiTable-root css-134cpbz']/tbody/tr/th[1]");
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstUsernameLocator));
        return usernameElement.getText();
    }


    public boolean isUsernamePresentInTable(String username) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//table[@class='MuiTable-root css-134cpbz']//th[normalize-space()='" + username + "']")));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    public String isUserSuspended() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By firstStatusLocator = By.xpath("//table[@class='MuiTable-root css-134cpbz']/tbody/tr/td[3]");
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstStatusLocator));
        return statusElement.getText().trim();
    }


    public String getDisplayedUsernameInViewPage(String expectedUsername) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String dynamicXPath = String.format("//h6[normalize-space()='%s']", expectedUsername);

        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(dynamicXPath)
        ));
        return usernameElement.getText().trim();
    }

    public void waitForUserViewPage(String expectedUsername) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String dynamicXPath = String.format("//h6[normalize-space()='%s']", expectedUsername);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));
    }





}
