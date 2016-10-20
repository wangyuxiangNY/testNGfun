package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.WebDriver;

import com.buxie.selenium.testNGfun.Page;

public class VerifyCommon extends Page implements Verify{
	
	public VerifyCommon()
	{
		
	}

	public VerifyCommon(WebDriver driver)
	{
		super(driver);
	}
	public void verifySearch_expectingResult()
	{   
		softAssert.assertTrue(this.searchResultShown(), "Search is not working.");
		softAssert.assertAll();
	}

	public void verifySearch_notExpectingResult()
	{   
		softAssert.assertFalse(this.searchResultShown(), "Search result is shown by mistake.");
		softAssert.assertAll();
	}

	
}
