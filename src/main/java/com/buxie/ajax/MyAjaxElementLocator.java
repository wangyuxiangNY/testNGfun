package com.buxie.ajax;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;

import java.lang.reflect.Field;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.SystemClock;

public class MyAjaxElementLocator extends AjaxElementLocator{
	protected final int timeOutInSeconds;
	//private Clock clock;
	  private WebDriver driver;
	//private Field field;
	

	public MyAjaxElementLocator(WebDriver driver, Field field, 
			int timeOutInSeconds) {
		this(((Clock) (new SystemClock())), driver, field, timeOutInSeconds);
		 this.driver = driver;
		//this.field = field;
	}

	private MyAjaxElementLocator(Clock clock, WebDriver driver, Field field,
			int timeOutInSeconds) {
		super(clock, driver, field, timeOutInSeconds);
		this.driver = driver;
		this.timeOutInSeconds = timeOutInSeconds;
		//this.clock = clock;
	}
	
	@Override
	protected boolean isElementUsable(WebElement element) {
		try {
		   return element.isEnabled();
			
		} catch (Exception ex) {
           ex.printStackTrace();
			//refresh the page and try again
			driver.navigate().refresh();
			  
		}
		
		driver.navigate().refresh();
		return element.isEnabled();
		//return false;
	}
	
	
	
}
