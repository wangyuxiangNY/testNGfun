package com.buxie.selenium.testNGfun;


import com.buxie.selenium.testCases.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;

import com.buxie.selenium.testNGfun.*;
import com.buxie.selenium.verificationLibrary.Verify;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.Assertion;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;




public class TestNGParallelRun {


	private WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	
	String browser = "chrome";
	// String browser = "firefox";
	// String browser = "edge";
	//String browser = "ie";
	
	String methodName ="";
	 
	final String URL = "http://www.iheart.com/";

	
	@Parameters({ "browser" })
	@BeforeMethod
	public void init(Method method, String browser) {
		System.out.println("Test in Browser:" + browser + "/" + Thread.currentThread().getId());
		
		DriverFactory.getInstance().setBrowser(browser);

		System.out.println("Double-check Browser/threadID:" + DriverFactory.getInstance().getBrowser()+
				Thread.currentThread().getId());
		driver = DriverFactory.getInstance().getDriver();
		
		//driver = Utils.createWebDriver(browser);
		driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
		
        WaitUtility.waitForPageToLoad(driver);
        
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
     
         System.out.println("test method:" +  method.getName()  + " run in Browser : " + browser +
        		" run with Thread Id." + Thread.currentThread().getId());
    }
	
/*
	 @Test
     public void testPopularUserFlow() 
     {
         forYouCases.flowAlong();
     }
     
*/
	@Test(groups ="ArtistRadioTest")
	 public void testFilterAndPlayCustomAfterLogin() throws Exception
	 {  
		artistRadioCases.filterAndPlayCustomAfterLogin();
		//Verify.softAssert.assertAll();
	 }
	
	

	@Test(groups ="ArtistRadioTest")
	 public void testFavorite() throws Exception
	 {  
		artistRadioCases.favorite();
		//Verify.softAssert.assertAll();
	 }
	
	
	
	 @Test(groups ="searchTest")
	 public void testSearchJoshInAll() throws Exception
	 {  
		 podcastCases.searchJoshInAll();
		 //Verify.softAssert.assertAll();
	 }
	
	 @Test(groups ="searchTest")
	 public void testSearchFromPodcast() throws Exception
	 {  
		 podcastCases.searchJoshInPodcast();
		// Verify.softAssert.assertAll();
	 }	 
 
	 @Test(groups ="podCastTest")
    public void testPodcastThumbDown() throws Exception
    {  
		 podcastCases.thumbDown();
		// Verify.softAssert.assertAll();
    }
			
	
	
	 @Test(groups ="liveRadioTest")
    public void testThumbUpLive() throws Exception
    {  
		 liveRadioCases.thumbUp();
		// Verify.softAssert.assertAll();
    }
			
	
	 @Test(groups ="profileTest")
    public void testPlayStastionFromProfile() throws Exception
    {  
		 profileCases.playMyStation(1);
		 
		// Verify.softAssert.assertAll();
		
    }
		
			

	// @Test(groups = "PerfectFor")
	 @Test(enabled = false)
	 public void testBrowsePerfectFor() throws Exception
	 {
	    
		 perfectForCases.browsePerfectFor();
		// Verify.softAssert.assertAll();
	 }
	

		@AfterMethod
	    public void tearDown(ITestResult result) throws Exception{
			
			if(result.getStatus() == ITestResult.FAILURE)
	        {
				 // Page.takeScreenshot(driver, result.getMethod().getMethodName());
		          takeScreenshot(driver, result);
	        }
			
			DriverFactory.getInstance().removeDriver();
			//driver.quit();
			System.out.println("Test case is done:" +  result.getMethod().getMethodName() +" / threadID:" +
			                 Thread.currentThread().getId());
    	  
	    	
	    }
	
	    @AfterTest
	    public void bye() throws Exception{
    	    System.out.println("Done done done.!");
	    	
	    }
	
	    
	    private void takeScreenshot(WebDriver driver,  ITestResult testResult)
	    {
	         try {
	             File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	             // String filePathRoot = "C:\\_Jenkins\\workspace\\" + jenkinsJobName + "\\target\\surefire-reports\\";
	     		String currentPath =  System.getProperty("user.dir");
	     		//String path = currentPath + "\\target\\surefire-reports\\";
	     		
	             //String fullFilePath = path + testResult.getTestClass() + "\\" + testResult.getTestName() + ".jpg";
	     		String fullFilePath =  currentPath + "\\" + testResult.getMethod().getMethodName() + ".jpg";
                System.out.println("screenshot:" + fullFilePath);

	             FileUtils.copyFile(screenshot, new File(fullFilePath));
	         } catch(Exception ex) {
	             System.out.println(ex.toString());
	             System.out.println(ex.getMessage());
	         }

	    }

}




