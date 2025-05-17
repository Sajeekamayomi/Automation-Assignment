package utility;


import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Created by sajeekam on 5/17/2025
 */

public class ChainTestReportListnerManager extends BaseClass implements ITestListener {

   public void onTestStart(ITestResult result){
       ChainTestListener.log("Started Test Execution : "+ result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
   }

   public void onTestSuccess (ITestResult result){
       ChainTestListener.log(result.getName() + " : Testcase Passed");
   }

   public void onTestFailure(ITestResult result){
       ChainTestListener.log(result.getName() + " - Testcase Failed");
       ChainTestListener.embed(takeScreenshot(),"image/png");
   }

   public void onTestSkipped(ITestResult result){
       ChainTestListener.log(result.getName()  + " : Testcase Skipped");
   }


}
