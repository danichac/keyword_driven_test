package com.danic.keyword_driven_test.test_cases;

import com.danic.keyword_driven_test.excel_file.ExcelReader;
import com.danic.keyword_driven_test.operations.ReadObjects;
import com.danic.keyword_driven_test.operations.UIOperations;
import com.danic.keyword_driven_test.utilities.Screenshot;
import com.danic.keyword_driven_test.utilities.data_sharing.DataShare;
import com.danic.keyword_driven_test.utilities.data_sharing.DataShareImpl;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jopendocument.dom.spreadsheet.Cell;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class ParentTest {
    protected WebDriver driver;
    private Properties props;
    private UIOperations operations;
    private DataShare dataShareService;
    private Map<String,String> expectedResults;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ReadObjects readObjects = new ReadObjects();
        props = readObjects.getObjects();
        dataShareService = new DataShareImpl();
        operations = new UIOperations(driver, dataShareService);
        expectedResults = new HashMap<>();
    }


    /**
     * This method executes each step of a test case in a spreadsheet
     * @param sheetName refers to a spreadsheet with extension .ods
     * @throws IOException
     */
    public void executeSteps(String sheetName) throws IOException {
        Sheet sheet = ExcelReader.readSpreadsheet(sheetName);
        int rowCount = sheet.getRowCount();

        for(int i = 1; i < rowCount; i++){
            Cell cell = sheet.getCellAt(0, i);

            if(cell.getTextValue().length() == 0){
                //Here each step is printed
            	System.out.printf("Step No.%03d -> %10s|%25s|%8s|%s\n",i-1, sheet.getCellAt(1, i).getTextValue(),
                        sheet.getCellAt(2,i).getTextValue(), sheet.getCellAt(3,i).getTextValue(),
                        sheet.getCellAt(4,i).getTextValue());
                try {
                    //Each step is performed here
                    operations.perform(props, sheet.getCellAt(1, i).getTextValue(),
                            sheet.getCellAt(2,i).getTextValue(), sheet.getCellAt(3,i).getTextValue(),
                            sheet.getCellAt(4,i).getTextValue());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(sheet.getCellAt(1,i).getTextValue().equals("ASSERT")){
                    addToExpectedResults(sheet.getCellAt(2,i).getTextValue(),
                            sheetName, sheet.getCellAt(4,i).getTextValue());
                }
            } else {
                System.out.println("New Testcase-> [" + cell.getTextValue() +"] Started");
            }
        }
        System.out.println("End of test case.\n");
    }

    /**
     * This method is called by a @Test method in order to execue
     * the assertions given in the test case steps in the spreadsheet
     * @param testCaseName
     */
    public void makeAssertions(String testCaseName){
        //This operation gets the keys of the expected results
        //corresponding to the test method calling this method
        Set<String> keys = expectedResults.keySet().stream()
                .filter(s -> s.endsWith(testCaseName))
                .collect(Collectors.toSet());

        //This instruction makes the assertions established in the test case
        for(String key : keys){
            String actualKey = key.split("_")[0];
            assertEquals(dataShareService.getResult(actualKey), expectedResults.get(key));
        }
    }

    /**
     * This method saves into a map the expected results of an assertion
     * @param objectName is the web element's name
     * @param sheetName
     * @param expectedResult that was established on the spreadsheet
     */
    private void addToExpectedResults(String objectName, String sheetName, String expectedResult){

        expectedResults.put(objectName+"_"+sheetName, expectedResult);
    }



    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
