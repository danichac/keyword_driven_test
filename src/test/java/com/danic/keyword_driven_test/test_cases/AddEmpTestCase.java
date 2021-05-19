package com.danic.keyword_driven_test.test_cases;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

public class AddEmpTestCase extends ParentTest{

    @Test(dependsOnMethods = {"loginTest"})
    public void addEmpTest(){
        try {
            executeSteps("AddEmployeeTestCase");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(driver.getCurrentUrl());
    }
}
