package com.buxie.selenium.testNGfun;


import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class RemoteDriverFactory {

	private String hubURL;
	 private String browser ="chrome";
	 private String platform ="win";
	 
     
	   private RemoteDriverFactory()
	   {
	      //Do-nothing..Do not allow to initialize this class from outside
	   }
	   private static RemoteDriverFactory instance = new RemoteDriverFactory();

	   public static RemoteDriverFactory getInstance()
	   {
	      return instance;
	   }

	   ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>() // thread local driver object for webdriver
	   {
	      @Override
	      protected RemoteWebDriver initialValue()
	      {
	         return createRemoteDriver(hubURL, browser, platform); // can be replaced with other browser drivers
	      }
	   };
	   
	   public synchronized void setHubURL(String url)
	   {
		   this.hubURL = url;
	   }
	   
	   public String getHubURL( )
	   {
		   return hubURL;
	   }
	   
	   
	   public synchronized void setPlatform(String platform)
	   {
		   this.platform = platform;
	   }
	   
	   public String getPlatform( )
	   {
		   return platform;
	   }

	   public synchronized void setBrowser(String browser)
	   {
		   this.browser = browser;
	   }
	   
	   public String getBrowser( )
	   {
		   return browser;
	   }
	   
	   public RemoteWebDriver getDriver() // call this method to get the driver object and launch the browser
	   {
		   
	      return driver.get();
	   }

	   public void removeDriver() // Quits the driver and closes the browser
	   {
	      driver.get().quit();
	      driver.remove();
	   }
	
	   
	   
	   private  RemoteWebDriver  createRemoteDriver(String hubURL, String browser, String platform)
		{  
			RemoteWebDriver driver;
		
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if (browser.equalsIgnoreCase("intenet explorer") || browser.equalsIgnoreCase("ie"))
			{
				File file = new File("C:\\Users\\azurewangyx\\seleniumDownloads\\iexploredriver.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				capabilities = DesiredCapabilities.internetExplorer();
			//	capabilities.setVersion("11");
			}else if (browser.equalsIgnoreCase("chrome"))
			{
				File file = new File("C:\\Users\\azurewangyx\\seleniumDownloads\\chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				capabilities = DesiredCapabilities.chrome();
			//	capabilities.setVersion("11");
			}else 
		    	capabilities.setBrowserName(browser);
			
			if (browser.equalsIgnoreCase("chrome"))
			{
				ChromeOptions options = new ChromeOptions();
				// On Linux start-maximized does not expand browser window to max screen size. Always set a window size.
				if (platform.equalsIgnoreCase("linux")) 
				{
					options.addArguments(Arrays.asList("--window-size=1920,1080"));	
				} else
				{
					options.addArguments(Arrays.asList("--start-maximized"));
				}
				
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			} 
			
			if (platform.equalsIgnoreCase("windows"))
		    	capabilities.setPlatform(Platform.WIN10);
			else
				capabilities.setPlatform(Platform.MAC);
		
			//replace USERNAME:ACCESS_KEY@SUBDOMAIN with your credentials found in the Gridlastic dashboard
			try
			{
			    driver = new RemoteWebDriver(new URL(hubURL),capabilities);
			}catch(Exception e)
			{
				e.printStackTrace();
				return null;
			}
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize(); // Always maximize firefox on windows
			
	     // On LINUX/FIREFOX the "driver.manage().window().maximize()" option does not expand browser window to max screen size. Always set a window size.
	 	if (platform.equalsIgnoreCase("linux") && browser.equalsIgnoreCase("firefox")) {
	 		driver.manage().window().setSize(new Dimension(1920, 1080));	
	 	}
	     
			
			return driver;
		}	
		
		
}
