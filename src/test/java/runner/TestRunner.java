package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import utility.DriverFactory;

/**
 * Created by sajeekam on 5/23/2025
 */

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefinitions",
        plugin = {
                "pretty",
                "html:target/HTMLReports/htmlreport.html",
                "json:target/cucumber-reports/Cucumber.json"
        },
        tags = "@smoke",
        monochrome = true


)
public class TestRunner extends AbstractTestNGCucumberTests {

    @AfterSuite
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}
