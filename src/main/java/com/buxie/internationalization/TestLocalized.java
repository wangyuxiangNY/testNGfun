package com.buxie.internationalization;



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


public class TestLocalized {

	 WebDriver driver;
	 GooglePage page;
	
	
	String browser = "chrome";
	//String browser = "firefox";
	//String browser = "edge";
	// String browser = "ie";
	 
	final String URL = "https://www.google.ae";
			//"http://www.google.co.jp";
	//"http://www.google.hk/";
	

	
	@Rule public TestName name = new TestName();
	
	
	@Before
    public void init()throws Exception
	{
		driver = Utils.launchBrowser(URL, browser);
		
        page = new GooglePage(driver);
        
    }
	
		

	 @Test
	 public void testSearch() throws Exception
	 {  
	 
		page.search("الامارات العربية المتحدة");
	 }
	

	     

     @After
    public void tearDown() throws Exception{
	  //  driver.quit();
    	 
    	 
    
    }

    
	
}
