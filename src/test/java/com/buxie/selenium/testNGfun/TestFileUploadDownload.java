package com.buxie.selenium.testNGfun;

import com.buxie.selenium.testCases.UploadDownloadCases;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.buxie.selenium.verificationLibrary.Verify;

public class TestFileUploadDownload {


	static WebDriver driver;
	UploadDownloadCases cases;
	
	
	String browser = "chrome";
	//String browser = "firefox";
	
	static String userCity = "";
	
	 
	final String URL = "https://www.dice.com";
	
	@BeforeMethod
	public void init(Method method) {
        driver = Utils.launchBrowser(URL, browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WaitUtility.waitForPageToLoad(driver);
        WaitUtility.sleep(5000);
        
        cases = new UploadDownloadCases(driver);
        
        
        System.out.println("test method:" +  method.getName() + " started." );
    }
	
	
	
	 @Test
    public void testUpload() throws Exception
    {  
		 cases.uploadResume();
		
    }
		
	 
	 @AfterMethod
	    public void tearDown(ITestResult result) throws Exception{
			Verify.softAssert.assertAll();
			if(result.getStatus() == ITestResult.FAILURE)
	        {
				try{
			    	  // Page.takeScreenshot(driver, name.getMethodName());
		            	Page.takeScreenshot(driver, "test");
		            }catch(Exception eX)
		            {
		            	
		            }
	        }
			
			 System.out.println("Test case:" +  result.getMethod().getMethodName() +" is done!");
 	   // driver.quit(); 
	    	
	    }
	
	    @AfterTest
	    public void bye() throws Exception{
 	    System.out.println("Done done done.!");
	    	
	    }
	
	
	
	
}
