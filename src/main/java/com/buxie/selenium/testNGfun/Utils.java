package com.buxie.selenium.testNGfun;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.FileDetector;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;



public class Utils {
	public static final String browserStack_USERNAME = "robrowe";
	public static final String browserStack_AUTOMATE_KEY = "SGtW65fVhR9zqp7KpVUo";
	public static final String browserStack_URL = "http://" + browserStack_USERNAME + ":" + browserStack_AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";


	public static WebDriver  createWebDriver() 
	
	{
	
	return createWebDriver("firefox");
	
	}
	
	
	public static WebDriver  createWebDriver(String browser) 
	
	{   WebDriver driver;
	
	    if (browser.equalsIgnoreCase("firefox"))
	    {
	    	
	       driver = new FirefoxDriver();
	
	    }else if (browser.equalsIgnoreCase("chrome"))
	    {   
	    	  ChromeDriverManager.getInstance().setup();
		      ChromeOptions options = new ChromeOptions();
		      options.addArguments("test-type");
		      
		      if (OSDetector().equals("Windows"))
		          options.addArguments("--start-maximized");
		      else
		    	  options.addArguments("--kiosk");
		      driver = new ChromeDriver(options);
	      
	      }else if (browser.equalsIgnoreCase("ie"))
	      {   
	    	  InternetExplorerDriverManager.getInstance().setup();
	    	  driver = new InternetExplorerDriver();
	
	      }else  if (browser.equalsIgnoreCase("edge"))
	      {
	    	  String serverPath = "C:\\Users\\azurewangyx\\Downloads\\MicrosoftWebDriver.exe";
	    	  //System.setProperty("dwebdriver.edge.driver", new File( serverPath).getAbsolutePath());
	    	  System.setProperty("webdriver.edge.driver", serverPath);
	    	  
	    	//  EdgeOptions options = new EdgeOptions();
	    	//  options.setPageLoadStrategy("eager");
	    	  
	    	  DesiredCapabilities capabilities = DesiredCapabilities.edge();
	    	  capabilities.setPlatform(Platform.WIN10);
	    	  capabilities.setBrowserName("MicrosoftEdge");
	    	  driver = new EdgeDriver(capabilities);
	    	  
	      }else
	      {
		      System.out.println("Unknown browser.");
		      return null;
	      }
	
	      driver.manage().window().maximize();
	
	      return driver;
	
	  }
	

	public static String OSDetector() 
	{
		String os = System.getProperty("os.name").toLowerCase();
		System.out.println("see os:" + os) ;
		if (os.contains("win")) {
			return "Windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		}else if (os.contains("mac")) {
			return "Mac";
		}else if (os.contains("sunos")) {
			return "Solaris";
		}else 
			return "Other";
	}
	
	
	
	public static WebDriver  createRemoteDriver(String browser,String browserVersion) throws Exception
	{  
		WebDriver driver;
		
		 final String VIDEO_URL="LOCALHOST";
		
		// Example test environment. NOTE: Gridlastic auto scaling requires all
		// these 3 environment variables in each request.
		String platform_name = OSDetector(); //"win7";
		String browser_name = "firefox";
		String browser_version = "46"; // for Chrome leave empty

		// optional video recording
		String record_video = "True";

		DesiredCapabilities capabilities = new DesiredCapabilities();
		if (platform_name.equalsIgnoreCase("win7")) {
			capabilities.setPlatform(Platform.VISTA);
		}
		if (platform_name.equalsIgnoreCase("win8")) {
			capabilities.setPlatform(Platform.WIN8);
		}
		if (platform_name.equalsIgnoreCase("win8_1")) {
			capabilities.setPlatform(Platform.WIN8_1);
		}
		if (platform_name.equalsIgnoreCase("linux")) {
			capabilities.setPlatform(Platform.LINUX);
		}
		
		if (platform_name.equalsIgnoreCase("mac")) {
			capabilities.setPlatform(Platform.MAC);
		}
		
		capabilities.setBrowserName(browser_name);
		capabilities.setVersion(browser_version);

		// video record
		if (record_video.equalsIgnoreCase("True")) {
			capabilities.setCapability("video", "True"); // NOTE: "True" is a case sensitive string, not boolean.
		} else {
			capabilities.setCapability("video", "False"); // NOTE: "False" is a case sensitive string, not boolean.
		}
		//??? Here, needs refinement, or simply utilize createDriver() for LOCAL
		if (browser_name.equalsIgnoreCase("chrome")){
			ChromeOptions options = new ChromeOptions();
			// On Linux start-maximized does not expand browser window to max screen size. Always set a window size.
			if (platform_name.equalsIgnoreCase("linux")) {
				options.addArguments(Arrays.asList("--window-size=1920,1080"));	
				} else {
				options.addArguments(Arrays.asList("--start-maximized"));
				}
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			} 
		
	
		//replace USERNAME:ACCESS_KEY@SUBDOMAIN with your credentials found in the Gridlastic dashboard
		driver = new RemoteWebDriver(new URL("http://USERNAME:ACCESS_KEY@localhost:80/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize(); // Always maximize firefox on windows
		
     // On LINUX/FIREFOX the "driver.manage().window().maximize()" option does not expand browser window to max screen size. Always set a window size.
 	if (platform_name.equalsIgnoreCase("linux") && browser_name.equalsIgnoreCase("firefox")) {
 		driver.manage().window().setSize(new Dimension(1920, 1080));	
 	}
     

		if (record_video.equalsIgnoreCase("True")) {
			System.out.println("Test Video: " + VIDEO_URL + ((RemoteWebDriver) driver).getSessionId());
		}
		
		return driver;
	}	
	
	
	public static WebDriver  createRemoteDriver(String browser,String browserVersion, String osName, String os_version) 
	{  
		WebDriver driver;
		
		DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("browser", browser);
	    caps.setCapability("browser_version", browserVersion);
	    caps.setCapability("os", osName);
	    caps.setCapability("os_version", os_version);
	    caps.setCapability("browserstack.debug", "true");
	   
	    try{
	       driver = new RemoteWebDriver(new URL(browserStack_URL), caps);
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	    driver.manage().window().maximize();
	
	   driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	
	    return driver;
	
	  }
	
	
	
	
	public static void waitForPageToLoad(WebDriver driver) {
	    ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	
	        public Boolean apply(WebDriver driver) {
	
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	
	        }
	
	      };
	
	    Wait<WebDriver> wait = new WebDriverWait(driver,1000);
	
	      try {
	
	              wait.until(expectation);
	
	      } catch(Throwable error) {
	
	              System.out.println("Timeout waiting for Page Load Request to complete.");
	
	      }
	
	} 
	
	
	
	
	public static WebDriver launchBrowser(String url, String browser)
	{       Page.setBrowser(browser);
			WebDriver driver = createWebDriver(browser);
			
			driver.get(url);
			
		//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			return driver;
	
	}
	

	
	public static int getRandomInt()
	{
		Random randomGenerator = new Random();
	  
	    int randomInt = randomGenerator.nextInt(999999);
	     
	    return randomInt;
	    
	}
	
	public static Map<String, String> getLocationByIp(WebDriver driver)
	{    Map<String, String> geoInfo = new HashMap<String, String>();
		 driver.navigate().to("http://www.iplocation.net");
		 String country = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(2)")).getText();
		 String state = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(3)")).getText();
		 String city = driver.findElement(By.cssSelector("#geolocation > table:nth-child(2) > tbody > tr:nth-child(4) > td:nth-child(4)")).getText();
		 
		 geoInfo.put("country", country);
		 geoInfo.put("state", state);
		 geoInfo.put("city", city);
		 
		 return geoInfo;
	}
	

	   
	   public static String getCurrentDateString()
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
			Date date = new Date();
			return dateFormat.format(date);
		}
		
		public static String getCurrentDateInMilli()
		{
			Date date = new Date();
			return date.getTime() + "";
		}
	   
	
	
	public static void scrollElementIntoView(WebDriver driver, WebElement element)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver; 
		jse.executeScript("arguments[0].scrollIntoView()", element);
	}
	
	
	public static void scrollScreenDown(WebDriver driver,  int offset)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver; 
		jse.executeScript("window.scrollBy(0," + offset + ")", "");
		//jse.executeScript("window.scrollBy(0,450)", "");
		
	}
	
	public static void scrollScreenRight(WebDriver driver, int offset)
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver; 
		jse.executeScript("scroll(0," + offset);
		//jse.executeScript("scroll(0, 250)"); // if the element is on bottom.
	}
	
	
	public static void pressKey(WebDriver driver)
	{
		//to be done
	}
	
	//If file resides locally
	public static void uploadFile(WebDriver driver, By by, String pathToFile)
	{
		WebElement upload = driver.findElement(by);
		upload.sendKeys(pathToFile);
	}
	
	//If file is on some other machine, like when you run your cases on GRID
	public static void uploadFileFromRemote(RemoteWebDriver driver, By by, String pathToFile)
	{   
		driver.setFileDetector(new LocalFileDetector());
		WebElement upload = driver.findElement(by);
		upload.sendKeys(pathToFile);
	}
	
	
	//	Big Wait Section
	public static WebElement getWhenVisible(WebDriver driver, By locator, int timeout) {
	    WebElement element = null;
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	    return element;
	}

	public static void clickWhenReady(WebDriver driver, By locator, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	    element.click();
	}
	

	public static void clickWhenReady(WebDriver driver, WebElement element, int timeout) {
	    WebDriverWait wait = new WebDriverWait(driver, timeout);
	    WebElement _element = wait.until(ExpectedConditions.elementToBeClickable(element));
	    _element.click();
	}
    	
	

}
