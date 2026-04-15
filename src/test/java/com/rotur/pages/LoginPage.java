package com.rotur.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.rotur.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    // LOCATORS
    By username = By.xpath("(//input)[1]");
    By password = By.xpath("//input[@type='password']");
    By loginbtn = By.xpath("//button[contains(text(),'Login')]");
    By validLogin = By.xpath("(//h1)[2]");
    By emailError = By.xpath("(//input)[1]/following-sibling::div");
    By passError = By.xpath("//input[@type='password']/following-sibling::div");
    By toast = By.xpath("//li[contains(@data-y-position, 'top')]");

    // ACTIONS
    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginbtn);
    }

    // VALIDATIONS
    public boolean isSuccessLogin() {
        return isPresent(validLogin);
    }

    public String getEmailErrMsg() {
        return isPresent(emailError) ? getText(emailError) : "";
    }

    public String getPasswordErrMsg() {
        return isPresent(passError) ? getText(passError) : "";
    }

    // TOAST
    public String getToastText() {
        return getText(toast);
    }

    public boolean getToastPosition() {
        try {
            WebElement element = find(toast);
            return element.getAttribute("data-x-position").equals("right")
                    && element.getAttribute("data-y-position").equals("top");
        } catch (Exception e) {
            return false;
        }
    }

    public String getToastTxtColor() {
        return getTextColorSafe(toast, "color");
    }

    public String getToastBGColor() {
        return getTextColorSafe(toast, "background-color");
    }

    private String getTextColorSafe(By locator, String css) {
        try {
            return find(locator).getCssValue(css);
        } catch (Exception e) {
            return "";
        }
    }
}

//package com.rotur.pages;
//
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//
//import com.utils.WaitUtils;
//
//
//public class LoginPage {
//
//
//    WebDriver driver;
//    WaitUtils wait;
//
//
//    // Constructor
//    public LoginPage(WebDriver driver){
//        this.driver = driver;
//        wait = new WaitUtils(driver);
//    }
//
//
//    // ================= LOCATORS =================
//    By username = By.xpath("(//input)[1]");
//    By password = By.xpath("//input[@type='password']");
//    By loginbtn = By.xpath("//button[contains(text(),'Login')]");
//
//
//    By validLogin = By.xpath("(//h1)[2]");
//    By emailError = By.xpath("(//input)[1]//following-sibling::div");
//    By passError = By.xpath("//input[@type='password']/following-sibling::div");
//
//
//    By toast = By.xpath("//li[contains(@data-y-position, 'top')]");
//
//
//    // ================= ACTIONS =================
//
//
//    public void enterUsername(String login) {
//        wait.WaitForVisibility(username).clear();
//        wait.WaitForVisibility(username).sendKeys(login);
//    }
//
//
//    public void enterPassword(String pass) {
//        wait.WaitForVisibility(password).clear();
//        wait.WaitForVisibility(password).sendKeys(pass);
//    }
//
//
//    public void clickLogin() {
//        wait.WaitForClickable(loginbtn).click();
//    }
//
//
//    public void login(String user, String pass) {
//        enterUsername(user);
//        enterPassword(pass);
//        clickLogin();
//    }
//
//
//    // ================= COMMON UTIL =================
//
//
//    public boolean isElementPresent(By locator) {
//        return driver.findElements(locator).size() > 0;
//    }
//
//
//    // ================= VALIDATIONS =================
//
//
//    public boolean isSuccessLogin() {
//        return isElementPresent(validLogin);
//    }
//
//
//    public String getEmailErrMsg() {
//        if (isElementPresent(emailError)) {
//            return wait.WaitForVisibility(emailError).getText();
//        }
//        return "";
//    }
//
//
//    public String getPasswordErrMsg() {
//        if (isElementPresent(passError)) {  // ✅ FIXED
//            return wait.WaitForVisibility(passError).getText();
//        }
//        return "";
//    }
//
//
//    // ================= TOAST =================
//
//
//    public String getToastText() {
//        try {
//            return wait.WaitForVisibility(toast).getText();
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//
//    public boolean getToastPosition() {
//        try {
//            WebElement element = wait.WaitForVisibility(toast);
//            String x = element.getAttribute("data-x-position");
//            String y = element.getAttribute("data-y-position");
//            return x.equals("right") && y.equals("top");
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//
//    public String getToastTxtColor() {
//        try {
//            return wait.WaitForVisibility(toast).getCssValue("color");
//        } catch (Exception e) {
//            return "";
//        }
//    }
//
//
//    public String getToastBGColor() {
//        try {
//            return wait.WaitForVisibility(toast).getCssValue("background-color");
//        } catch (Exception e) {
//            return "";
//        }
//    }
//}









//package com.rotur.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//
//import com.utils.WaitUtils;
//
//public class LoginPage {
//
//	WebDriver driver;
//	WaitUtils wait;
//
//	// Constructor
//	public LoginPage(WebDriver driver){
//		this.driver = driver;
//		wait = new WaitUtils(driver);
//	}
//
//	// Locators
//	By username = By.xpath("(//input)[1]");
//	By password = By.xpath("//input[@type='password']");
//	By loginbtn = By.xpath("//button[contains(text(),'Login')]") ;
//
//	//Logic for Login
//	public void enterUsername(String login) {
//		wait.WaitForVisibility(username).sendKeys(login);
//	}
//
//	public void enterPassword(String pass) {
//		driver.findElement(password).sendKeys(pass);
//	}
//
//	public void clickLogin() {
//		wait.WaitForClickable(loginbtn).click();
//	}
//
//	public void login(String user, String pass) {
//		enterUsername(user);
//		enterPassword(pass);
//		clickLogin();
//	}
//
//	//Logic for validation check
//		By ValidLogin = By.xpath("(//h1)[2]");
//		By emailError = By.xpath("(//div[@class='text-xs text-red-600'])[1]");
//		By passError = By.xpath("(//div[@class='text-xs text-red-600'])[2]");
//
//		public boolean isSuccessLogin() {
//			return wait.WaitForVisibility(ValidLogin).isDisplayed();
//		}
//
//		public boolean isElementPresent(By locator) {
//			return driver.findElements(locator).size()>0;
//		}
//		
//		public String getEmailErrMsg() {
//			if(isElementPresent(emailError)) {
//				return wait.WaitForVisibility(emailError).getText();
//			}
//			return "";	
//		}
//
//		public String getPasswordErrMsg() {
//			if(isElementPresent(emailError)) {
//				return wait.WaitForVisibility(passError).getText();
//			}
//			return "";
//		}
//
//
//	// Validate Toast validation
//	By toast = By.xpath("//li[contains(@data-y-position, 'top')]");
//	public String getToastText() {
//		return wait.WaitForVisibility(toast).getText();
//	}
//	public boolean getToastPosition() {
//		WebElement element = wait.WaitForVisibility(toast);
//		String x = element.getAttribute("data-x-position");
//		String y = element.getAttribute("data-y-position");
//		return x.equals("right") && y.equals("top");
//	}
//	public String getToastTxtColor() {
//		return wait.WaitForVisibility(toast).getCssValue("color");
//	}
//	public String getToastBGColor() {
//		return wait.WaitForVisibility(toast).getCssValue("background-color");
//	}
//
//}
