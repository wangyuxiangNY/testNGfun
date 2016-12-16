package com.buxie.browserMob;



import static org.junit.Assert.fail;

import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testCases.ArtistRadioCases;
import com.buxie.selenium.testCases.ForYouCases;
import com.buxie.selenium.testCases.LiveRadioCases;
import com.buxie.selenium.testCases.PerfectForCases;
import com.buxie.selenium.testCases.PodcastCases;
import com.buxie.selenium.testCases.ProfileCases;
import com.buxie.selenium.testNGfun.*;

//import net.lightbody.bmp.core.har.*;
import de.sstoehr.harreader.HarReader;
import de.sstoehr.harreader.model.Har;
import de.sstoehr.harreader.model.HarLog;
import de.sstoehr.harreader.model.HarEntry;
import de.sstoehr.harreader.model.HarHeader;
import de.sstoehr.harreader.model.HarPostData;
import de.sstoehr.harreader.model.HarPostDataParam;
import de.sstoehr.harreader.model.HarQueryParam;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.filters.RequestFilter;
import net.lightbody.bmp.proxy.CaptureType;
import net.lightbody.bmp.util.HttpMessageContents;
import net.lightbody.bmp.util.HttpMessageInfo;

public class RunBrowserMobTest {

	WebDriver driver;
	ArtistRadioCases artistRadioCases;
	ForYouCases forYouCases;
	PerfectForCases perfectForCases;
	ProfileCases profileCases;
	LiveRadioCases liveRadioCases;
	PodcastCases podcastCases;
	
	BrowserMobProxy proxy;
	net.lightbody.bmp.core.har.Har har;
	
	static String browser = "firefox";
   // static String browser = "chrome";
	final String URL = "http://www.iheart.com";
	
	@Rule public TestName name = new TestName();
	/*
	@BeforeClass
	public static void startUpProxy(){
		
		 proxy = new Proxy();
		 proxy.initProxy(browser);
	}
	*/
	
	@Before
    public void init()  {
		
		 proxy = new BrowserMobProxyServer();
		 proxy.start(0);
		 proxy.setHarCaptureTypes(CaptureType.REQUEST_HEADERS);
		 proxy.setHarCaptureTypes(CaptureType.REQUEST_CONTENT);
		 proxy.setHarCaptureTypes(CaptureType.RESPONSE_HEADERS);
		 proxy.setHarCaptureTypes(CaptureType.RESPONSE_CONTENT);
		 
		
		 
		 int port = proxy.getPort();
		 System.out.println("sEE PORT:" + port);
		  proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
		  proxy.addRequestFilter(new RequestFilter() {
	            @Override
	            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
	                if (messageInfo.getOriginalUrl().endsWith("api.iheart.com")) {
	                   System.out.println("got one from api.iherat:" + messageInfo.getOriginalUrl());
	                   System.out.println("see uri:" + request.getUri());
	                   System.out.println("see method:" + request.getMethod());
	                   System.out.println("see uri:" + messageInfo.getOriginalRequest().headers().get("X-Requested-With"));
                       
	                }
	                // in the request filter, you can return an HttpResponse object to "short-circuit" the request
	               return null;
	            }
	        });
		 
	        //har.writeTo(fos);
		 
		 
		driver = Utils.launchFirefoxWithProxy(proxy, URL);
		
		  String homeTraffic = "home.har";
			 
			 har = proxy.getHar();
			 
		try{
			FileOutputStream fos = new FileOutputStream(homeTraffic);
	        har.writeTo(fos);
	        fos.close();
	        
		}catch(IOException e)
		{
		   e.printStackTrace();
		}finally{
		   
		}
		forYouCases = new ForYouCases(driver);
        perfectForCases = new PerfectForCases(driver);
        profileCases = new ProfileCases(driver);
        liveRadioCases = new LiveRadioCases(driver);
        podcastCases = new PodcastCases(driver);
        artistRadioCases = new ArtistRadioCases(driver);
       
  }
	

	@Test
	 public void testHome() throws Exception
	 {  
			System.out.println("test method:" +  name.getMethodName() );
	 	    parseHar("home.har");
 	 		 
	 		//Filter request/RESPONSE
	 		// Match up request/response
	 		
	 		// Grab payload. This is what I am trying to achieve today.  
	 		proxy.stop();
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
	
	@Test
	 public void testPlay5Minutes() throws Exception
	 {  
	 	System.out.println("test method:" +  name.getMethodName() );
	 	
	 	
	 	try{
	 		
	 		artistRadioCases.playArtistRadio();
	         
	        WaitUtility.sleep(5*60*1000);
	       
	        String artistTraffic = "artist.har";
			 
			 har = proxy.getHar();
	        FileOutputStream fos = new FileOutputStream(artistTraffic);
	        har.writeTo(fos);
	        
	 		HarReader harReader = new HarReader();
	 		Har har = harReader.readFromFile(new File("artist.har"));
	 		List<HarEntry> entries = har.getLog().getEntries();
	 		for (HarEntry entry: entries)
	 		{   
	 			if (entry.getRequest().getUrl().contains("imrworld") || entry.getRequest().getUrl().contains("reporting") )
	 			{	
	 			    System.out.println("see URL:" + entry.getRequest().getUrl() + "/" + entry.getRequest().getMethod() );
	 		        if (entry.getRequest().getMethod().name().equalsIgnoreCase("POST"))
	 		        { 	
	 		        	System.out.println("See mime: " + entry.getRequest().getPostData().getMimeType()); 
	 		        	List<HarPostDataParam> params = entry.getRequest().getPostData().getParams();
	 		        	System.out.println("See post param size:" + params.size());
	 		        	for (HarPostDataParam param: params)
	 		        	{ 
	 		        		System.out.println("see contentType:" + param.getContentType() );
	 		        	    System.out.println("see param:" + param.getName() + "=" + param.getValue() );
	 		        	}
	 		        	
	 		        }else{
	 		        	List<HarQueryParam> params = entry.getRequest().getQueryString();
	 		        	for (HarQueryParam param: params)
	 		        	{ 
	 		        	    System.out.println("see query string:" + param.getName() + "=" + param.getValue() );
	 		        	}
	 		        }
	 			}
	 		}
 	 		 
	 		//Filter request/RESPONSE
	 		// Match up request/response
	 		
	 		// Grab payload. This is what I am trying to achieve today.  
	 		
	 		
	 		proxy.stop();
	 	   
	 	}catch(Exception e)
	 	{  
	 		handleException(e);
	 	}
	 	System.out.println(name.getMethodName() + " is Done.");
	 }
	 
	 @After
	 public void tearDown() throws Exception{
	   	
	   	try{
	       	//driver.quit(); 
	   	}catch(Exception e)
	   	{   
	   		System.out.println("Excepton closing driver. Why the hack so?");
	   		e.printStackTrace();
	   	}
	  
	   	
	   }
	   /*
	   @AfterClass
	   public static void shutDownProxy()
	   {
	   	 proxy.proxy.stop();
	   	 System.out.println("Proxy is shut down.");
	   }
	    */
	   private void handleException(Exception e)
	   {  
	       e.printStackTrace();
	       try{
	   	   Page.takeScreenshot(driver, name.getMethodName());
	       }catch(Exception eX)
	       {
	       	eX.printStackTrace();
	       }
	   }
	   
	   
	   /*
	    * If get request,  query string and response
2. If post, request body and response
3. Filter out non-ajax calls by checking the header field?

	    */
	  public static void parseHar(String harFile) throws Exception
	  {
			HarReader harReader = new HarReader();
	 		Har har = harReader.readFromFile(new File(harFile));
	 		List<HarEntry> entries = har.getLog().getEntries();
	 		for (HarEntry entry: entries)
	 		{   	
	 			String method = entry.getRequest().getMethod().name();
 			    System.out.println(method + " URL:" + entry.getRequest().getUrl()) ;
	 		
	 			//Is it ajax
	 			
	 			boolean isAjax = false;
	 			List<HarHeader>  headers = entry.getRequest().getHeaders();
	 			System.out.println("request Headers size: " + entry.getRequest().getHeadersSize());
	 			
 			
 				for (HarHeader header: headers)
 				{  
 					System.out.println(header.getName() + "=" + header.getValue());
 					if (header.getName().equals("X-Requested-With") && header.getValue().equals("XMLHttpRequest"))
 					{
 						isAjax = true;
 					//	break;
 					}
 				}
	 			
 				List<HarHeader>  responseHeaders = entry.getResponse().getHeaders();
 				System.out.println("responseHeaders size: " + entry.getResponse().getHeadersSize());
 			
 				for (HarHeader header: responseHeaders)
 				{  
 					System.out.println(header.getName() + "=" + header.getValue());
 					if (header.getName().equals("X-Requested-With") && header.getValue().equals("XMLHttpRequest"))
 					{
 						isAjax = true;
 					//	break;
 					}
 				}
	 			
 				
	 			if ( isAjax && method.equals("GET"))
	 			{	//For get request, we need to know URl/QueryString, response

	 			    System.out.println("URL:" + entry.getRequest().getUrl()) ;
	 				List<HarQueryParam> params = entry.getRequest().getQueryString();
	 				/*
 		        	for (HarQueryParam param: params)
 		        	{ 
 		        	    System.out.println("query string:" + param.getName() + "=" + param.getValue() );
 		        	}
 		        	*/
 		        	//What about response? 
 		        	System.out.println("Response status:" + entry.getResponse().getStatus());
 		        	System.out.println("Response :" + entry.getResponse().getContent().getText());
 		        	
	 			}else if (isAjax &&  method.equals("POST"))
	 			{	//for post request, we need to know: url, request body, response status/content
	 				
	 				
	 				System.out.println("see URL:" + entry.getRequest().getUrl());
	 				System.out.println("See mime: " + entry.getRequest().getPostData().getMimeType());
	 				List<HarPostDataParam> params = entry.getRequest().getPostData().getParams();
 		        	System.out.println("See post param size:" + params.size());
 		        	for (HarPostDataParam param: params)
 		        	{ 
 		        		System.out.println("see contentType:" + param.getContentType() );
 		        	    System.out.println("see param:" + param.getName() + "=" + param.getValue() );
 		        	}
 		        	
 		         	System.out.println("Response status:" + entry.getResponse().getStatus());
		        	System.out.println("Response :" + entry.getResponse().getContent().getText());
		        	
	 			}
	 			    
	 			
	 		}//for
 	 		 
	  }
	
	  
	  public static void captureFilteredTraffic(BrowserMobProxy proxy)
	  {
		  proxy.addRequestFilter(new RequestFilter() {
	            @Override
	            public HttpResponse filterRequest(HttpRequest request, HttpMessageContents contents, HttpMessageInfo messageInfo) {
	                if (messageInfo.getOriginalUrl().endsWith("api.iheart.com")) {
	                   System.out.println("got one from api.iherat:" + messageInfo.getOriginalUrl());
	                }

	                // in the request filter, you can return an HttpResponse object to "short-circuit" the request
	                return null;
	            }
	        });


	  }
}
