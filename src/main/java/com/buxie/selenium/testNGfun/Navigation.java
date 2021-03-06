package com.buxie.selenium.testNGfun;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class Navigation extends Page{
   
	@FindBy(css=".header-menu-main > li:nth-child(1) > a:nth-child(1)") private static WebElement forYou;
	@FindBy(css=".header-menu-main > li:nth-child(2) > a:nth-child(1)") private static WebElement liveRadio;
	@FindBy(css=".header-menu-main > li:nth-child(3) > a:nth-child(1)") private  static WebElement artistRadio;
	@FindBy(css=".header-menu-main > li:nth-child(4) > a:nth-child(1)") private  static WebElement genres;
	@FindBy(css=".header-menu-main > li:nth-child(5) > a:nth-child(1)") private  static WebElement podcasts;
	@FindBy(css=".header-menu-main > li:nth-child(6) > a:nth-child(1)") private static  WebElement perfectFor;
//	@FindBy(css=".header-menu-main > li:nth-child(7) > a:nth-child(1)") private  static WebElement more;
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

	
	public Navigation()
	    {
	    	this(driver);
	    }
	    
	    public Navigation(WebDriver driver)
	    {
	    	super(driver);
	    }
	
	    
	public static LiveRadioPage gotoLive()
	{   
	   if (!liveRadio.isEnabled())
		  WaitUtility.sleep(1000);
		liveRadio.click();
		
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, LiveRadioPage.class);
	}
	
	public  static  ForYou gotoForYou()
	{   
		forYou.click();
		
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, ForYou.class);
	}
	
	
	public  static   PerfectForPage gotoPerfectFor()
	{   
		//WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".header-menu-main > li:nth-child(6) > a:nth-child(1)"), 60).click();//perfectFor.click();
		//WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".header-menu-main > li > a[title='Perfect For']"), 60).click();//perfectFor.click();
		try{
		  perfectFor.click();
		}catch(Exception e)
		{
			WaitUtility.sleep(1000);
			perfectFor.click();
		}
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, PerfectForPage.class);
	}
	
	
	public  static  ArtistRadioPage gotoArtistRadio()
	{   
		artistRadio.click();
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, ArtistRadioPage.class);
	}
	
	public  static  GenresPage gotoGenres()
	{   
		genres.click();
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, GenresPage.class);
	}
	
	
	public  static  PodcastsPage gotoPodcasts()
	{   
		podcasts.click();
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, PodcastsPage.class);
	}
	
	
	//After user logs in
	public  static  ProfilePage gotoProfile()
	{   
		//profile.click();
		Actions builder  = new Actions(driver);
		builder.moveToElement(accountDropDown).click(profile).build().perform();
		
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, ProfilePage.class);
	}
	
	
	public  static  MyStationsPage gotoMyStations()
	{   
		//profile.click();
		Actions builder  = new Actions(driver);
		builder.moveToElement(accountDropDown).click(myStations).build().perform();
		
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, MyStationsPage.class);
	}
	
	
	public  static  ListenHistoryPage gotoListenHistory()
	{   
		//profile.click();
		Actions builder  = new Actions(driver);
		builder.moveToElement(accountDropDown).click(listenHistory).build().perform();
		
		WaitUtility.waitForPageToLoad(driver);
        return PageFactory.initElements(driver, ListenHistoryPage.class);
	}
	
	
	
	public static void gotoLiveRadioPage_direct()
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
	
	
	public static void gotoArtistRadioPage_direct()
	{  
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/artist/" ;
		System.out.println("SEE new url:"  + newURL );
		
		int count = 0;
		do{
	    	driver.get(newURL);
	    	WaitUtility.sleep(3000);
	    	count++;
		}while (!driver.getCurrentUrl().contains("artist") && count < 3); 
		
		//WaitUtility.waitForPageToLoad(driver);
	}


	
	public static void gotoPerfectFor_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/perfect-for/" ;
		System.out.println("SEE new url:"  + newURL );
		int count = 0;
		do {
		   driver.get(newURL);
			System.out.println("See url in browser now: " + driver.getCurrentUrl());
			count++;
		}while (!driver.getCurrentUrl().contains("perfect") && count < 3);
		
		//WaitUtility.waitForPageToLoad(driver);
	}

	
	
	public static void gotoPodcastPage_direct()
	{   String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/show/" ;
		System.out.println("SEE new url:"  + newURL );
		
		
		int count = 0;
		do {
		   driver.get(newURL);
			System.out.println("See url in browser now: " + driver.getCurrentUrl());
			count++;
		}while (!driver.getCurrentUrl().contains("show") && count < 3);
		
		//WaitUtility.waitForPageToLoad(driver);
	}


	
	
	public void gotoGenrePage_direct()
	{   
		String currentURL = driver.getCurrentUrl();
		System.out.println("SEE current url:"  + currentURL);
	    String part1 = currentURL.split("//")[0];
	    String part2  = currentURL.split("//")[1].split("/")[0];
	    
	    String newURL = part1 + "//" + part2 + "/genre/" ;
		System.out.println("SEE new url:"  + newURL );
		
		driver.get(newURL);
	//	WaitUtility.waitForPageToLoad(driver);

	}
	
	
    
}