package com.danic.keyword_driven_test.operations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Locale;
import java.util.Properties;

public class UIOperations {
    private WebDriver driver;

    public UIOperations(WebDriver driver) {
        this.driver = driver;
    }

    public void perform(Properties props, String action, String objectName, String objectType,
                        String value) throws Exception {

        switch (action.toUpperCase()){
            case "CLICK":
                driver.findElement(this.getObject(props, objectName, objectType)).click();
                break;
            case "SETTEXT":
                driver.findElement(this.getObject(props, objectName, objectType)).sendKeys(value);
                break;
            case "GOTOURL":
                driver.get(value);
                break;
            case "GETTEXT":
                driver.findElement(this.getObject(props, objectName, objectType)).getText();
                break;
        }
    }

    private By getObject(Properties props, String objectName, String objectType) throws Exception {
        switch (objectType.toUpperCase()){
            case "XPATH":
                return By.xpath(props.getProperty(objectName));
            case "CLASSNAME":
                return By.className(props.getProperty(objectName));
            case "NAME":
                return By.name(props.getProperty(objectName));
            case "CSS":
                return By.cssSelector(props.getProperty(objectName));
            case "LINK":
                return By.linkText(props.getProperty(objectName));
            case "PARTIALLINK":
                return By.partialLinkText(props.getProperty(objectName));
            default:
                throw new Exception("Incorrect object type.");
        }
    }
}
