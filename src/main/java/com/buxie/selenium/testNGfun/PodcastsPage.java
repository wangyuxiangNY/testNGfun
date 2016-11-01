package com.buxie.selenium.testNGfun;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

public class PodcastsPage extends Page {

	/*
	 *  PAGE Structure
	 *  1. Filter by topics
	 *  2. categoried station tiles section
	 */
	
	//FILTER
	@FindBy(name="category")  private WebElement category;
	
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
	    
		 
		 @FindBys({
			    @FindBy(css = "table.episodes"),
			    @FindBy(css = "tr.playable-row")
			    })
			protected List<WebElement> episodes;
		    
	 
	 @FindBys({
		    @FindBy(css = "table.episodes"),
		    @FindBy(css = "tr.playable-row"),
		    @FindBy(css = "i.icon-play")
		    })
		protected List<WebElement> episodePlayIcons;
	    
	 @FindBys({
		    @FindBy(css = "table.episodes"),
		    @FindBy(css = "tr.playable-row"),
		    @FindBy(css = "i.icon-thumb-up-unfilled")
		    })
		protected List<WebElement> episodeThumbUpIcons;
	
	 
	 @FindBys({
		    @FindBy(css = "table.episodes"),
		    @FindBy(css = "tr.playable-row"),
		   // @FindBy(css ="i")
		    @FindBy(css = "i.icon-thumb-down-unfilled")
		    })
		protected List<WebElement> episodeThumbDownIcons;
	 
	 public PodcastsPage( )
    {
    	
    }
	    	
	    
	 public PodcastsPage(WebDriver driver)
	    {
	    	super(driver);
	    }
	    	
	
		public void filterStation(int optionIndex)
		{   
		    new Select(category).selectByIndex(optionIndex);
		    WaitUtility.waitForPageToLoad(driver);
		}	
		
		public String chooseStation(int index)
		{
			 logger.info("See  STATION count:" + stations.size());
		     
		      String chosenStationName = stations.get(index).getAttribute("alt");
		      Actions builder = new Actions(driver);
		      builder.moveToElement(stations.get(index), 5,5).click().build().perform();
		      WaitUtility.waitForPageToLoad(driver);
		      return chosenStationName;
		}
		
		
		public String playStation(int index)
		{
			 logger.info("See  STATION count:" + stations.size());
		     
		      String chosenStationName = stations.get(index).getAttribute("alt");
		      stations.get(index).click();
		      WaitUtility.waitForPageToLoad(driver);
		      return chosenStationName;
		}
		
		/*
		 * CHOOSE CATEGORY
		 * CHOOSE station
		 * choose episode
		 * 
		 * @Return playing-station name
		 */
		public void playEpisodeByIndex(int stationIndex, int episodeIndex)
		{  
		   logger.info("See episode index:" + episodeIndex);
	       String chosenStationName = chooseStation(stationIndex);
	       WaitUtility.waitForPageToLoad(driver);
	       //True reason is that element needs to scroll up to be visible
	       Utils.scrollElementIntoView(driver, episodes.get(episodeIndex));
	       //Sometimes it goes down a little bit too much, thus need adjustment
	       Utils.scrollScreenDown(driver, -35);
	       episodePlayIcons.get(episodeIndex).click();
	       WaitUtility.waitForPageToLoad(driver);
	       waitForPreroll();
		}
		
		//return the chosen thumbup icon
		public WebElement thumbUpEpisode(int index)
		{
			WebElement chosenIcon = episodeThumbUpIcons.get(index);
			chosenIcon.click();
			WaitUtility.waitForPageToLoad(driver);
			return chosenIcon;
			
		}
		
		public WebElement thumbDownEpisode(int index)
		{
			logger.info("SEE thumbDown icon count:" +  episodeThumbDownIcons.size() ) ;
			WebElement chosenIcon = driver.findElement(By.cssSelector("#main > div:nth-child(2) > div > section > div > table > tbody > tr:nth-child(1) > td.track-actions > button:nth-child(1) > i"));
					// episodeThumbDownIcons.get(index);
			if (chosenIcon.getAttribute("class").contains("-filled"))
			{
				chosenIcon.click();
				WaitUtility.waitForPageToLoad(driver);
			}
			chosenIcon.click();
			WaitUtility.waitForPageToLoad(driver);
			return chosenIcon;
			
		}
		
		
		public boolean isThumbUpIconFilled(WebElement icon)
		{
			return icon.getAttribute("class").equals("icon-thumb-up-filled");
		}
		
		public boolean isThumbDownIconFilled(WebElement icon)
		{
			return icon.getAttribute("class").equals("icon-thumb-down-filled");
		}
		
		public boolean isThumbUpIconFilled(int index)
		{
			return episodeThumbUpIcons.get(index).getAttribute("class").equals("icon-thumb-up-filled");
		}
		
		public boolean isThumbDownIconFilled(int index)
		{
			return episodeThumbDownIcons.get(index).getAttribute("class").equals("icon-thumb-down-filled");
		}
		
		
}
