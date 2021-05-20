package com.danic.keyword_driven_test.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    @FindBy(how = How.XPATH, using ="//h5[text()='Elements']")
    private WebElement elements;
    @FindBy(how = How.XPATH, using ="//h5[text()='Forms']")
    private WebElement forms;
    @FindBy(how = How.XPATH, using ="//h5[text()='Alerts, Frame & Windows']")
    private WebElement alerts;
    @FindBy(how = How.XPATH, using ="//h5[text()='Widgets']")
    private WebElement widgets;
    @FindBy(how = How.XPATH, using ="//h5[text()='Interactions']")
    private WebElement interactions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        //This executor is used in order to scroll down to be able to click on the elements
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        PageFactory.initElements(driver, this);
    }

    public WidgetsPage clickWidgetPage(){
        widgets.click();
        return new WidgetsPage(driver);
    }
}
