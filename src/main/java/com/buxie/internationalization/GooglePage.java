package com.buxie.internationalization;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import static org.junit.Assert.assertTrue;

import com.buxie.selenium.testNGfun.WaitUtility;


public class GooglePage {

	
	 @FindBy(id="lst-ib") protected WebElement searchField;
	 @FindBy(id="ires") protected WebElement searchResult;
		
		
		public static  WebDriver driver;
		
		 String browser ="";
		
		private  StringBuffer errors = new StringBuffer(); 
		
		
		public GooglePage(WebDriver driver)
		{  
			this.driver = driver;
			PageFactory.initElements(driver, this);
		}
		
		
		public void search(String searchString)
		{
	        searchField.clear();
	        searchField.sendKeys(searchString);
	        //hit enter
	        searchField.sendKeys(Keys.RETURN);
			WaitUtility.sleep(3000);
	        System.out.println(searchResult.getText());
	        assertTrue(searchResult.getText().contains("المزيد من الصور لـ الامارات العربية المتحدة"));

		}
		
}
