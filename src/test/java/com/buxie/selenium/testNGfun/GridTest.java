package com.buxie.selenium.testNGfun;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

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

public class GridTest {

	private RemoteWebDriver driver;

	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	// String browser = "chrome";
	 String browser = "firefox";
	final String URL = "http://www.iheart.com";
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) throws Exception {
		
		String hubURL = "http://192.168.1.5:4444/wd/hub";
		RemoteDriverFactory.getInstance().setBrowser(browser);
		RemoteDriverFactory.getInstance().setPlatform(platform);

		driver = RemoteDriverFactory.getInstance().getDriver();
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
		DriverFactory.getInstance().removeDriver();
	}

}
