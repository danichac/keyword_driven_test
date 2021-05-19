package com.danic.keyword_driven_test.test_cases;

import com.danic.keyword_driven_test.excel_file.ExcelReader;
import com.danic.keyword_driven_test.operations.ReadObjects;
import com.danic.keyword_driven_test.operations.UIOperations;
import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

public class ParentTest {
    protected WebDriver driver;
    private ReadObjects readObjects;
    private Properties props;
    private UIOperations operations;

    @BeforeSuite
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        readObjects = new ReadObjects();
        props = readObjects.getObjects();
        operations = new UIOperations(driver);
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

    public void executeSteps(String sheetName) throws IOException {
        Sheet sheet = ExcelReader.readSpreadsheet(sheetName);
        int rowCount = sheet.getRowCount();

        for(int i = 1; i < rowCount; i++){
            Cell cell = sheet.getCellAt(0, i);

            if(cell.getTextValue().length() == 0){
                try {
                    operations.perform(props, sheet.getCellAt(1, i).getTextValue(),
                            sheet.getCellAt(2,i).getTextValue(), sheet.getCellAt(3,i).getTextValue(),
                            sheet.getCellAt(4,i).getTextValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("New Testcase-> " + cell.getTextValue() +" Started");
            }
        }
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }

}
