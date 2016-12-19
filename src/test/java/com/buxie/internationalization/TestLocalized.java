package com.buxie.internationalization;



import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.Test; 
import org.junit.Ignore; 
import org.junit.Before;
import org.junit.After; 
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;

import org.junit.runner.Description;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
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
	static String URL;
    static {
	  
	  Properties p = System.getProperties();
	  URL = p.getProperty("AUT URL");
    }  
	  // = "https://www.google.ae";
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
	

	 @Test
	 public void testSearchالاماراربي() throws Exception
	 {  
		 page.search("العربية المتحدة");
		 fail("fail this deliberately.");
	 }
	
	     

     @After
    public void tearDown() throws Exception{
	  // driver.quit();
    	 	 
    
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
	        		String os = System.getProperty("os.name");
	        		System.out.println("See os: " + os);
	        		String path;
	        		 String fullFilePath;
	        		if (os.contains("windows")) 
	        		{	path = currentPath + "\\target\\surefire-reports\\";
	        		    fullFilePath = path + description.getClassName() + "\\" + description.getMethodName() + ".jpg";

	        		}else 
	        		{	path = currentPath + "/target/surefire-reports/";
	        		  fullFilePath = path + description.getClassName() + "/" + description.getMethodName() + ".jpg";

		             }
	                  FileUtils.copyFile(screenshot, new File(fullFilePath));
	                
	        		
	            } catch(Exception ex) {
	                System.out.println(ex.toString());
	                System.out.println(ex.getMessage());
	            }

	            driver.quit();
	        }
	    };
	
}
