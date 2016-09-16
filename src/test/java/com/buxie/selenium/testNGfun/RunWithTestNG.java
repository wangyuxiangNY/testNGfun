package com.buxie.selenium.testNGfun;


import com.buxie.selenium.testCases.*;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;

import com.buxie.selenium.testNGfun.*;
import com.buxie.selenium.verificationLibrary.Verify;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.Assertion;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;



//show the use of @BeforeSuite and @BeforeTest
public class RunWithTestNG {
	

	static WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	
	//String browser = "chrome";
	 String browser = "firefox";
	
	static String userCity = "";
	
	 
	final String URL = "http://www.iheart.com/";

	@BeforeMethod
	public void init(Method method) {
        driver = Utils.launchBrowser(URL, browser);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WaitUtility.waitForPageToLoad(driver);
        
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
        
        Page.setDriver (driver);
        
        System.out.println("test method:" +  method.getName() + " started." );
    }
	
	
	 @Test
     public void testPopularUserFlow() 
     {
         forYouCases.flowAlong();
     }

	@Test(groups ="ArtistRadioTest")
	 public void testFilterAndPlayCustomAfterLogin() throws Exception
	 {  
		artistRadioCases.filterAndPlayCustomAfterLogin();
	 }
	
	@Test(groups ="ArtistRadioTest")
	 public void testFavorite() throws Exception
	 {  
		artistRadioCases.favorite();
	 }
	
	 @Test(groups ="searchTest")
	 public void testSearchJoshInAll() throws Exception
	 {  
		 podcastCases.searchJoshInAll();
	 }
	
	 @Test(groups ="searchTest")
	 public void testSearchFromPodcast() throws Exception
	 {  
		 podcastCases.searchJoshInPodcast();
	 }	 
 
	 @Test(groups ="podCastTest")
    public void testPodcastThumbDown() throws Exception
    {  
		 podcastCases.thumbDown();
		
    }
			
	
	
	 @Test(groups ="liveRadioTest")
    public void testThumbUpLive() throws Exception
    {  
		 liveRadioCases.thumbUp();
		
    }
			
	
	 @Test(groups ="profileTest")
    public void testPlayStastionFromProfile() throws Exception
    {  
		 profileCases.playMyStation(1);
		
    }
		
			

	 @Test(groups = "PerfectFor")
	 public void testBrowsePerfectFor() throws Exception
	 {
	    
		 perfectForCases.browsePerfectFor();
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
			
			if(result.getStatus() == ITestResult.SUCCESS)	 
    	        driver.quit(); 
    	   System.out.println("Test case:" +  result.getMethod().getMethodName() +" is done!");
	    	
	    }
	
	    @AfterTest
	    public void bye() throws Exception{
    	    System.out.println("Done done done.!");
	    	
	    }
	
	   
	

}