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

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.junit.Assert.*; 

import org.junit.Test; 
import org.junit.Ignore; 
import org.junit.Before; 
import org.junit.After; 
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.TestName;
import org.junit.runner.Description;

import org.openqa.selenium.WebDriver;


public class RunWithJunit {
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
	

	
	@Rule public TestName name = new TestName();
	
	
	@Before
    public void init()throws Exception
	{
		driver = Utils.launchBrowser(URL, browser);
		 WaitUtility.waitForPageToLoad(driver);
		 
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
	   
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
    

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        public void finished(Description description) {
            driver.quit();
        }

        @Override
        public void failed(Throwable e, Description description) {
            try {
                File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

                // String filePathRoot = "C:\\_Jenkins\\workspace\\" + jenkinsJobName + "\\target\\surefire-reports\\";
        		String currentPath =  System.getProperty("user.dir");
        		String path = currentPath + "\\target\\surefire-reports\\";
        		
                String fullFilePath = path + description.getClassName() + "\\" + description.getMethodName() + ".jpg";

                FileUtils.copyFile(screenshot, new File(fullFilePath));
            } catch(Exception ex) {
                System.out.println(ex.toString());
                System.out.println(ex.getMessage());
            }

            driver.quit();
        }
    };
    
}
