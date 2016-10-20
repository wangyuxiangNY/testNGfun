package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.PerfectForPage;

public class VerifyPerfectFor  extends PerfectForPage implements Verify  {
	
  
    
	public VerifyPerfectFor(WebDriver driver)
	{
		super(driver);
		
	}
    
    
	public void verifySoftGateIsShown()
	{
		softAssert.assertTrue(getSignUpPage().isSoftGateShown(), "Softgate is not shown.");
	}
	
   
}
