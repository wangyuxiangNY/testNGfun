package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.PerfectForPage;

public class VerifyPerfectFor  extends PerfectForPage implements Verify  {
	
    private PerfectForPage perfectForPage;
    
	
    public VerifyPerfectFor( )
	{
		perfectForPage = PageFactory.initElements(driver, PerfectForPage.class);
	}
	
	
	public void verifySoftGateIsShown()
	{
		softAssert.assertTrue(getSignUpPage().isSoftGateShown(), "Softgate is not shown.");
	}
	
   
}
