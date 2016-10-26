package com.buxie.selenium.testNGfun;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
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
	// String browser, platform;
	final String hubURL = "http://192.168.1.5:4444/wd/hub";
	final String URL = "http://www.iheart.com";
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Suite started: " +  (new Date()).toString());
		System.out.println("Suite started: " +  Calendar.getInstance().getTimeInMillis());
	}

	
	@Parameters({ "browser", "platform" })
	@BeforeMethod
	public void init(Method method, String browser, String platform) {
		System.out.println("Test in Browser/platform:" + browser + "/" + platform +" /ThreadID:" +  Thread.currentThread().getId());
		RemoteDriverFactory.getInstance().setHubURL(hubURL);
		RemoteDriverFactory.getInstance().setBrowser(browser);
		RemoteDriverFactory.getInstance().setPlatform(platform);

		System.out.println("Double-check :" + RemoteDriverFactory.getInstance().getBrowser() 
				+	"/" + RemoteDriverFactory.getInstance().getPlatform() +" /ThreadID:" +  Thread.currentThread().getId());
				
		
		driver = RemoteDriverFactory.getInstance().getDriver();
		driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
		
        WaitUtility.waitForPageToLoad(driver);
        
        forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
        
        System.out.println("test method:" +  method.getName() + " run in : " + 
        				RemoteDriverFactory.getInstance().getPlatform()   +
        				"/" +  RemoteDriverFactory.getInstance().getBrowser()  +
        				" / " + Thread.currentThread().getId());
			
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
	
	//@Test(groups ="ArtistRadioTest")
	@Test(enabled = false)
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
			// Page.takeRemoteScreenshot(driver, result.getMethod().getMethodName());
	         takeScreenshot(new Augmenter().augment(driver ), result);  
        }
		
		RemoteDriverFactory.getInstance().removeDriver();
		System.out.println("Test case:" +  result.getMethod().getMethodName() +" is done!");
	  
    	
    }

	@AfterSuite
	public void afterSuite() {
		System.out.println("Suite ended: " +  (new Date()).toString());
		System.out.println("After Suite: " +  Calendar.getInstance().getTimeInMillis());
	}
	
	 private void takeScreenshot(WebDriver driver,  ITestResult testResult)
	   {
		   
	        try {
	            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

	            // String filePathRoot = "C:\\_Jenkins\\workspace\\" + jenkinsJobName + "\\target\\surefire-reports\\";
	    		String currentPath =  System.getProperty("user.dir");
	    		String path = currentPath + "\\target\\surefire-reports\\";
	    		
	            String fullFilePath = path +  testResult.getTestClass().getName() + "\\" +
	            		RemoteDriverFactory.getInstance().getBrowser()   + "\\" + testResult.getMethod().getMethodName() + ".jpg";
                System.out.println("see screenshot/ThreadID: " + fullFilePath + "/" + Thread.currentThread().getId());
                FileUtils.copyFile(screenshot, new File(fullFilePath));
	        } catch(Exception ex) {
	            System.out.println(ex.toString());
	            System.out.println(ex.getMessage());
	        }

	   }


	
}
