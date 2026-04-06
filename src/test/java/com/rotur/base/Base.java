package com.rotur.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.utils.ConfigReader;

public class Base {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
    	
    	String browser = ConfigReader.getProperty("browser");
    	
    	driver = BrowserFactory.getDriver(browser);
        //driver = new ChromeDriver();
    	
        driver.manage().window().maximize();
        
        int time = Integer.parseInt(ConfigReader.getProperty("timeout"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
        
        driver.get(ConfigReader.getProperty("url"));
        System.out.println("Browser Launched");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
    	//Thread.sleep(5000);
        driver.quit();
        System.out.println("Browser Closed");
    }
}
