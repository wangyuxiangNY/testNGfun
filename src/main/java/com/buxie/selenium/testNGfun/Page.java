package com.buxie.selenium.testNGfun;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.buxie.forVideo.FlashObjectWebDriver;

import org.apache.log4j.Logger;

public abstract class Page {
	
	//ever-present elements
	 @FindBy(css=".input") public WebElement searchBox;
	 @FindBy(name="Filter") public WebElement searchFilter;
	   
	
	@FindBy(css=".icon-account")
	   private  WebElement login;
	@FindBy(name="userName") private   WebElement userName;
	@FindBy(name ="password") private   WebElement password;
	
	@FindBy(css="button.btn-login") private   WebElement loginButton;
	
	@FindBy(css=".account-dropdown > div:nth-child(1) > button:nth-child(1)") 
		public WebElement signedAccount;
	
	
	
	final static Logger logger = Logger.getLogger(Page.class);
 	
	public  WebDriver driver;
	
	 String browser ="";
	 final String USER_NAME ="iheartrocks888@gmail.com";
	 final  String PASSWORD ="iheart001";
	 final  String FACEBOOK_USER_NAME = USER_NAME;
	 final String GOOGLE_USER_NAME = USER_NAME;
	
	
	
	private  StringBuffer errors = new StringBuffer(); 
	
		
	public Page()
	{  
		PageFactory.initElements(driver, this);
		
	}
	
	public Page(WebDriver _driver)
	{   
		this.driver = _driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public  void login()
	{   // USER_NAME ="iheartrocks888@gmail.com";
		// PASSWORD ="iheart001";
		
		 WaitUtility.sleep(5000);
		 login.click();
		// driver.findElement(By.cssSelector(".icon-account")).click();
		 
		//WebElement mylogin= WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".icon-account"), 15);
		
	
		
		WaitUtility.waitForPageToLoad(driver);
		WaitUtility.fluentWaitIgnoreAll(driver, By.name("username"), 2).sendKeys(USER_NAME);
		WaitUtility.waitForPageToLoad(driver);
		password.sendKeys(PASSWORD);
		loginButton.click();
		WaitUtility.waitForPageToLoad(driver);
	}
	
	
	public boolean  isElementPresent(WebElement element)
	{
		 try{
			  System.out.println("see element:" +  element.getText());
			   return true;
		   }catch(Exception e)
		   {  // e.printStackTrace();
			   return false;
		   }
	}
	
	public  void setBrowser(String _browser)
	{
		browser = _browser;
	}
	
	
	public  StringBuffer getErrors()
	{
		return errors;
	}
	
	public void handleError(String msg, String methodName) 
	{
		errors.append(msg);
		
	}
	
	
	public  String  switchWindow()
	{
		//Switch to new tab where the sign-up is
		String winHandleBefore = driver.getWindowHandle();
		//Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}	
		
		WaitUtility.waitForPageToLoad(driver);
		
		return winHandleBefore;
	}
	
	
	
	public  void clearErrors()
	{
		errors.setLength(0);
	}
	
	public  String getUserName()
	{
		return USER_NAME;
	}
	
	public  String getPassword()
	{
		return PASSWORD;
	}
	
	
	public  WebDriver getDriver()
	{
		return driver;
	}
	
	public void search(String what, String  filterCategory)
	{   searchBox.clear();
		searchBox.sendKeys(what);
		WaitUtility.waitForPageToLoad(driver);
		new Select(searchFilter).selectByValue(filterCategory);//selectByIndex(filterIndex);
		WaitUtility.waitForPageToLoad(driver);
	}
	
	public void search(String what, int filterIndex)
	{   searchBox.clear();
		searchBox.sendKeys(what);
		WaitUtility.waitForPageToLoad(driver);
		if (filterIndex >= 0)
		{   new Select(searchFilter).selectByIndex(filterIndex);
		    WaitUtility.waitForPageToLoad(driver);
		}    
	}
	
	public boolean searchResultShown()
	{
		if (driver == null)
			System.out.println("driver is null in searchResultShown()");
		List<WebElement> resultRows = driver.findElements(By.className("search-section"));
		System.out.println(resultRows.size() + " rows are suggested.");
		
		if (resultRows == null || resultRows.size() <1)
	    	return false;
		
	    return true;
	}
	
	
	
	
	public  void waitForPreroll()
	{
		WaitUtility.sleep(45000);
	}
	
	
    public static void takeScreenshot(WebDriver driver, String testMethod) throws Exception 
    {      
 	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
    			Date date = new Date();
    			//System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
 	       String screenshotName = testMethod + dateFormat.format(date) + ".png";
 	       System.out.println("See screenshotName:" + screenshotName);
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, new File(screenshotName));
            System.out.println("Screenshot is taken.");
    }
    
    
    public static void takeRemoteScreenshot(RemoteWebDriver driver, String testMethod) throws Exception
    {
    	takeScreenshot(new Augmenter().augment(driver ), testMethod);
    }
    
    public  WebElement waitForElement( WebElement element, long timeOutInMilliSecond)
	{
    	return waitForElement(driver, element, timeOutInMilliSecond);
	}
    
	//.isDisplayed() doesn't work with iheart elements, This might have something to do ajax elements
	public static WebElement waitForElement(WebDriver driver, WebElement element, long timeOutInMilliSecond)
	{  
		long times = timeOutInMilliSecond / 500 + 1;    
		long count = 0;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		
		do{
			try{
			   System.out.println(element.getAttribute("outerHTML"));
			  if (element.isEnabled())
			      break;
			}catch(Exception e)
			{  System.out.println("Not there. try again.");
			  // e.printStackTrace();
			   WaitUtility.sleep(500);
			}
			
			count++;
		}while (count< times);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return element;
	}
	
	
	public  void hoverThenClick(WebElement hoverTo, WebElement clickTarget)
	{
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(hoverTo).click(clickTarget).build().perform();
		 
	}
	
	
	public  void hoverThenClick(WebElement hoverTo, By locator , int timeOutInSecond)
	{
		Actions actionBuilder = new Actions(driver);
		actionBuilder.moveToElement(hoverTo).perform();
		WaitUtility.sleep(200);
		driver.findElement(locator).click();
		//WebElement clickable =  WaitUtility.fluentWaitIgnoreAll(driver, locator, 2);
		//actionBuilder.moveToElement(hoverTo).click(clickable).build().perform();
		//actionBuilder.moveToElement(hoverTo).click(WaitUtility.fluentWaitIgnoreAll(driver, locator, 2)).build().perform();
	  //  clickable.click();
		
		 
	}

    public  boolean isLoggedIn()
    {
    	return signedAccount.getText().length() > 0;
    }
    
    
    public SignUpPage getSignUpPage()
    {
    	return PageFactory.initElements(driver, SignUpPage.class);
    }
    
    public Player getPlayer()
    {
    	return PageFactory.initElements(driver, Player.class);
    }
    
    public void handlePreroll()
    {  
    	WebElement ad = WaitUtility.waitForElementToBeVisible(driver, By.id("ads-player"), 30);
        System.out.println("See ad:" + ad.getAttribute("innerHTML"));
    	
        WaitUtility.waitForElementToDisppear(driver, By.id("ads-player"), 35);
    	System.out.println("Preroll is done.");
    }
    
    public void pausePreroll()
    {
    	WebElement ad = WaitUtility.waitForElementToBeVisible(driver, By.id("ads-player"), 30);
        System.out.println("See ad:" + ad.getAttribute("innerHTML"));
        WaitUtility.sleep(5000);
    	FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver, "ads-player");
    	flashApp.callFlashObject("pauseVideo");
    	System.out.println("Preroll paused.");
    	 WaitUtility.sleep(5000);
    	
    }
    
    public void resumePreroll()
    {   
    	FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver, "ads-player");
    	flashApp.callFlashObject("playVideo");
    	System.out.println("Preroll resumed.");
    }
}