package com.rotur.test;

import com.rotur.base.Base;
import com.rotur.pages.LoginPage;
import com.utils.JsonUtils;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.testData.LoginData;
import org.testng.annotations.DataProvider;


public class RoturLogin extends Base {

    // DataProvider inside test class
    @DataProvider(name = "loginData")
    public Object[] getData() {
        return JsonUtils
                .getLoginData("src/test/resources/testdata/login.json")
                .toArray();
    }

    // Single Test Method for All Scenarios
    @Test(dataProvider = "loginData")
    public void testLogin(LoginData data) {

        LoginPage lp = new LoginPage(driver);

        // Step 1: Perform Login
        lp.login(data.getEmail(), data.getPassword());

        // Step 2: Validation based on type
        switch (data.getType()) {

            case "success":
                Assert.assertTrue(lp.isSuccessLogin(), "Login Failed!");
                break;

            case "validation":
            	
            	System.out.println("Email: " + data.getEmail());
            	System.out.println("Password: " + data.getPassword());

                // Email validation
                if (data.getEmail().trim().isEmpty()) {
                    Assert.assertEquals(lp.getEmailErrMsg(), data.getExpected(),
                            "Email validation mismatch");
                }

                // Password validation
                if (data.getPassword().trim().isEmpty()) {
                    Assert.assertEquals(lp.getPasswordErrMsg(), data.getExpected(),
                            "Password validation mismatch");
                }

                break;    
                
//            case "validation":
//                Assert.assertEquals(lp.getEmailErrMsg(), data.getExpected(), "Email validation mismatch");
//                Assert.assertEquals(lp.getPasswordErrMsg(), data.getExpected(), "Password validation mismatch");
//                break;

            case "error":
                Assert.assertEquals(lp.getToastText(), data.getExpected(), "Toast text mismatch");
                Assert.assertTrue(lp.getToastPosition(), "Toast position incorrect");
                Assert.assertEquals(lp.getToastTxtColor(), "rgba(153, 27, 27, 1)", "Text color mismatch");
                Assert.assertEquals(lp.getToastBGColor().trim(), "rgba(254, 242, 242, 1)", "BG color mismatch");
                break;

            default:
                throw new RuntimeException("Invalid test type: " + data.getType());
        }
    }
}
