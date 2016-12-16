package com.buxie.ajax;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public abstract class AbstractPage {
     @FindBy(css="form.header-search  input") protected WebElement searchField;
	
	public static  WebDriver driver;
	
	 String browser ="";
	 final String USER_NAME ="iheartrocks888@gmail.com";
	 final  String PASSWORD ="iheart001";
	 final  String FACEBOOK_USER_NAME = USER_NAME;
	 final String GOOGLE_USER_NAME = USER_NAME;
	
	
	
	private  StringBuffer errors = new StringBuffer(); 
	
	
	public AbstractPage(WebDriver driver)
	{  
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void search(String searchString)
	{
        searchField.clear();
        searchField.sendKeys(searchString);
		

	}
	
}
