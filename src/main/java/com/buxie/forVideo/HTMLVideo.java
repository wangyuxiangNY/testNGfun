package com.buxie.forVideo;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.JavascriptExecutor;


public class HTMLVideo {
	  
public WebDriver driver;
  
@Test
public void testVideo() throws InterruptedException
{  
 driver = new FirefoxDriver();
 driver.manage().window().maximize();
 driver.get("http://www.w3.org/2010/05/video/mediaevents.html");
 JavascriptExecutor js = (JavascriptExecutor) driver;
   
 //play video
 js .executeScript("document.getElementById(\"video\").play()");
 Thread.sleep(5000);
   
 //pause playing video 
 js .executeScript("document.getElementById(\"video\").pause()");
   
 //check video is paused
 System.out.println(js .executeScript("document.getElementById(\"video\").paused"));
   
 js .executeScript("document.getElementById(\"video\").play()");
   
 // play video from starting
 js .executeScript("document.getElementById(\"video\").currentTime=0");
 Thread.sleep(5000);
   
 //reload video
 js .executeScript("document.getElementById(\"video\").load()");
}
}