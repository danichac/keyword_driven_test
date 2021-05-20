package com.danic.keyword_driven_test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WidgetsPage {
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "//span[text()='Tool Tips']")
    private WebElement toolTipsLink;

    public WidgetsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickToolTips() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        toolTipsLink.click();
    }

    private void hoverToElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();

    }

    private String getElementToolTipText(By toolTipLocator){

        WebDriverWait wait = new WebDriverWait(driver,2);

        wait.until(ExpectedConditions.visibilityOfElementLocated(toolTipLocator));
        WebElement elementToolTip = driver.findElement(toolTipLocator);

        return elementToolTip.getText();
    }

    public String getButtonToolTipText(){
        hoverToElement(By.id("toolTipButton"));
        By toolTipLocator = By.cssSelector("#buttonToolTip>div.tooltip-inner");

        return getElementToolTipText(toolTipLocator);
    }

    public String getTextFieldToolTipText(){
        hoverToElement(By.id("toolTipTextField"));

        By toolTipLocator = By.cssSelector("#textFieldToolTip>div.tooltip-inner");
        return getElementToolTipText(toolTipLocator);
    }

    public String getAnchorTagToolTipText(){
        hoverToElement(By.xpath("//a[text()='Contrary']"));

        By toolTipLocator = By.cssSelector("#contraryTexToolTip>div.tooltip-inner");
        return getElementToolTipText(toolTipLocator);
    }
}
