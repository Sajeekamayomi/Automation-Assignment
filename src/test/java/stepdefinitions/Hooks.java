package stepdefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utility.DriverFactory;
import utility.PropertyFileReader;

import java.time.Duration;

/**
 * Created by sajeekam on 5/28/2025
 */

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp(Scenario scenario) {
        logger.info("Starting Scenario: " + scenario.getName() + "-----------");

        String browser = PropertyFileReader.getInstance().getProperty("config", "browser");
        String appURL = PropertyFileReader.getInstance().getProperty("config", "App_Url");
        long implicit_wait = Long.parseLong(PropertyFileReader.getInstance().getProperty("config", "implicit_wait"));

        DriverFactory.initializeDriver(browser);

        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit_wait));
        DriverFactory.getDriver().get(appURL);
    }

    @After
    public void tearDown(Scenario scenario) {
        logger.info("Ending Scenario: " + scenario.getName() + " -----------");
        DriverFactory.quitDriver();
    }
}
