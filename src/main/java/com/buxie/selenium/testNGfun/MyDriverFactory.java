package com.buxie.selenium.testNGfun;

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

public class MyDriverFactory {
	 private static final ThreadLocal<WebDriver> driver;
 
	 private static final ThreadLocal<String> browser;
     
	
	   private MyDriverFactory()
	   {
	      //Do-nothing..Do not allow to initialize this class from outside
	   }
	   private static MyDriverFactory instance = new MyDriverFactory();

	   public static MyDriverFactory getInstance()
	   {
	      return instance;
	   }

	
		 static {
	        /**
	         * dirver is a ThreadLocal<ChromeDriver>. This allows the driver to be static and available for the complete project, and exclusive for each thread.
	         */
	        driver = new ThreadLocal<WebDriver>() {
	            @Override
	            protected WebDriver initialValue() {
	                return createWebDriver(getBrowser());
	            }
	        };
	    }
		 
		 static {
		        /**
		         * browser is a ThreadLocal<String>. This allows the browser to be static and available for the complete project, and exclusive for each thread.
		         */
		        browser = new ThreadLocal<String>() {
		            @Override
		            protected String initialValue( ) {
		               return getBrowser();
		          
		            }
		        };
		    }
		 	 
	 
	   public  static void setBrowser(String _browser)
	   {
		   browser.set( _browser);
	   }
	   
	   public static String getBrowser( )
	   {
		   return browser.get();
	   }
	   
	   public   WebDriver getDriver() // call this method to get the driver object and launch the browser
	   {
		   
	      return driver.get();
	   }

	   public void removeDriver() // Quits the driver and closes the browser
	   {  System.out.println("About to remove driver:" + Thread.currentThread().getId());
	    //  driver.get().quit();
	      driver.remove();
	      browser.remove();
	      System.out.println("driver removed:" + Thread.currentThread().getId());
		    
	   }
	
	   
	   private  WebDriver  createWebDriver( ) 
	   {  
		   return createWebDriver("chrome");
	   }
	   
	   private  static  WebDriver  createWebDriver(String browser) 
		{   
		   System.out.println("About to createWebDriver().." + Thread.currentThread().getId());
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
			      System.out.println("Unknown browser.");
			      return null;
		      }
		
		      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		      driver.manage().window().maximize();
		     
		      System.out.println("Done createWebDriver().." + Thread.currentThread().getId());
				
		      return driver;
		
		  }
		
	   
	
}
