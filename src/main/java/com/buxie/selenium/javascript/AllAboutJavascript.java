package com.buxie.selenium.javascript;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.buxie.selenium.testNGfun.WaitUtility;

public class AllAboutJavascript {
	
	public void highLightElement(WebDriver driver, WebElement element)
	{
		((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute(arguments[1], arguments[2])",
	    		 element, "style", "border: 2px solid yellow; color: yellow; font-weight: bold;");
	}     


	public static void wait(WebDriver driver)
	{
		 driver.manage().timeouts().setScriptTimeout(10, java.util.concurrent.TimeUnit.SECONDS) ;
		   long start = System.currentTimeMillis();
		  ((JavascriptExecutor) driver).executeAsyncScript(
		
		       "window.setTimeout(arguments[arguments.length - 1], 5000);" 
		    ) ;
		   System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

	}
	
	
	public static void waitForAjax(WebDriver driver)
	{    injectJQuery(driver);
		//Check: how many on-going ajax call on this page?
		long ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
		System.out.println("Ajax call count:" + ajaxCallCount);
	    while (true) // Handle timeout somewhere
	    {
	        boolean ajaxIsComplete =(Boolean) ((JavascriptExecutor)driver ).executeScript("return jQuery.active == 0");
	        if (ajaxIsComplete)
	            break;
	        WaitUtility.sleep(500);
	    }
	   
	    ajaxCallCount = (Long)((JavascriptExecutor)driver ).executeScript("return jQuery.active");
		System.out.println("Active Ajax call count after waiting:" + ajaxCallCount);
	}
	
	public static void anotherWait(WebDriver driver)
	{
		 driver.manage().timeouts().setScriptTimeout(10, java.util.concurrent.TimeUnit.SECONDS) ;
		String script = "var avengers = ['Captain America', 'Iron Man', 'Thor', 'The Vision'];" +
						"var list = []; " +
						"var callback = arguments[arguments.length - 1];" +
						"setTimeout(function() {" +
						"for (var i = 0; i < avengers.length; i++) {" +
						"console.log('Assemble: ' + avengers[i]);" +
						"list.push(avengers[i]);" +
						"console.log('Currently Assembled: ' + list);" +
						" callback(list);" +
						"}" +
					"}, 3000);" +
 
				"console.log('Final List: '+ list);" +
				"console.log('Avengers Assembled!');";
		
		   long start = System.currentTimeMillis();
		  List<String>  result = (List<String>)((JavascriptExecutor) driver).executeAsyncScript(script);
	
		  System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));
		   
		  System.out.println("See return result:" + result.toString());
	}
	
	/*
	function addXMLRequestCallback(callback){
	    var oldSend, i;
	    if( XMLHttpRequest.callbacks ) {
	        // we've already overridden send() so just add the callback
	        XMLHttpRequest.callbacks.push( callback );
	    } else {
	        // create a callback queue
	        XMLHttpRequest.callbacks = [callback];
	        // store the native send()
	        oldSend = XMLHttpRequest.prototype.send;
	        // override the native send()
	        XMLHttpRequest.prototype.send = function(){
	            // process the callback queue
	            // the xhr instance is passed into each callback but seems pretty useless
	            // you can't tell what its destination is or call abort() without an error
	            // so only really good for logging that a request has happened
	            // I could be wrong, I hope so...
	            // EDIT: I suppose you could override the onreadystatechange handler though
	            for( i = 0; i < XMLHttpRequest.callbacks.length; i++ ) {
	                XMLHttpRequest.callbacks[i]( this );
	            }
	            // call the native send()
	            oldSend.apply(this, arguments);
	        }
	    }
	}

	public void getEverythingAjax()
	{
		addXMLRequestCallback( function( xhr ) {
		    console.log( xhr.responseText ); // (an empty string)
		});
		addXMLRequestCallback( function( xhr ) {
		    console.dir( xhr ); // have a look if there is anything useful here
		});
	}	
	*/

	 public static List<String> fetchAjaxRequest(WebDriver driver) throws Exception
	 {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	    	
	       // String result = "";
	    	List<String> result;
	     
	        result = (List<String>)js.executeAsyncScript("(function(open, callback) {" +
	        		   		" var ajaxURL;" +
	        		        " var ajaxResponse;" +
	        		        "var dataArray = [];" +
	        		    	"function onStateChange(event) { "+
			        		   "console.log('STATE HAS changed.' + this.readyState + '/' + this.status );" +
			        		// " if (this.readyState === 4) {" +
			        		//	"callback(dataArray);" + 
			        	    " setTimeout(function() {" +
				    		" console.log('dataArray:' + dataArray);" +
				    		"callback(dataArray);" + 
				    		" }, 301*1000 );" +
			        		 "}"+
			        		
	        		   		
	        		   		"XMLHttpRequest.prototype.open = function(method, url, async, user, pass) {" +
	        		   		   " ajaxURL =  url;" +
	        		   	
	                    	"	 this.addEventListener('readystatechange', onStateChange);" +
	        				
	                    	"  open.call(this, method, url, async, user, pass);" +
	                    	" dataArray.push(url);"+
	                        " setTimeout(function() {" +
				    				" console.log('dataArray:' + dataArray);" +
				    					"callback(dataArray);" + 
				    		" }, 5*60*1000);" +
	        				"};" +
	       		"})(XMLHttpRequest.prototype.open,arguments[arguments.length - 1]);" 
	        	
	           		 );  
	         
	       return result;
	  }	
	
	
	 public static List<String> fetchAjaxResponse(WebDriver driver) throws Exception
	 {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	    	
	       // String result = "";
	    	List<String> result;
	     
	        result = (List<String>)js.executeAsyncScript("(function(callback) {" +
	        		   		" var ajaxURL;" +
	        		        " var ajaxResponse;" +
	        		        "var responses = [];" +
	        		   		"function onStateChange(event) { "+
			        		   "console.log('STATE HAS changed.' + this.readyState + '/' + this.status );" +
	        		   		  " responses.push(this.responseText);" +
			        		// " if (this.readyState === 4 && this.status == 200) {" +
			        		//	CATCH RESPONSES FOR 5 MINUTES, THEN RETURN
			        		 " setTimeout(function() {" +
					    		" console.log('responses:' + responses);" +
					    		"callback(responses);" + 
					    		" }, 301*1000 );" +
			        		 "}"+
					
	       		"})(arguments[arguments.length - 1]);" 
	        	
	           		 );  
	         
	       return result;
	  }						
	    
	
	    
	    public static List<Object> fetchtAjaxSendData(WebDriver driver) throws Exception
	    {   
	    	List<Object> result;
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	        result = (List<Object>) js.executeAsyncScript("(function(send, callback) {" +
	        		        " var payLoads = [];" +
							"XMLHttpRequest.prototype.send = function(data) {" +
								"	    console.log('see data:' + data  );"+
							    " payLoads.push(data);" +
								"	send.call(this, data);" +
								 " setTimeout(function() {" +
						    		" console.log('payLoads:' + payLoads);" +
						    		"callback(payLoads);" + 
						    		" }, 201*1000 );" +
							"};" + 
				
	        		"})(XMLHttpRequest.prototype.send,arguments[arguments.length - 1]);" 
	        );  
	  //      System.out.println("See sent ajax data:" + result);
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
		
		
	
}
