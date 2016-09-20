package com.buxie.selenium.testNGfun;

import java.lang.reflect.Method;

//NOTE: replace USERNAME:ACCESS_KEY@YOUR_SUBDOMAIN and VIDEO_URL with your credentials found in the Gridlastic dashboard
//ALSO SEE https://github.com/Gridlastic/demo1 FOR JAVA TESTNG EXAMPLES WITH PARALLEL TEST EXECUTIONS
//NOTE: THE FIRST TEST ON A GRID NODE AFTER BEING LAUNCHED CAN BE SLOW, FOLLOWING TESTS ARE MUCH FASTER AFTER THE NODE IS "WARMED" UP.


import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.buxie.selenium.testCases.ArtistRadioCases;
import com.buxie.selenium.testCases.ForYouCases;
import com.buxie.selenium.testCases.LiveRadioCases;
import com.buxie.selenium.testCases.PerfectForCases;
import com.buxie.selenium.testCases.PodcastCases;
import com.buxie.selenium.testCases.ProfileCases;

public class GridTest2 {
	

	// VIDEO_URL set to like "https://s3-ap-southeast-2.amazonaws.com/b2729248-ak68-6948-a2y8-80e7479te16a/9ag7b09j-6a38-58w2-bb01-17qw724ce46t/play.html?".
	// Find this VIDEO_URL value in your Gridlastic dashboard.
	private static final String VIDEO_URL = null; 
	private RemoteWebDriver driver;

	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	 String browser = "chrome";
	// String browser = "firefox";
	//String browser = "internet explorer";
	final String URL = "http://www.iheart.com";
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) throws Exception {
		
		String hubURL = "http://192.168.1.5:4444/wd/hub";
		driver = Utils.createRemoteDriver(hubURL, browser, "windows");
		driver.get(URL);
        WaitUtility.waitForPageToLoad(driver);
        
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
        
        Page.setDriver (driver);
        Page.setBrowser(browser);
        
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
	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		driver.quit();
	}
	

}
