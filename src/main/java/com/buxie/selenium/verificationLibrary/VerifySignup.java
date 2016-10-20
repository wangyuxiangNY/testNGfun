package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.Player;
import com.buxie.selenium.testNGfun.SignUpPage;

public class VerifySignup extends SignUpPage implements Verify
{
    private SignUpPage signUpPage;
	
	public VerifySignup(WebDriver driver)
	{
		super(driver);
		signUpPage = PageFactory.initElements(driver, SignUpPage.class);
	}
	
	
	public void verifySoftGateIsShown()
	{
		softAssert.assertTrue(signUpPage.isSoftGateShown(), "Softgate is not shown.");
	}

}
