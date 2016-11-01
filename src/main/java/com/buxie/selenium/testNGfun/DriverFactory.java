package com.buxie.selenium.testNGfun;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;


public class DriverFactory
{
	protected  final static  Logger logger = LoggerFactory.getLogger(DriverFactory.class);

       private static String browser ="chrome";
       
	   private DriverFactory()
	   {
	      //Do-nothing..Do not allow to initialize this class from outside
	   }
	   private static DriverFactory instance = new DriverFactory();

	   public static DriverFactory getInstance()
	   {
	      return instance;
	   }

	  static  ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
	   {
	      @Override
	      protected WebDriver initialValue()
	      {
	         return createWebDriver(browser); // can be replaced with other browser drivers
	      }
	   };
	   
	  

	   public synchronized void setBrowser(String browser)
	   {
		   this.browser = browser;
	   }
	   
	   public String getBrowser( )
	   {
		   return browser;
	   }
	   
	   public  synchronized WebDriver getDriver() // call this method to get the driver object and launch the browser
	   {
		   
	      return driver.get();
	   }

	   public void removeDriver() // Quits the driver and closes the browser
	   {  logger.info("About to remove driver:" + Thread.currentThread().getId());
	      driver.get().quit();
	      driver.remove();
	      logger.info("driver removed:" + Thread.currentThread().getId());
		    
	   }
	
	   
	   private  WebDriver  createWebDriver( ) 
	   {  
		   return createWebDriver("chrome");
	   }
	   
	   public  static  WebDriver  createWebDriver(String browser) 
		
		{   
		   logger.info("About to createWebDriver().." + Thread.currentThread().getId());
		   WebDriver driver;
		
		    if (browser.equalsIgnoreCase("firefox"))
		    {  
		      //  System.setProperty("webdriver.gecko.driver","C:\\Users\\azurewangyx\\seleniumDownloads\\geckodriver.exe");
		    	FirefoxProfile profile = new FirefoxProfile();
		    //	profile.setPreference("browser.usedOnWindows10", false);
		    //	profile.setPreference("browser.usedOnWindows10.introURL", "about:blank");
		        profile.setPreference("browser.startup.homepage_override.mstone", "ignore");
		    	profile.setPreference("browser.startup.homepage","about:blank");


		    	//profile.setPreference("startup.homepage_welcome_url.additional",  "about:blank");
		    	
		    	driver = new FirefoxDriver(profile);
		    	//driver = new FirefoxDriver();
		
		    }else if (browser.equalsIgnoreCase("chrome"))
		    {   
		    	  ChromeDriverManager.getInstance().setup();
			      ChromeOptions options = new ChromeOptions();
			      options.addArguments("test-type");
			      
			     
			      options.addArguments("--start-maximized");
			   
			      driver = new ChromeDriver(options);
		      
		      }else if (browser.equalsIgnoreCase("ie"))
		      {   
		    	  InternetExplorerDriverManager.getInstance().setup();
		    	  driver = new InternetExplorerDriver();
		
		      }else  if (browser.equalsIgnoreCase("edge"))
		      {
		    	  
		    	  String serverPath = "C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe";
		    	  System.setProperty("webdriver.edge.driver", serverPath);
		    	  
		    	  //EdgeDriverManager.getInstance().setup();
		    	  
		    	  // EdgeOptions options = new EdgeOptions();
		    	   //options.setPageLoadStrategy("eager");
		    	  
		    	   driver = new EdgeDriver();
		    	  
		      }else
		      {
			      logger.info("Unknown browser.");
			      return null;
		      }
		
		      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		      driver.manage().window().maximize();
		     
		      logger.info("Done createWebDriver().." + Thread.currentThread().getId());
				
		      return driver;
		
		  }
		
	   

}
