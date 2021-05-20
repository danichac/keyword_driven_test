package com.danic.keyword_driven_test.utilities.reporting;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.danic.keyword_driven_test.utilities.Screenshot;

public class TestListener implements ITestListener {
    public void onStart(ITestContext context) {
        System.out.println("*** Test Suite [" + context.getName() + "] started ***");
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite [" + context.getName() + "] ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        System.out.println(("*** Running test method [" + result.getMethod().getMethodName() + "]..."));
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("*** Executed [" + result.getMethod().getMethodName() + "] test successfully...");
        ExtentTestManager.getTest().log(Status.PASS, "Test passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("*** Test execution [" + result.getMethod().getMethodName() + "] failed...");
        ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        Screenshot.takeScreenshot(driver, result.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("*** [Test " + result.getMethod().getMethodName() + "] skipped...");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
    }

}
