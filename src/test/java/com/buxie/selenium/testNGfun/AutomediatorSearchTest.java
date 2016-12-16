package com.buxie.selenium.testNGfun;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class AutomediatorSearchTest {
	private WebDriver driver;
	String browser ;
	String methodName ="";
	final String URL = "http://www.automeditator.com/";
	
	@Parameters({ "browser" })
	@BeforeMethod
	public void init(Method method, String browser) {
		DriverFactory.getInstance().setBrowser(browser);
        driver = DriverFactory.getInstance().getDriver();
		driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   
	    methodName = method.getName();
        System.out.println("test method:" +  method.getName() + " started." );
    }

	 @Test
     public void testSearch() 
     {
		 WebElement searchField = driver.findElement(By.className("search-field"));
		 searchField.click();
		 searchField.sendKeys("jenkins");
		 searchField.sendKeys(Keys.ENTER);
		 assertTrue(driver.getTitle().contains("Search Results"));
     }
	 
	 @AfterMethod
    public void tearDown(ITestResult result) throws Exception
	{
	    DriverFactory.getInstance().removeDriver();
    }
}
