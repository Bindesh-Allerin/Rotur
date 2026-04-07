package com.rotur.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rotur.base.Base;
import com.rotur.pages.LoginPage;
import com.testData.LoginData;
import com.utils.JsonUtils;
import com.utils.ValidationUtils;
import com.utils.Retry;
import com.utils.ExtentTestManager;


public class RoturLogin extends Base {

    @DataProvider(name = "loginData")
    public Object[] getData() {
        return JsonUtils
                .getLoginData("src/test/resources/testdata/login.json")
                .toArray();
    }

    @Test(dataProvider = "loginData", retryAnalyzer = Retry.class)
    public void testLogin(LoginData data) {

        System.out.println("Executing Test → " + data.toString());

        ExtentTestManager.getTest().info("Executing: " + data.toString());
        LoginPage lp = new LoginPage(driver);
        
        ExtentTestManager.getTest().info("Entering credentials");
        lp.login(data.getEmail(), data.getPassword());

        switch (data.getType()) {

            case "success":
                ValidationUtils.validateText(
                        String.valueOf(lp.isSuccessLogin()),
                        "true",
                        "Login Success");
                break;

            case "validation":
                ExtentTestManager.getTest().info("Validating field errors");
                if (data.getErrors() != null) {

                    if (data.getErrors().containsKey("email")) {
                        ValidationUtils.validateText(
                                lp.getEmailErrMsg(),
                                data.getErrors().get("email"),
                                "Email");
                    }

                    if (data.getErrors().containsKey("password")) {
                        ValidationUtils.validateText(
                                lp.getPasswordErrMsg(),
                                data.getErrors().get("password"),
                                "Password");
                    }
                }
                break;

            case "error":
                ExtentTestManager.getTest().info("Validating error toast");
            	ValidationUtils.validateToast(
            		    lp.getToastText(),
            		    data.getToast(),
            		    lp.getToastTxtColor(),
            		    lp.getToastBGColor(),
            		    lp.getToastPosition(),
            		    "error"
            		);

                break;

            default:
                throw new RuntimeException("Invalid test type: " + data.getType());
        }
    }
}



//package com.rotur.test;
//
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.rotur.base.Base;
//import com.rotur.pages.LoginPage;
//import com.testData.LoginData;
//import com.utils.JsonUtils;
//
//
//public class RoturLogin extends Base {
//	
//	    // DataProvider
//	    @DataProvider(name = "loginData")
//	    public Object[] getData() {
//	        return JsonUtils
//	                .getLoginData("src/test/resources/testdata/login.json")
//	                .toArray();
//	    }
//
//	    // Reusable validation helper
//	    private void validateField(String actual, String expected, String fieldName) {
//	        Assert.assertEquals(actual, expected, fieldName + " validation mismatch");
//	    }
//
//	    @Test(dataProvider = "loginData")
//	    public void testLogin(LoginData data) {
//
//	        LoginPage lp = new LoginPage(driver);
//	        
//	        // Step 1: Perform Login
//	        lp.login(data.getEmail(), data.getPassword());
//
//	        // Step 2: Validation
//	        switch (data.getType()) {
//
//	            case "success":
//
//	                Assert.assertTrue(lp.isSuccessLogin(), "Login Failed!");
//	                break;
//
//	            case "validation":
//	            	
//	                String expectedEmail = data.getErrors() != null ? data.getErrors().get("email") : "";
//
//	                String expectedPassword = data.getErrors() != null ? data.getErrors().get("password") : "";
//
//	                String actualEmail = lp.getEmailErrMsg();
//	                String actualPassword = lp.getPasswordErrMsg();
//
//	                // Validate Email Error
//	                if (expectedEmail != null && !expectedEmail.isEmpty()) {
//	                    validateField(actualEmail, expectedEmail, "Email");
//	                }
//	                // Validate Password Error
//	                if (expectedPassword != null && !expectedPassword.isEmpty()) {
//	                    validateField(actualPassword, expectedPassword, "Password");
//	                }
//	                break;
//
//
//	            case "error":
//
//	                // Optional wait (for flaky toast)
//	                try { Thread.sleep(1000); } catch (Exception e) {}
//
//	                String actualToast = lp.getToastText();
//	                validateField(actualToast, data.getToast(), "Toast Message");
//	                Assert.assertTrue(lp.getToastPosition(), "Toast position incorrect");
//	                Assert.assertEquals(lp.getToastTxtColor(),"rgba(153, 27, 27, 1)", "Text color mismatch");
//	                Assert.assertEquals(lp.getToastBGColor().trim(),"rgba(254, 242, 242, 1)", "BG color mismatch");
//	                break;
//
//
//	            default:
//	                throw new RuntimeException("Invalid test type: " + data.getType());
//	        }
//	    }
//	

//}

//    // DataProvider inside test class
//    @DataProvider(name = "loginData")
//    public Object[] getData() {
//        return JsonUtils
//                .getLoginData("src/test/resources/testdata/login.json")
//                .toArray();
//    }
//
//    // Single Test Method for All Scenarios
//    @Test(dataProvider = "loginData")
//    public void testLogin(LoginData data) {
//
//        LoginPage lp = new LoginPage(driver);
//
//        // Step 1: Perform Login
//        lp.login(data.getEmail(), data.getPassword());
//
//        // Step 2: Validation based on type
//        switch (data.getType()) {
//
//            case "success":
//                Assert.assertTrue(lp.isSuccessLogin(), "Login Failed!");
//                break;
//
//            case "validation":
//
//            	System.out.println("Email: " + data.getEmail());
//            	System.out.println("Password: " + data.getPassword());
//
//                // Email validation
//            	
//            	String actualEmailErr = lp.getEmailErrMsg();
//            	String actualPassErr = lp.getPasswordErrMsg();
//            	String expectEmailErr = data.getErrors().get("email");
//            	String expectPassErr = data.getErrors().get("password");           	
//
////            	Assert.assertEquals(actualEmailErr, expectEmailErr, "Email validation mismatch");
////            	Assert.assertEquals(actualPassErr, expectPassErr, "Password validation mismatch");
//            	
//                if (data.getEmail().trim().isEmpty()) {
//                    Assert.assertEquals(actualEmailErr, expectEmailErr,
//                            "Email validation mismatch");
//                }
//
//                // Password validation
//                if (data.getPassword().trim().isEmpty()) {
//                    Assert.assertEquals(actualPassErr, expectPassErr,
//                            "Password validation mismatch");
//                }
//
//                break;
//
//            case "error":
//                Assert.assertEquals(lp.getToastText(), data.getToast(), "Toast text mismatch");
//                Assert.assertTrue(lp.getToastPosition(), "Toast position incorrect");
//                Assert.assertEquals(lp.getToastTxtColor(), "rgba(153, 27, 27, 1)", "Text color mismatch");
//                Assert.assertEquals(lp.getToastBGColor().trim(), "rgba(254, 242, 242, 1)", "BG color mismatch");
//                break;
//
//            default:
//                throw new RuntimeException("Invalid test type: " + data.getType());
//        }
//    }

