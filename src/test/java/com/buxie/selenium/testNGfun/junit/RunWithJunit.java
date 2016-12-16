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
import com.buxie.selenium.testNGfun.XpathAndCSS;

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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.chrome.ChromeDriver;


public class RunWithJunit {
	 WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	XpathAndCSS xpath;
	
	//String browser = "chrome";
	String browser = "firefox";
	//String browser = "edge";
	// String browser = "ie";
	 
	final String URL = "http://www.iheart.com/";
	

	
	@Rule public TestName name = new TestName();
	
	
	@Before
    public void init()throws Exception
	{
		driver = Utils.launchBrowser(URL, browser);
		
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
        
        xpath = new XpathAndCSS(driver);
	   
        artistRadioCases.getErrors().delete(0, artistRadioCases.getErrors().length());
	        
    }
	
	@Ignore("skip")
	public void huntAjax() throws Exception
	{  
		//WaitUtility.injectJQuery(driver);
		//WaitUtility.fetchAjax(driver);
		//WaitUtility.fetchtAjaxSendData(driver);
        List<Object> result = WaitUtility.tryScript(driver);
        System.out.println(result.toString());
		
	}
	
	@Test
	 public void testFlash() throws Exception
	 {  
		//WaitUtility.sleep(8000);
    	System.out.println("SEE ad section:" + driver.findElement(By.id("media__video_large-media_0--wrapper")).getAttribute("innerHTML"));
       
  
	 }
	
	
	@Test
	 public void testXpath() throws Exception
	 {  
		xpath.xpath();
	 }
	

	@Test
	 public void testCSS() throws Exception
	 {  
		xpath.css();
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
	   public void testFilteredLive() throws Exception
	   {  
			 liveRadioCases.playFilteredLive();
			
	   }
			

	 @Test
   public void testPlayLive() throws Exception
   {  
		 liveRadioCases.playLive();
		
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
	
	
	 @Test
	 public void testPlayArtiestRadio() throws Exception
	 {
	    
		 artistRadioCases.playArtistRadio();
		
	 }
	  
	     

     @After
    public void tearDown() throws Exception{
	  //  driver.quit();
    	 
    	 
    	if (artistRadioCases.getErrors().length() > 0)
    	{	
    		fail(artistRadioCases.getErrors().toString());
    		
    	}	
    	
    	
    }

    private void handleException(Exception e)
    {   artistRadioCases.getErrors().append("Exception is thrown.");
        e.printStackTrace();
        try{
    	   Page.takeScreenshot(driver, name.getMethodName());
        }catch(Exception eX)
        {
        	
        }
    }
    

}
