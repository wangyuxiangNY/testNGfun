package com.buxie.ajax;



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

import com.buxie.selenium.testNGfun.Utils;
import com.buxie.selenium.testNGfun.WaitUtility;

import org.openqa.selenium.chrome.ChromeDriver;


public class RunWithJunit {
	 WebDriver driver;
	
	LiveRadioPage liveRadio;
	NavigationForAjax navigation;
	
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
		
        liveRadio = new LiveRadioPage(driver);
        navigation = new NavigationForAjax(driver);
        
    }
	
		

	 @Test
	 public void testLiveFilter() throws Exception
	 {  
	 
		 NavigationForAjax.gotoLive();
		 //WaitUtility.injectJQuery(driver);
		 
		 liveRadio.filterStation(1, 2, 3);
	 }
	

	     

     @After
    public void tearDown() throws Exception{
	  //  driver.quit();
    	 
    	 
    
    }

    
}
