package utility;


import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Created by sajeekam on 5/20/2025
 */


public class RetryAnalyzer implements IRetryAnalyzer {

    int failedCount = 0;
    int maxRetryCunt = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if(failedCount<maxRetryCunt){
            failedCount++;
            return true;  //Retry the Test
        }
        return false;
    }
}
