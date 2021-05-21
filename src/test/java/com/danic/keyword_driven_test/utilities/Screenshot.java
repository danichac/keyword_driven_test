package com.danic.keyword_driven_test.utilities;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public class Screenshot {
    private static final String fileSeparator = System.getProperty("file.separator");
    private static final String reportFilepath = System.getProperty("user.dir") + fileSeparator + "TestReport"
        + fileSeparator + "screenshots";

    public static void takeScreenshot(WebDriver driver, String screenshotName){
        TakesScreenshot camera = (TakesScreenshot)driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        String timeStamp = Instant.now().toString();
        String newScreenshotName = reportFilepath + fileSeparator + screenshotName + "-" + timeStamp + ".png";
        System.out.println(newScreenshotName);
        try {
            Files.move(screenshot, new File(newScreenshotName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
