package com.rotur.pages;

import com.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	WebDriver driver;
	WaitUtils wait;
	
	// Constructor
	public LoginPage(WebDriver driver){
		this.driver = driver;
		wait = new WaitUtils(driver);
	}																						
	
	// Locators
	By username = By.xpath("(//input)[1]");
	By password = By.xpath("//input[@type='password']");
	By loginbtn = By.xpath("//button[contains(text(),'Login')]") ;
	
	//Logic for Login
	public void enterUsername(String login) {
		wait.WaitForVisibility(username).sendKeys(login);
	}
	
	public void enterPassword(String pass) {
		driver.findElement(password).sendKeys(pass);
	}
	
	public void clickLogin() {
		wait.WaitForClickable(loginbtn).click();
	}
	
	public void login(String user, String pass) {
		enterUsername(user);
		enterPassword(pass);
		clickLogin();
	}
	
	//Logic for validation check
		By ValidLogin = By.xpath("(//h1)[2]");
		By emailError = By.xpath("(//div[@class='text-xs text-red-600'])[1]");
		By passError = By.xpath("(//div[@class='text-xs text-red-600'])[2]");
		
		public boolean isSuccessLogin() {
			return wait.WaitForVisibility(ValidLogin).isDisplayed();
		}
		
		public String getEmailErrMsg() {
			return wait.WaitForVisibility(emailError).getText();
		}
		
		public String getPasswordErrMsg() {
			return wait.WaitForVisibility(passError).getText();
		}
	
		
	// Validate Toast validation
	By toast = By.xpath("//li[contains(@data-y-position, 'top')]");
	public String getToastText() {
		return wait.WaitForVisibility(toast).getText();
	}
	public boolean getToastPosition() {
		WebElement element = wait.WaitForVisibility(toast);
		String x = element.getAttribute("data-x-position");
		String y = element.getAttribute("data-y-position");
		return x.equals("right") && y.equals("top");
	}
	public String getToastTxtColor() {
		return wait.WaitForVisibility(toast).getCssValue("color");
	}
	public String getToastBGColor() {
		return wait.WaitForVisibility(toast).getCssValue("background-color");
	}
	
}
