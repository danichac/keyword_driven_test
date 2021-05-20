package com.danic.keyword_driven_test.page_tests;

import com.danic.keyword_driven_test.pages.WidgetsPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class WidgetsPageTest extends BaseTest{
    private WidgetsPage widgetsPage;

    @BeforeClass
    public void goToWidgetsPage(){
        widgetsPage = homePage.clickWidgetPage();
        widgetsPage.clickToolTips();
    }

    @Test
    public void testButtonTooltipText(){
        widgetsPage.clickToolTips();
        String expected = "You hovered over the Button";
        String actual = widgetsPage.getButtonToolTipText();
        assertEquals(actual, expected);
    }

    @Test
    public void testTextFieldToolTipText(){
        widgetsPage.clickToolTips();
        String expected = "You hovered over the text field";
        String actual = widgetsPage.getTextFieldToolTipText();
        assertEquals(actual, expected);
    }

    @Test
    public void testAnchorTagToolTipText(){
        widgetsPage.clickToolTips();
        String expected = "You hovered over the Contrary";
        String actual = widgetsPage.getAnchorTagToolTipText();
        assertEquals(actual, expected);
    }
}
