package com.danic.keyword_driven_test.test_cases;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

public class PIMTestCases extends ParentTest{

    @Test(dependsOnMethods = {"searchEmpTest"})
    public void deleteEmpTest() throws IOException {
        executeSteps("DeleteEmployee");
    }

    @Test(dependsOnMethods = {"addEmpTest"})
    public void searchEmpTest() throws IOException {
        executeSteps("SearchEmployee");
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
}
