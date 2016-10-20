package com.buxie.selenium.testNGfun;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;


public class ArtistRadioPage extends Page {
	
	//Hero section
    @FindBy(css="button.favorite>i") private WebElement favorite;
    		

	//select genre
	@FindBy(name="genre") private WebElement genre;
	
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
    
    public ArtistRadioPage()
    {
    }
    
    public ArtistRadioPage(WebDriver driver)
    {
    	super(driver);
    }
    
   public void playRandomStation()
   {
	   //Generate random number, and play that station
   }
   
	
	
	public void chooseGenre(int index)
	{   
	    new Select(genre).selectByIndex(index);
	    WaitUtility.waitForPageToLoad(driver);
	}	
	
	
	public String playFirstStation()
	{
	    return playStationByIndex(0);
	}
	
	/*
	 * @Return playing-station name
	 */
	public String playStationByIndex(int index)
	{
	    System.out.println("See  STATION count:" + stations.size());
     
       String chosenStationName = stations.get(index).getAttribute("alt");
       stations.get(index).click();
       return  chosenStationName;
       
	}
	
	public void favorite()
	{
	   if (favorite.getAttribute("class").contains("-filled"))
	   {	
		   favorite.click();
		   WaitUtility.waitForPageToLoad(driver);
	   }
	   	
	   favorite.click();
	   WaitUtility.waitForPageToLoad(driver);
	}
	
	public void unfavorite()
	{
	   if (favorite.getAttribute("class").contains("-unfilled"))
	   {	
		   favorite.click();
		   WaitUtility.waitForPageToLoad(driver);
	   }
	   	
	   favorite.click();
	   WaitUtility.waitForPageToLoad(driver);
	}
	
	public boolean isFavorited()
	{
		return favorite.getAttribute("class").contains("-filled");
	}
	
	
	
}
