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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;




public class TestNGParallelRun {

	//private static ThreadLocal<WebDriver> driver;
	private   WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	// private static final Set<WebDriver> drivers = Collections.newSetFromMap(new ConcurrentHashMap<>());
	private static final ConcurrentHashMap<Long, WebDriver> drivers = new ConcurrentHashMap<Long, WebDriver>();
	  
	//String browser = "chrome";
	// String browser = "firefox";
	// String browser = "edge";
	//String browser = "ie";
	
	String methodName ="";
	 
	final String URL = "http://www.iheart.com/";

	protected  final static  Logger logger = LoggerFactory.getLogger(TestNGParallelRun.class);

	
	@Parameters({ "browser" })
	@BeforeMethod(alwaysRun=true)
	public  void  init(Method method, String browser) {
		
		logger.info("Test in Browser:" + browser + "/" + Thread.currentThread().getId());
		
		MyDriverFactory.getInstance().setBrowser(browser);

		logger.info("Double-check Browser/threadID:" + MyDriverFactory.getInstance().getBrowser()+
				Thread.currentThread().getId());
		
		 logger.info("test method:" +  method.getName()  + " run in Browser : " + browser +
	        		" run with Thread Id." + Thread.currentThread().getId());
		 
		driver = MyDriverFactory.getInstance().getDriver();
		
		//drivers.add(driver);
		drivers.put(Thread.currentThread().getId(), driver);
		driver.get(URL);
		driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
		
        WaitUtility.waitForPageToLoad(driver);
        
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
     
         logger.info("test method:" +  method.getName()  + " run in Browser : " + browser +
        		" run with Thread Id." + Thread.currentThread().getId());
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
	 
	 /*
	 
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
		
	*/		

	// @Test(groups = "PerfectFor")
	 @Test(enabled = false)
	 public void testBrowsePerfectFor() throws Exception
	 {
	    
		 perfectForCases.browsePerfectFor();
		// Verify.softAssert.assertAll();
	 }


		@AfterMethod(alwaysRun = true)
	    public void tearDown(ITestResult result) throws Exception{
			
			if(result.getStatus() == ITestResult.FAILURE)
	        {
				 // Page.takeScreenshot(driver, result.getMethod().getMethodName());
		          takeScreenshot(driver, result);
	        }
			

			logger.info("Test case is done:" +  result.getMethod().getMethodName() +" / threadID:" +
			                 Thread.currentThread().getId());
			
			
	    	
		 	
	    }
	
	    @AfterTest(alwaysRun=true)
	    public void bye() throws Exception
	    {
	    	logger.info("Done done done.! ThreadID:" + Thread.currentThread().getId());
	    	logger.info("About to close 1 of the drivers for browser:" +  MyDriverFactory.getInstance().getBrowser());
			logger.info("See drivers count:"+ drivers.size());
			drivers.get(Thread.currentThread().getId()).quit();
	    	/*
	    	logger.info("About to close all drivers:" + drivers.size());
	    	 for (WebDriver webDriver : drivers) {
	             try {
	            	 String browser = MyDriverFactory.getInstance().getBrowser();
	            	 if (browser.equalsIgnoreCase("chrome"))
	            	 {   
	            		 if (webDriver instanceof ChromeDriver)
	            			 webDriver.quit();
	            	 }else if (browser.equalsIgnoreCase("firefox"))
	            	 {
	            		 if (webDriver instanceof FirefoxDriver)
	            			 webDriver.quit();
	            	 }
	                     
	             } catch (Exception e) {
	                 e.printStackTrace();
	             }
	         }
	         */
	    	
	    	MyDriverFactory.getInstance().removeDriver();
    	   
	    	
	    }
	
	    
	    private void takeScreenshot(WebDriver driver,  ITestResult testResult)
	    {
	         try {
	        	 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	    		 Date date = new Date();
	    		 
	             File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	             String currentPath =  System.getProperty("user.dir");
		     	 String fullFilePath =  currentPath + "\\" + testResult.getMethod().getMethodName() +
		     			 dateFormat.format(date) + "_"+ MyDriverFactory.getInstance().getBrowser() + ".jpg";
	             logger.info("screenshot:" + fullFilePath);

	             FileUtils.copyFile(screenshot, new File(fullFilePath));
	         } catch(Exception ex) {
	             logger.info(ex.toString());
	             logger.info(ex.getMessage());
	         }

	    }

}




