package com.buxie.selenium.testNGfun.junit;


import com.buxie.selenium.testCases.ArtistRadioCases;
import com.buxie.selenium.testCases.ForYouCases;
import com.buxie.selenium.testCases.LiveRadioCases;
import com.buxie.selenium.testCases.PerfectForCases;
import com.buxie.selenium.testCases.PodcastCases;
import com.buxie.selenium.testCases.ProfileCases;
import com.buxie.selenium.testNGfun.Page;
import com.buxie.selenium.testNGfun.Utils;
import com.buxie.selenium.testNGfun.WaitUtility;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import java.net.URL;

import org.junit.Test; 
import org.junit.Ignore; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(Parallelized.class)
public class RunTestParallelInGrid_old {
	
	private WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	 private String platform;
	 private String browserName;
	 private String browserVersion;
	  
		//String browser = "chrome";
		// String browser = "firefox";
		//String browser = "edge";
		// String browser = "ie";
		 
	final String URL = "http://www.iheart.com/";
	
	@Rule public TestName name = new TestName();
	
	 @Parameterized.Parameters
	  public static LinkedList<String[]> getEnvironments() throws Exception {
	    LinkedList<String[]> env = new LinkedList<String[]>();
	    env.add(new String[]{Platform.WINDOWS.toString(), "chrome", "53"});
	    env.add(new String[]{Platform.MAC.toString(),"firefox","46"});
	   // env.add(new String[]{Platform.WINDOWS.toString(),"ie","11"});

	    //add more browsers here

	    return env;
	  }
	
	  
   public RunTestParallelInGrid_old(String platform, String browserName, String browserVersion) {
	    this.platform = platform;
	    this.browserName = browserName;
	    this.browserVersion = browserVersion;
   }  
	
	@Before
    public void init()throws Exception
	{
		// driver = Utils.launchBrowser(URL, browser);
		
		String hubURL = "http://192.168.1.5:4444/wd/hub";
		
		driver = Utils.createRemoteDriver(hubURL, browserName, "windows");
		driver.get(URL);
	
		
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WaitUtility.waitForPageToLoad(driver);
        
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
	    
    }

	@Test
	 public void testFavorite() throws Exception
	 {  
		artistRadioCases.favorite();
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
		    
			 perfectForCases.browsePerfectFor();
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
		   
	    	if (forYouCases.getErrors().length() > 0)
				 fail(forYouCases.getErrors().toString());
	    }

	    private void handleException(Exception e)
	    {   forYouCases.getErrors().append("Exception is thrown.");
	        e.printStackTrace();
	        try{
	    	   Page.takeScreenshot(driver, name.getMethodName());
	        }catch(Exception eX)
	        {
	        	
	        }
	    }
	    

}
