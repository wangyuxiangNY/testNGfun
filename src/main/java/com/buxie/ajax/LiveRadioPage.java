package com.buxie.ajax;


import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.buxie.selenium.testNGfun.WaitUtility;

public class LiveRadioPage extends AbstractPage {
	
	@FindBy(css=".country-filter [name='country']") private WebElement countrySelect;
	@FindBy(css=".market-filter [name='city']") private WebElement citySelect;
	@FindBy(css=".genre-filter [name='genre']") private WebElement genreSelect;
	
	//li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(3) > button:nth-child(2) > i:nth-child(1)
	@FindBy(css=".station-tiles > li.tile:nth-child(1) i.icon-play") private WebElement firstStation;
	
	
	public LiveRadioPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void filterStation(int country, int city, int genre)
	{
		/*
	   new Select(countrySelect).selectByIndex(country);
	   new Select(citySelect).selectByIndex(city);
	   new Select(genreSelect).selectByIndex(genre);
	   
	   firstStation.click();
	   */
		
		/*
		chooseAjaxOptionByIndex(countrySelect, country);
		chooseAjaxOptionByIndex(citySelect, city);
		chooseAjaxOptionByIndex(genreSelect, genre);
	   
	   clickOnAjaxElement(firstStation);
	   */
		/*
		WebElement _country = WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".country-filter [name='country']"), 30);
		new Select(_country).selectByIndex(country);
		
		WebElement _city = WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".market-filter [name='city']"), 30);
		new Select(_city).selectByIndex(city);
		
		WebElement _genre = WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".genre-filter [name='genre']"), 30);
		new Select(_genre).selectByIndex(genre);
		
		WebElement _firstStation = WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".station-tiles > li.tile:nth-child(1) i.icon-play"), 60);
		_firstStation.click();
		*/
		
	
		WebElement _country = WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".country-filter [name='country']"), 30);
	
		WaitUtility.sleep(50);
		
		new Select(_country).selectByIndex(country);
		
		
		
		WebElement _city = WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".market-filter [name='city']"), 30);
		WaitUtility.sleep(50);
		new Select(_city).selectByIndex(city);
		
		
		
		WebElement _genre = WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".genre-filter [name='genre']"), 30);
		WaitUtility.sleep(50);
		new Select(_genre).selectByIndex(genre);
	
		
	//	#main > div.directory-live > section > ul > li > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i
		WebElement _firstStation = WaitUtility.waitForElementToPresent(driver, By.cssSelector("#main > div.directory-live > section > ul > li > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > button > i"), 60);
		
		//WebElement _firstStation = WaitUtility.waitForElementToBeVisible(driver, By.cssSelector(".station-tiles > li.tile:nth-child(1) .station-thumb"), 60);
		_firstStation.click();
	}
   
	public void filterStation_working(int country, int city, int genre)
	{
		/*
	   new Select(countrySelect).selectByIndex(country);
	   new Select(citySelect).selectByIndex(city);
	   new Select(genreSelect).selectByIndex(genre);
	   
	   firstStation.click();
	   */
		
		
		chooseAjaxOptionByIndex(countrySelect, country);
		chooseAjaxOptionByIndex(citySelect, city);
		chooseAjaxOptionByIndex(genreSelect, genre);
	   
	   clickOnAjaxElement(firstStation);
	  
		
	
	}
   
	
	
	 private void chooseAjaxOptionByIndex(WebElement select, int index)
	 {  
		 int attemptCount = 5;
		 
		 do {
			 attemptCount--;
			 try{
		         new Select(select).selectByIndex(index);
		          return;
			 }catch(StaleElementReferenceException e)
			 {
				 System.out.println("Get StaleElementReferenceException");
			 }
		 }while (attemptCount >0)  ;
		 
		 System.out.println("Not found after 5 time retry.");
	 }
	 
	 private void clickOnAjaxElement(WebElement element)
	 {  
		 int attemptCount = 5;
		 
		 do {
			 attemptCount--;
			 try{
		         element.click();
		          return;
			 }catch(StaleElementReferenceException e)
			 {
				 System.out.println("Get StaleElementReferenceException");
			 }
		 }while (attemptCount >0)  ;
		 
		 System.out.println("Not clickable after 5 time retry.");
	 }
	 
	 
}
