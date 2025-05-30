package setup;


import base.BaseClass;
import org.testng.annotations.BeforeSuite;

/**
 * Created by sajeekam on 5/25/2025
 */

public class TestSetup {

    @BeforeSuite
    public void suiteSetup() {
        new BaseClass().beforeSuite();
    }
}
