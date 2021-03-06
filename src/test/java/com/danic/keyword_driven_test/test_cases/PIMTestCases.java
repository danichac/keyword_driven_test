package com.danic.keyword_driven_test.test_cases;

import com.danic.keyword_driven_test.utilities.Screenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

public class PIMTestCases extends ParentTest{

    @Test(dependsOnMethods = {"searchEmpTest"}, testName = "deleteEmpTest")
    public void deleteEmpTest() throws IOException {
        executeSteps("DeleteEmployee");
        makeAssertions("DeleteEmployee");
    }

    @Test(dependsOnMethods = {"addEmpTest"}, testName = "searchEmployee")
    public void searchEmpTest() throws IOException {
        executeSteps("SearchEmployee");
        makeAssertions("SearchEmployee");
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void addEmpTest() throws IOException {
        executeSteps("AddEmployeeTestCase");
    }
    
    @Test
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
