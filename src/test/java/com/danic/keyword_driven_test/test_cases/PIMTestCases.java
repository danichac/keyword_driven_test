package com.danic.keyword_driven_test.test_cases;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

public class PIMTestCases extends ParentTest{

    @Test(dependsOnMethods = {"addEmpTest"})
    public void searchEmpTest(){
        try {
            executeSteps("SearchEmployee");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(dependsOnMethods = {"loginTest"})
    public void addEmpTest(){
        try {
            executeSteps("AddEmployeeTestCase");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(driver.getCurrentUrl());
    }
    
    @Test
    public void loginTest() {
        try {
            executeSteps("LogInTestCase");
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(driver.getCurrentUrl(), "https://opensource-demo.orangehrmlive.com/index.php/dashboard");

    }
}
