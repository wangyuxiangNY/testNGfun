package com.buxie.selenium.testNGfun;




import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;


public class ForYou  extends Page{

	/*
	 *  findAll: Find all that matches any of the conditions 
	 *  FindBys: Find all that matches all of the conditions
	 */
	 @FindBys({
		    @FindBy(css = "ul.genres"),
		    @FindBy(css = "li.genre")
		    })
		    private List<WebElement> genres;
	
	@FindBy(css=".genre-game-footer > button:nth-child(1)")
	    private WebElement getStations;
	
	@FindBy(css=".section-header") protected WebElement sectionHeader;
	
	@FindBys({
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css="li.tile")
	    })
	protected List<WebElement> stations;
	
	@FindBys({
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css = "li.tile"),
	    @FindBy(css = "i.icon-play")
	    })
	protected List<WebElement> stationPlayIcons;
	
	//hover over the station, get 'more' out, then hover over to 'not for me', then click
	
	@FindBys({
	    @FindBy(css = "div.tile-dropdown"),
	    @FindBy(css = "div>button"),
	    @FindBy(css = "i.icon-more-horizontal")
	    })
	protected List<WebElement> horizontalMoreIcons;
	
	@FindBys({
	    @FindBy(css = "div.dropdown-content"),
	    @FindBy(css = "nav.dropdown > ul >li"),
	    @FindBy(css = "a[title='Add to Favorites']")
	    })
	protected List<WebElement> addToFavoriteIcons;
	
	//for Z100.com/Popular User Flow
		@FindBy(css=".ihr-icon-listen-live")
				private WebElement listenLive;
	
		
	 public ForYou(WebDriver driver)
	    {
	    	super(driver);
	    }
	    
		
		
	public void chooseGenre(int index)
	{    
		//genres = driver.findElement(By.cssSelector("ul.genres")).findElements(By.cssSelector("li.genre"));
		System.out.println("genre count:" + genres.size());
	    
		 int count = 0;
	     for (WebElement genre: genres)
	     {
	    	 if (count == index)
	    	 {   System.out.println("Chosen genre: " + genre.getText());
	    		 genre.click();
	    		 break;
	    	 }else
	    		 count++;
	     }
	}
	
	public void getStations()
	{
		getStations.click();
		WaitUtility.waitForPageToLoad(driver);
	}
	
	
	public void playStation(int index)
	{   
		System.out.println("station count:" + stations.size());
		int count = 0;
	     for (WebElement station: stations)
	     { 
	    	 if (count == index)
	    	 {   //first scoll down if elment is not visible
	    		 if (index > 4)
	    			 Utils.scrollScreenDown(driver, 700);
	    		 	 By  by = By.cssSelector("ul.station-tiles:nth-child(2) > li:nth-child(" + index  + ") > div:nth-child(1) > div:nth-child(1) > a:nth-child(1) > div:nth-child(3) > button:nth-child(2)" );
	    		 hoverThenClick(station, by, 5);
	    		 break;
	    	 }else
	    		 count++;
	     }
	}
	
	
	public void notForMe(int stationIndex)
	{  
		Actions action = new Actions(driver);
		Utils.scrollElementIntoView(driver, stations.get(0));
		Utils.scrollScreenDown(driver, -50);
		action.moveToElement(stations.get(0)).build().perform();
		action.moveToElement(horizontalMoreIcons.get(0)).build().perform();
		WebElement notForMe = driver.findElement(By.cssSelector("#main > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > div > div.dropdown-content > nav > ul > li:nth-child(2) > a"));
				
		action.moveToElement(notForMe).click().build().perform();
		
	}
	
	public void addToFavorite(int stationIndex)
	{  
		Actions action = new Actions(driver);
		Utils.scrollElementIntoView(driver, stations.get(0));
		Utils.scrollScreenDown(driver, -50);
		action.moveToElement(stations.get(0)).build().perform();
		action.moveToElement(horizontalMoreIcons.get(0)).build().perform();
	//	action.moveToElement(addToFavoriteIcons.get(0)).click().build().perform();
		WebElement fav = driver.findElement(By.cssSelector("ul.station-tiles:nth-child(2) > li:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
		
		action.moveToElement(fav).click().build().perform();
		
	}
	
	public void playFromZ100()
	{
		driver.get("http://www.z100.com");
		WaitUtility.waitForPageToLoad(driver);
		listenLive.click();
		//WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector("div.play-button > i.ihr-icon-play"), 10).click();
	}
	
}
