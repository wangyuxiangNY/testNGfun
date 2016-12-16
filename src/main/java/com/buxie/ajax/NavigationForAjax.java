package com.buxie.ajax;


import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import com.buxie.selenium.testNGfun.WaitUtility;

public class NavigationForAjax extends AbstractPage{

	   
		@FindBy(css=".header-menu-main > li:nth-child(1) > a:nth-child(1)") private static WebElement forYou;
		
		@FindBy(css=".header-menu-main li > a[title='Live Radio']") private static WebElement liveRadio;
		
		@FindBy(css=".header-menu-main > li:nth-child(4) > a:nth-child(1)") private  static WebElement artistRadio;
		@FindBy(css=".header-menu-main > li:nth-child(5) > a:nth-child(1)") private  static WebElement genres;
		@FindBy(css=".header-menu-main > li:nth-child(6) > a:nth-child(1)") private  static WebElement podcasts;
		@FindBy(css=".header-menu-main > li:nth-child(7) > a:nth-child(1)") private static  WebElement perfectFor;
//		@FindBy(css=".header-menu-main > li:nth-child(7) > a:nth-child(1)") private  static WebElement more;
		@FindBy(css="#page-view-container > div > div.header > div.header-wrapper > div > div.header-left > div > div:nth-child(1) > button > i")
		   private  static WebElement more;
		    //for submenus of More
		
		@FindBy(css="div.dropdown-trigger:nth-child(4) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)") 
		   private static WebElement more_perfectFor;
		
		@FindBy(css=".icon-account") private WebElement login;
		
		//after login
		@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(1) > button:nth-child(1)")
		   private static WebElement accountDropDown;
		
		@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")
		    private static WebElement profile;
		@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(2) > a:nth-child(1)")
			private static WebElement myStations;
		@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(3) > a:nth-child(1)")
	    	private  static WebElement listenHistory;
		@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(4) > a:nth-child(1)")
			private  static WebElement favoriteSongs;
		@FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(5) > a:nth-child(1)")
			private static  WebElement friends;
	    @FindBy(css="div.dropdown-trigger:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")
			private static  WebElement settings;

		
		
		    public NavigationForAjax(WebDriver driver)
		    {
		    	super(driver);
		    }
		
		    
		public static void gotoLive()
		{  
			WaitUtility.waitForElementToBeVisible(driver, By.cssSelector(".header-menu-main li > a[title='Live Radio']"), 30).click();
		}
		
		
		public  void gotoLiveRadioPage_direct()
		{   
			String currentURL = driver.getCurrentUrl();
		
			System.out.println("SEE current url:"  + currentURL);
		    String part1 = currentURL.split("//")[0];
		    String part2  = currentURL.split("//")[1].split("/")[0];
		    
		  //  String newURL = part1 + "//" + part2 + "/live/country/US/" ;
		    String newURL = part1 + "//" + part2 + "/live/country/US" +"/" ;
			System.out.println("SEE new url:"  + newURL );
			
			 driver.get(newURL);
			
		}
		
	
}
