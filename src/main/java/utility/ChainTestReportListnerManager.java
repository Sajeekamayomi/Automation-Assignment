package utility;


import base.BaseClass;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Created by sajeekam on 5/17/2025
 */

public class ChainTestReportListnerManager extends BaseClass implements ITestListener, IAnnotationTransformer {

//    @Override
//    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
//        annotation.setRetryAnalyzer(RetryAnalyzer.class);
//    }

    // Get actual scenario name
    private String getScenarioName(ITestResult result) {
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            Object param = parameters[0];
            if (param instanceof io.cucumber.testng.PickleWrapper) {
                return ((io.cucumber.testng.PickleWrapper) param).getPickle().getName();
            }
        }
        return result.getMethod().getMethodName();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String scenarioName = getScenarioName(result);
        result.setAttribute("name", scenarioName);
        ChainTestListener.log("Started Test Execution : " + scenarioName);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        ChainTestListener.log(getScenarioName(result) + " : Testcase Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ChainTestListener.log(getScenarioName(result) + " - Testcase Failed");
        ChainTestListener.embed(takeScreenshot(), "image/png");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ChainTestListener.log(getScenarioName(result) + " : Testcase Skipped");
    }


}
