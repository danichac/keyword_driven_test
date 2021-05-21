package com.danic.keyword_driven_test.test_cases;

import com.danic.keyword_driven_test.utilities.Screenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class TimeTestCases extends ParentTest{

    @Test(dependsOnMethods = {"loginTest"}, testName = "punchInTest")
    public void punchInTest() throws IOException {
        executeSteps("PunchIn");
        makeAssertions("PunchIn");
    }

    @Test(dependsOnMethods = {"punchInTest"}, testName = "punchOutTest")
    void punchOutTest() throws IOException {
        executeSteps("PunchOut");
        makeAssertions("PunchOut");
    }

    @Test(testName = "loginTest")
    public void loginTest() throws IOException {
        executeSteps("LogInTestCase");

        assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/index.php/dashboard");
    }

    @AfterMethod
    public void screenShotOnFail(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            String callerName = result.getName();
            Screenshot.takeScreenshot(driver, callerName);
        }
    }
}
