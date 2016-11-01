package com.buxie.selenium.testNGfun;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

public class ProfilePage extends Page{
	
	/* Page Structure
	 *  1. Hero Content
	 *  2. My Stations
	 *  3. Listen history
	 *  4. fav songs
	 *  5. fav episodes
	 * 
	 */

	@FindBy(css="div.hero-player") private WebElement heroPlayer;
	@FindBy(css="button.large:nth-child(1)") private WebElement heroPlayerButton;
	@FindBy(css=".favorite") private WebElement favorite;
	
	//My Stations
	@FindBy(css="section.section-block:nth-child(1) > h3:nth-child(1) > a:nth-child(1)")
	   private WebElement myStationsLink;
	
	//This way is dangerous if application is heavily ajaxed. 
    @FindBys({
    	@FindBy(css="section.section-block:nth-child(1)"),
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css="li.tile")
	    })
	protected List<WebElement> stations;
	
	@FindBys({
		@FindBy(css="section.section-block:nth-child(1)"),
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css = "li.tile"),
	    @FindBy(css = "i.icon-play")
	    })
	protected List<WebElement> stationPlayIcons;
    
	
	
	@FindBys({
		@FindBy(css="section.section-block:nth-child(1)"),
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css="li.tile"),
	    @FindBy(css="div.station-text")
	    })
	protected List<WebElement> stationTexts;
	
	@FindBys({
		@FindBy(css="section.section-block:nth-child(1)"),
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css="li.tile"),
	    @FindBy(css="div.station-text"),
	    @FindBy(css="a.title")
	    })
	protected List<WebElement> stationNames;
	
	
	//Listen History
	@FindBy(css="#main > div:nth-child(2) > div:nth-child(1) > section:nth-child(2) > h3:nth-child(1) > a:nth-child(1)")
	   private WebElement listenHistoryLink;
	
	 @FindBys({
		    @FindBy(css="section.section-block:nth-child(2)"),
		    @FindBy(css = "ul.listen-history"),
		    @FindBy(css="li > div.listen-hisotry-station")
		    })
		protected List<WebElement> listenHistoryStations;
		
		@FindBys({
			@FindBy(css="section.section-block:nth-child(2)"),
		    @FindBy(css = "ul.station-tiles"),
		    @FindBy(css = "li > div.listen-hisotry-station"),
		    @FindBy(css = "button")
		    })
		protected List<WebElement> listenHistoryStationPlayIcons;
	    
	
	//Favorite Songs
	 @FindBys({
		    @FindBy(css="section.section-block:nth-child(3)"),
		    @FindBy(css = "ul.station-tiles"),
		    @FindBy(css="li.tile")
		    })
		protected List<WebElement> favoriteSongs;
		
		@FindBys({
			@FindBy(css="section.section-block:nth-child(3)"),
		    @FindBy(css = "ul.station-tiles"),
		    @FindBy(css = "li.tile"),
		    @FindBy(css = "i.icon-play")
		    })
		protected List<WebElement> favoriteSongPlayIcons;
	    	
		
    public ProfilePage(WebDriver driver)
    {
    	super(driver);
    }
    
    
    public void extendMyStaion()
    {
    	myStationsLink.click();
    	WaitUtility.waitForPageToLoad(driver);
    }
    
    
    public void extendListenHistory()
    {
    	listenHistoryLink.click();
    	WaitUtility.waitForPageToLoad(driver);
    }
    
    
   
    public String playFirstStation()
	{
	    return playStation(0);
	}
	
	

	/*
	 * @Return playing-station name
	 */
	public String playStation(int index)
	{   
		String stationName ="";
		
		logger.info("station count:" + stations.size());
		int count = 0;
	     for (WebElement station: stations)
	     { 
	    	 if (count == index)
	    	 {   //first scoll down if elment is not visible
	    		 if (index > 4)
	    		     //station.sendKeys(Keys.PAGE_DOWN);
	    			 Utils.scrollScreenDown(driver, 700);
	    		 stationName = stationNames.get(index).getText();
	    		 hoverThenClick(station, stationPlayIcons.get(index));
	    		 break;
	    	 }else
	    		 count++;
	     }
	     
	     return stationName;
	}
    
    
	
	
}
