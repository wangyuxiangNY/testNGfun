package com.buxie.selenium.testNGfun;


import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.NoSuchElementException;

import com.sun.corba.se.impl.orbutil.threadpool.TimeoutException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;





//import java.util.function.Function;
import com.google.common.base.Function;

public class WaitUtility {
	
	private static List<Object>  ajaxCalls;

	public static void sleep(long milliSecond)
	{
		try{
			Thread.sleep(milliSecond);
		}catch(Exception e)
		{
			
		}
	}
	
	
	public static void waitForPageToLoad(WebDriver driver) {

	     ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
	        public Boolean apply(WebDriver driver) {
	          return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	        }
	      };

	     Wait<WebDriver> wait = new WebDriverWait(driver,5*60);   //timeInSeconds, so give it 5 minutes
	      try {
	              wait.until(expectation);
	              System.out.println("Page loading is done!");
	      } catch(Throwable error) {
	              System.out.println("Timeout waiting for Page Load Request to complete.");
	      }
	      
	      WaitUtility.sleep(3000); //Give it extra time to wait.
	 } 
	
	
	/*
	public static void waitForAjax(WebDriver driver)
	{    injectJQuery(driver);
		//Check: how many on-going ajax call on this page?
		long ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
	//	System.out.println("Ajax call count:" + ajaxCallCount);
	    while (true) // Handle timeout somewhere
	    {
	        boolean ajaxIsComplete =(Boolean) ((JavascriptExecutor)driver ).executeScript("return jQuery.active == 0");
	        if (ajaxIsComplete)
	            break;
	        sleep(1000);
	    }
	   
	    ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
		System.out.println("Active Ajax call count after waiting:" + ajaxCallCount);
	}

	*/
	
	/** dynamically load jQuery */
	public static void injectJQuery(WebDriver driver){
	    String LoadJQuery = "(function(jqueryUrl, callback) {\n" +
	            "if (typeof jqueryUrl != 'string') {" +
	            "jqueryUrl = 'https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js';\n" +
	            "}\n" +
	            "if (typeof jQuery == 'undefined') {\n" +
	            "var script = document.createElement('script');\n" +
	            "var head = document.getElementsByTagName('head')[0];\n" +
	            "var done = false;\n" +
	            "script.onload = script.onreadystatechange = (function() {\n" +
	            "if (!done && (!this.readyState || this.readyState == 'loaded'\n" +
	            "|| this.readyState == 'complete')) {\n" +
	            "done = true;\n" +
	            "script.onload = script.onreadystatechange = null;\n" +
	            "head.removeChild(script);\n" +
	            "callback();\n" +
	            "}\n" +
	            "});\n" +
	            "script.src = jqueryUrl;\n" +
	            "head.appendChild(script);\n" +
	            "}\n" +
	            "else {\n" +
	            "callback();\n" +
	            "}\n" +
	            "})(arguments[0], arguments[arguments.length - 1]);\n";
	    
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	   // give jQuery time to load asynchronously
	   driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
	   js.executeAsyncScript(LoadJQuery);
	    System.out.println("Jquery is loaded.");
	}	
	
	
	
	@Test
	public static void testWebDriverWait(WebDriver driver)
	{
	
		WebElement myDynamicElement = (new WebDriverWait(driver, 10))
		  .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
		
		System.out.println("See element:" + myDynamicElement.getText() );

	}
	
	
	/**  This one is no good. I don't like it. 
	 * Waits for an element to appear on the page before returning. Example:
	 * WebElement waitElement =
	 * fluentWait(By.cssSelector(div[class='someClass']));
	 * 
	 * @param locator
	 * @return
	 */
	protected static WebElement waitForElementToAppear(WebDriver driver, final By locator, int timeOutInSecond)
	{
		 driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	        
	  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSecond, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

	  WebElement element = null;
	  try {
	    element = wait.until(new Function<WebDriver, WebElement>() {

	      public WebElement apply(WebDriver driver)
	      {
	        return driver.findElement(locator);
	      }
	    });
	  } catch (Exception e) {
	    try {
	      // I want the error message on what element was not found
	      driver.findElement(locator);
	    } catch (NoSuchElementException renamedErrorOutput) {
	      // print that error message
	      renamedErrorOutput.addSuppressed(e);
	      // throw new
	      // NoSuchElementException("Timeout reached when waiting for element to be found!"
	      // + e.getMessage(), correctErrorOutput);
	      throw renamedErrorOutput;
	    }
	    e.addSuppressed(e);
	    throw new NoSuchElementException("Timeout reached when searching for element!", e);
	  }

	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
      
	  return element;
	}
	 
	/**
	 * Waits for an element to appear on the page before returning. Example:
	 * WebElement waitElement =
	 * fluentWait(By.cssSelector(div[class='someClass']));
	 * 
	 * @param locator
	 * @return
	 */
	protected static WebElement fluentWaitIgnoreAll(WebDriver driver, final By locator, int timeOutInSecond)
	{
		cancelImplicitWait(driver);
		 
		 List<Class <? extends Exception>> exceptionsToIgnore = new ArrayList<Class <? extends Exception>>();
		 exceptionsToIgnore.add(NoSuchElementException.class);
		 exceptionsToIgnore.add(StaleElementReferenceException.class);
		 exceptionsToIgnore.add(WebDriverException.class);
		 //exceptionsToIgnore.add(Exception.class);
		
		  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeOutInSecond, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS).ignoreAll(exceptionsToIgnore);
	
		  WebElement element = null;
		  try {
			  element = wait.until(new Function<WebDriver, WebElement>() {
	
		      public WebElement apply(WebDriver driver)
		      {
		    	  return driver.findElement(locator);
		      }
		    });
		  } catch (Exception e)
		  {
			  System.out.println("Exception is thrown while fluentWait..");
			  e.printStackTrace();
		  }
	
		  setImplicitWait(driver, 20);
		  	return element;
	}
	 
	
	
	
	
	public static void cancelImplicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	
	public static void setImplicitWait(WebDriver driver, int timeInSeconds)
	{
		driver.manage().timeouts().implicitlyWait(timeInSeconds, TimeUnit.SECONDS);
	}
	
	//Handle StaleElementReferenceException
	public static void selectDropDown(WebDriver driver, By by, int index)
	{
		try{
		   new Select(driver.findElement(by)).selectByIndex(index);
		}catch(StaleElementReferenceException e)
		{
			System.out.println("ooh, stinks. Try again.");
			new Select(driver.findElement(by)).selectByIndex(index);
		}
		
	}
	/*
	 * Use retry to handle. Not a good one indeed. 
	 */
	public void StaleElementHandleByID (WebDriver driver, String elementID){
		int count = 0;
		boolean clicked = false;
		while (count < 4 || !clicked){
		    try {
		       WebElement yourSlipperyElement= driver.findElement(By.id(elementID));
		       yourSlipperyElement.click(); 
		       clicked = true;
		     } catch (StaleElementReferenceException e){
		       e.toString();
		       System.out.println("Trying to recover from a stale element :" + e.getMessage());
		       count = count+1;
		     }     
		}
	
	}
	
	public WebElement waitForElementToBeVisible(WebDriver driver, By locator)
	{
		WebElement element = driver.findElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, 20); //here, wait time is 20 seconds
	
		wait.until(ExpectedConditions.visibilityOf(element)); //this will wait for elememt to be visible for 20 seconds
		return element;
	}	
	
	
	public void clickElement(WebDriver driver, By locator)
	{
		WebElement element = driver.findElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, 20); //here, wait time is 20 seconds
	
		wait.until(ExpectedConditions.visibilityOf(element)); //this will wait for elememt to be visible for 20 seconds
		element.click();
	}
	
	   public static List tryScript(WebDriver driver) throws Exception
	    {
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	        //List<Object> result = new LinkedList<Object>();
	    	List<Object> result = (List)js.executeScript("window.myURLs = ['aa']; window.myURLs.push('bb'); return window.myURLs");
	    	 
	    	return result;

	    }
	   

	
	   
    public static String fetchAjax(WebDriver driver) throws Exception
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	
        String result = "";
    	
     
        result = (String)js.executeAsyncScript("(function(open, callback) {" +
        		   		" var ajaxURL;" +
        		        " var ajaxResponse;" +
        		        "var dataArray = [];" +
        		   		"function onStateChange(event) { "+
		        		    "console.log('STATE HAS changed.' + this.readyState + '/' + this.status );" +
		        		    " if (this.readyState === 4 && this.status == 200) {" +
		        		     "console.log('AJAX IS DONE. see response:' + this.responseText);"+
		        		     " ajaxResponse = this.responseText;" +
							//" console.log('see event.data/target:' + event.data + '/' + event.target);"+ 
		        		    
		        		    // fires on every readystatechange ever
		        		    // use `this` to determine which XHR object fired the change event
		        		    " setTimeout(function() {" +
		        		    "    console.log('2s wait is done.'); " +
		        		   " }, 2000);" +
		        		 "}}"+
		        		    
		        		 
        		   		"XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {" +
        		   		   " ajaxURL =  url;" +
        		   	
                    	"	 this.addEventListener('readystatechange', onStateChange);" +
        				
                    	"  open.call(this, method, url, async, user, pass);" +
                    	" dataArray.push(url);"+
                    	"callback(url);" + 
        				"};" +
                   //callback(dataArray);"+
				
       		"})(XMLHttpRequest.prototype.open,arguments[arguments.length - 1]);" 
        	
           		 );  
         
       return result;
  }						
    
    
	
    public static void interceptAjax(WebDriver driver) throws Exception
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        String result = "";
     
         result = (String)js.executeAsyncScript("(function(open, callback) {" +
        		   		" var ajaxURL;" +
        		   
        		   		"function onStateChange(event) { "+
		        		    "console.log('STATE HAS changed.' + this.readyState + '/' + this.status );" +
		        		    " if (this.readyState === 4 && this.status == 200) {" +
		        		     "console.log('AJAX IS DONE. see response:' + this.responseText);"+
							//" console.log('see event.data/target:' + event.data + '/' + event.target);"+ 
		        		    
		        		    // fires on every readystatechange ever
		        		    // use `this` to determine which XHR object fired the change event
		        		    " setTimeout(function() {" +
		        		    "    console.log('2s wait is done.'); " +
		        		   " }, 2000);" +
		        		 "}}"+
		        		    
		        		 
        		   		"XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {" +
        		   		" ajaxURL =  url;" +
        		   		"	    console.log('see ajax calls:' + url  );"+
                    	//"	   console.log('WHY? url contains getTracks?' + url.indexOf('getTracks'));"+
        		   		" if (url.indexOf('getStreamUrl') >=0 ){ " +
                    	"	    this.addEventListener('readystatechange', onStateChange);}" +
        				
                    	"  open.call(this, method, url, async, user, pass);" +
        			
        				"};" +
				"callback();"+
        		"})(XMLHttpRequest.prototype.open,arguments[arguments.length - 1]);" 
        	
           		 );  
          
  }						
    
    
    public static String fetchtAjaxSendData(WebDriver driver) throws Exception
    {   String result="";
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        result = (String) js.executeAsyncScript("(function(send, callback) {" +
        		  "var callback = arguments[arguments.length - 1];" +
						"XMLHttpRequest.prototype.send = function(data) {" +
							"	    console.log('see data:' + data  );"+
						    " sentData = data;" +
							"	send.call(this, data);" +
						    " callback(data);" +
						"};" + 
				//"callback(sentData);"+
        		"})(XMLHttpRequest.prototype.send,arguments[arguments.length - 1]);" 
        );  
        System.out.println("See sent ajax data:" + result);
        return result;
        
    }						
    
    
    public static void interceptAjaxSendData(WebDriver driver) throws Exception
    {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeAsyncScript("(function(send, callback) {" +
						"XMLHttpRequest.prototype.send = function(data) {" +
							"	    console.log('see data:' + data  );"+
							"	send.call(this, data);" +
						"};" + 
				"callback();"+
        		"})(XMLHttpRequest.prototype.send,arguments[arguments.length - 1]);" 
        );  
    }						
    
    
    
}
