package com.danic.keyword_driven_test.excel_file;

import com.danic.keyword_driven_test.operations.ReadObjects;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class ExcelReader {

    public static Sheet readSpreadsheet(String sheetName) throws IOException {
        Properties props = new ReadObjects().getObjects();
        File file = new File(props.getProperty("spreadsheet"));
        SpreadSheet spreadsheet = SpreadSheet.createFromFile(file);

        return spreadsheet.getSheet(sheetName);
    }
}
