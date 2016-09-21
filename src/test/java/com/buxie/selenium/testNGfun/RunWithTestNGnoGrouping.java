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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.Assertion;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class RunWithTestNGnoGrouping {

	static WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	
	//String browser = "chrome";
	 String browser = "firefox";
	// String browser = "edge";
	
	static String userCity = "";
	
	 
	final String URL = "http://www.iheart.com/";

	/*
	@Parameters({ "browser" })
	@BeforeMethod
	public void init(Method method, String browser) {
		System.out.println("Test in Browser:" + browser);
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
        
        System.out.println("test method:" +  method.getName() + " run in Browser : " + browser);
        System.out.println("test method:" +  method.getName() +  "run with Thread Id." + Thread.currentThread().getId());
    }
	*/
	
	
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
		try{
           forYouCases.flowAlong();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
     }

	@Test
	 public void testFilterAndPlayCustomAfterLogin() throws Exception
	 {  
		try{
	           artistRadioCases.filterAndPlayCustomAfterLogin();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	
	@Test
	 public void testFavorite() throws Exception
	 {  
		try{
	           artistRadioCases.favorite();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	 }
	
	 @Test
	 public void testSearchJoshInAll() throws Exception
	 {  
		 try{
	            podcastCases.searchJoshInAll();
		 }catch(Exception e)
			{
				e.printStackTrace();
			}
	 }
	
	 @Test
	 public void testSearchFromPodcast() throws Exception
	 {  
		 try{
	            podcastCases.searchJoshInPodcast();
		 }catch(Exception e)
			{
				e.printStackTrace();
			}
	 }	 
 
	 @Test
    public void testPodcastThumbDown() throws Exception
    {  
		 try{
	            podcastCases.thumbDown();
		 }catch(Exception e)
			{
				e.printStackTrace();
			}
		
    }
			
	
	
	 @Test
    public void testThumbUpLive() throws Exception
    {  
		 try{
	           liveRadioCases.thumbUp();
		 }catch(Exception e)
			{
				e.printStackTrace();
			}
		
    }
			
	
	 @Test
    public void testPlayStastionFromProfile() throws Exception
    {  
		 try{
	           profileCases.playMyStation(1);
		 }catch(Exception e)
			{
				e.printStackTrace();
			}
		
    }
		
			

	 @Test
	 public void testBrowsePerfectFor() throws Exception
	 {
	    
		 try{
	           perfectForCases.browsePerfectFor();
		 }catch(Exception e)
			{
				e.printStackTrace();
			}
	 }
	

		@AfterMethod
	    public void tearDown(ITestResult result){
			try{
			   Verify.softAssert.assertAll();
			   
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			if(result.getStatus() == ITestResult.FAILURE)
	        {
				try{
			    	  // Page.takeScreenshot(driver, name.getMethodName());
		            	Page.takeScreenshot(driver, "test");
		            }catch(Exception eX)
		            {
		            	
		            }
	        }
			
			//if(result.getStatus() == ITestResult.SUCCESS)	 
    	   driver.quit(); 
    	   System.out.println("Test case:" +  result.getMethod().getMethodName() +" is done!");
	    	
	    }
	
	    @AfterTest
	    public void bye() throws Exception{
	    	if (driver != null)
	    		driver.quit();
    	    System.out.println("Done done done.!");
	    	
	    }
	
	   
	
	
}
