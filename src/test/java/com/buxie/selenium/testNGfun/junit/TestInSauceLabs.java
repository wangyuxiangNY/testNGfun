package com.buxie.selenium.testNGfun.junit;


import com.buxie.selenium.testNGfun.Page;
import com.buxie.selenium.testNGfun.Utils;
import com.buxie.selenium.testNGfun.WaitUtility;
import com.buxie.selenium.testCases.ArtistRadioCases;
import com.buxie.selenium.testCases.ForYouCases;
import com.buxie.selenium.testCases.LiveRadioCases;
import com.buxie.selenium.testCases.PerfectForCases;
import com.buxie.selenium.testCases.PodcastCases;
import com.buxie.selenium.testCases.ProfileCases;
import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Test; 
import org.junit.Ignore; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestInSauceLabs {

	 WebDriver driver;
		ArtistRadioCases artistRadioCases;
		ForYouCases forYouCases;
		PerfectForCases perfectForCases;
		ProfileCases profileCases;
		LiveRadioCases liveRadioCases;
		PodcastCases podcastCases;
		
		
		//String browser = "chrome";
		String browser = "firefox";
		//String browser = "edge";
		// String browser = "ie";
		 
		final String URL = "http://www.iheart.com/";
		
        private final String hubURL = "ondemand.saucelabs.com:80";
		
		
		@Rule public TestName name = new TestName();
		
		
		@Before
	    public void init()throws Exception
		{
			//driver = Utils.launchBrowser(URL, browser);
			driver = Utils.createRemoteDriver(hubURL, browser, "windows");
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        
	         
	        forYouCases = new ForYouCases(driver);
	        perfectForCases = new PerfectForCases(driver);
	        profileCases = new ProfileCases(driver);
	        liveRadioCases = new LiveRadioCases(driver);
	        podcastCases = new PodcastCases(driver);
	        artistRadioCases = new ArtistRadioCases(driver);
		    Page.setDriver (driver);
		    
		    Page.getErrors().delete(0, Page.getErrors().length());
		        
	    }
	
		
		
		@Test
		 public void testForYou_NotForMe() throws Exception
		 {  
			forYouCases.notForMe(0);
		 }
		
		
		@Test
		 public void testForYou_Favorite() throws Exception
		 {  
			forYouCases.addToFavorite(0);
		 }

		@Test
		 public void testFavorite() throws Exception
		 {  
			artistRadioCases.favorite();
			/*
			WaitUtility.injectJQuery(driver);
			WaitUtility.fetchAjax(driver);
			WaitUtility.fetchtAjaxSendData(driver);
			
			WaitUtility.sleep(600*000);
			*/
	      
		 }
		
		 @Test
		 public void testSearchJoshInAll() throws Exception
		 {  
			 podcastCases.searchJoshInAll();
		 }
		
		 @Test
		 public void testSearchFromPodcast() throws Exception
		 {  
			 podcastCases.searchJoshInPodcast();
		 }	 

		 @Test
	   public void testPodcastThumbDown() throws Exception
	   {  
			 podcastCases.thumbDown();
			
	   }
				
		
		
		 @Test
	   public void testThumbUpLive() throws Exception
	   {  
			 liveRadioCases.thumbUp();
			
	   }
				
		
		 @Test
	   public void testPlayStastionFromProfile() throws Exception
	   {  
			 profileCases.playMyStation(1);
			
	   }
			
				

		 @Test
		 public void testBrowsePerfectFor() throws Exception
		 {
		    try{
			   perfectForCases.browsePerfectFor();
		    }catch(Exception e)
		    {
		    	handleException(e);
		    }
		 }
		

		 @Test
	    public void testPopularUserFlow() 
	    {
	        forYouCases.flowAlong();
	    }

		@Test
		 public void testFilterAndPlayCustomAfterLogin() throws Exception
		 {  
			artistRadioCases.filterAndPlayCustomAfterLogin();
		 }
		
		
		   
		     

	     @After
	    public void tearDown() throws Exception{
		   driver.quit();
	    	 
	    	 
	    	if (Page.getErrors().length() > 0)
	    	{	
	    		fail(Page.getErrors().toString());
	    		
	    	}	
	    	
	    	
	    }

	    private void handleException(Exception e)
	    {   Page.getErrors().append("Exception is thrown.");
	        e.printStackTrace();
	        try{
	    	   Page.takeScreenshot(driver, name.getMethodName());
	        }catch(Exception eX)
	        {
	        	
	        }
	    }
	    
	
}
