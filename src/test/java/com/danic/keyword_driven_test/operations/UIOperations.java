package com.danic.keyword_driven_test.operations;

import com.danic.keyword_driven_test.utilities.data_sharing.DataShare;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class UIOperations {
    private final WebDriver driver;
    private final Actions actions;
    private final WebDriverWait wait;
    private final DataShare dataShareService;

    public UIOperations(WebDriver driver, DataShare dataShareService) {
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver,5);
        this.dataShareService = dataShareService;
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
            case "ASSERT":
                dataShareService.addOperationResult(objectName,
                driver.findElement(this.getObject(props, objectName, objectType)).getText());
                break;
            case "HOVER":
                actions.moveToElement(driver.findElement(this.getObject(props, objectName, objectType)))
                        .perform();
                break;
            case "SLEEP":
                long milliseconds = Long.valueOf(value);
                Thread.sleep(milliseconds);
                break;
            case "WAIT":
                wait.until(ExpectedConditions.visibilityOfElementLocated(this.getObject(props, objectName, objectType)));
                break;
            case "WAITTEXT":
                wait.until(ExpectedConditions.textToBe(this.getObject(props, objectName, objectType), value));
                break;
            case "PRINT":
                System.out.printf("Object: %s | Text: %s\n",objectName,
                        driver.findElement(this.getObject(props, objectName, objectType)).getText());
                break;
            case "MAXIMIZE":
                driver.manage().window().maximize();
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
            case "ID":
                return By.id(props.getProperty(objectName));
            default:
                throw new Exception("Incorrect object type.");
        }
    }
}
