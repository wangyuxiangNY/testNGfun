package com.buxie.ajax;

import java.lang.reflect.Field;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class MyAjaxElementLocatorFactory extends AjaxElementLocatorFactory {
	
	private WebDriver driver;
    
    int myTimeOutInSeconds;
	
	public MyAjaxElementLocatorFactory(WebDriver driver, int timeOutInSeconds) {
		super(driver, timeOutInSeconds);
		this.driver = driver;
		myTimeOutInSeconds = timeOutInSeconds;
	}

	public ElementLocator createLocator(Field field) {
		return new MyAjaxElementLocator(driver, field, myTimeOutInSeconds);
	}

}
