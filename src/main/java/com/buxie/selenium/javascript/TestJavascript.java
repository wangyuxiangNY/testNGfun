package com.buxie.selenium.javascript;


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


public class TestJavascript {

	 WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	
	String browser = "chrome";
	 //String browser = "firefox";
	//String browser = "edge";
	// String browser = "ie";
	 
	final String URL = "http://www.iheart.com/";
	

	
	
	@Rule public TestName name = new TestName();
	
	
	@Before
    public void init()throws Exception
	{
		
		
		driver = Utils.launchBrowser(URL, browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         
        driver.manage().timeouts().setScriptTimeout(11*60, java.util.concurrent.TimeUnit.SECONDS) ;
        
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
	    
	   
    }
	
	@Test
	public void huntAjaxRequests() throws Exception
	{  
		artistRadioCases.playArtistRadio();
		
		List<String> result = AllAboutJavascript.fetchAjaxRequest(driver);
		System.out.println("See ajax calls:");
		for (String s : result)
			System.out.println(s);
		
		System.out.println("Done done done!");
	}
	
	@Test
	public void huntAjaxResponses() throws Exception
	{  
		artistRadioCases.playArtistRadio();
		
		List<String> result = AllAboutJavascript.fetchAjaxResponse(driver);
		System.out.println("See ajax responses:");
		for (String s : result)
			System.out.println(s);
		
		System.out.println("Done done done!");
	}
	
	@Test
	public void huntAjaxSentData() throws Exception
	{  
		artistRadioCases.playArtistRadio();
		
		List<Object> result = AllAboutJavascript.fetchtAjaxSendData(driver);
		System.out.println("See ajax sent data:");
		for (Object s : result)
			if (s != null) System.out.println(s.toString());
		
		System.out.println("Done done done!");
	}
	
	@Test
	public void waitForBrowser() throws Exception
	{  
		System.out.println("waitFOrBrowser begins...");
		AllAboutJavascript.wait(driver);
		System.out.println("WaitForBrowser is done.");
		
	}
	
	
	@Test
	public void anotherWait() throws Exception
	{  
		driver.navigate().refresh();
		 AllAboutJavascript.waitForAjax(driver);
	       /*
		System.out.println("anotherWait begins...");
		AllAboutJavascript.anotherWait(driver);
		System.out.println("anotherWait is done.");
		*/
	}
	

     @After
    public void tearDown() throws Exception{
	   // driver.quit();
    	 
    	 
    }

    private void handleException(Exception e)
    {   
        e.printStackTrace();
        try{
    	   Page.takeScreenshot(driver, name.getMethodName());
        }catch(Exception eX)
        {
        	
        }
    }
    

	
}
