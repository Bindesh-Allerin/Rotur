//package com.rotur.base;
//
//import java.time.Duration;
//
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestResult;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import com.utils.ConfigReader;
//import com.utils.ScreenshotUtil;
//
//public class Base {
//
//    public WebDriver driver;
//
//    @BeforeMethod
//    public void setup() {
//
//    	String browser = ConfigReader.getProperty("browser");
//
//    	driver = BrowserFactory.getDriver(browser);
//        //driver = new ChromeDriver();
//
//        driver.manage().window().maximize();
//
//        int time = Integer.parseInt(ConfigReader.getProperty("timeout"));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
//
//        driver.get(ConfigReader.getProperty("url"));
//        System.out.println("Browser Launched");
//    }
//
//    @AfterMethod
//    public void tearDown(ITestResult result) {
//
//        if (ITestResult.FAILURE == result.getStatus()) {
//            ScreenshotUtil.capture(driver, result.getName());
//        }
//
//        driver.quit();
//    }
//
//}

//
//
package com.rotur.base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.utils.ConfigReader;
import com.utils.ExtentManager;
import com.utils.ExtentTestManager;
import com.utils.ScreenshotUtil;

public class Base {

    public WebDriver driver;
    protected static ExtentReports extent;

    // Report Setup (ONCE)
    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getInstance();
    }

    // Browser + Test Setup
    @BeforeMethod
    public void setup(Method method) {

        String browser = ConfigReader.getProperty("browser");
        driver = BrowserFactory.getDriver(browser);

        driver.manage().window().maximize();

        int time = Integer.parseInt(ConfigReader.getProperty("timeout"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));

        driver.get(ConfigReader.getProperty("url"));

        System.out.println("Browser Launched");

        // Extent Test Creation
        ExtentTest test = extent.createTest(method.getName());
        ExtentTestManager.setTest(test);
    }

    // Tear Down + Reporting
    @AfterMethod
    public void tearDown(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();

        if (result.getStatus() == ITestResult.FAILURE) {

            test.fail(result.getThrowable());

            // Screenshot
            ScreenshotUtil.capture(driver, result.getName());
            String path = "screenshots/" + result.getName() + ".png";

            try {
                test.addScreenCaptureFromPath(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }

        else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Skipped");
        }

        driver.quit();
    }

    // Flush Report (END)
    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}
