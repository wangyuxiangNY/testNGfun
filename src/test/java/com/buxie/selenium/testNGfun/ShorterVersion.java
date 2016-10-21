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



	public class ShorterVersion {

		
		WebDriver driver;
		private ThreadLocal<WebDriver> driverInstance;
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

		
		
		
		@BeforeMethod
		public void init(Method method) {
			/*
			 driverInstance = Utils.createThreadSafeWebDriver(browser);
			 driver = driverInstance.get();
	         driver.get(URL);
	         driver.manage().window().maximize();
	         */
			driver = DriverFactory.getInstance().getDriver();
			 driver.get(URL);
	         driver.manage().window().maximize();
	         driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		   
	        
	        
	        forYouCases = new ForYouCases(driver);
	        perfectForCases = new PerfectForCases(driver);
	        profileCases = new ProfileCases(driver);
	        liveRadioCases = new LiveRadioCases(driver);
	        podcastCases = new PodcastCases(driver);
	        artistRadioCases = new ArtistRadioCases(driver);
	        
	        forYouCases.setBrowser(browser);
	        
	     
	        methodName = method.getName();
	        
	        System.out.println("test method:" +  method.getName() + " started." );
	    }

		
		

		@Test(groups ="ArtistRadioTest")
		 public void testFilterAndPlayCustomAfterLogin() throws Exception
		 {  
			artistRadioCases.filterAndPlayCustomAfterLogin();
			//Verify.softAssert.assertAll();
		 }
		
		//@Test(groups ="ArtistRadioTest")
		@Test
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
		 
		 
		
		 @Test
	     public void testPopularUserFlow() 
	     {
	         forYouCases.flowAlong();
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
				try{
		            	Page.takeScreenshot(driver, result.getMethod().getMethodName());
		            }catch(Exception eX)
		            {
		            	
		            }
	        }
			
		  // driver.quit();
    	  DriverFactory.getInstance().removeDriver();
    	   System.out.println("Test case:" +  result.getMethod().getMethodName() +" is done!");
    	  
	    }
	
	    @AfterTest
	    public void bye() throws Exception{
    	    System.out.println("Done done done.!");
	    	
	    }
	
	   
	

	}