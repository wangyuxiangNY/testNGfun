package com.buxie.selenium.testNGfun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class Player extends Page {
	
	@FindBy(css=".bottom-fixed") protected WebElement miniPlayer;
	//Left part
	@FindBy(css=".player-station") protected WebElement stationName;
	@FindBy(css=".player-artist") protected WebElement artist;
	
	//@FindBy(css=".player-left > div:nth-child(3) > div:nth-child(1) > button:nth-child(1)") protected WebElement more;
	// More
	@FindBy(css=".player-left > div:nth-child(3) > div:nth-child(1) > button:nth-child(1)")  protected WebElement more;
	//@FindBy(css="button>i.icon-more-horizontal") protected WebElement more; //Many mores in the page
	//@FindBy(css="ul>li>a[title='Add to Favorites']") 
	@FindBy(css=".player-left > div:nth-child(3) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)") protected WebElement  more_addToFavorites;
	@FindBy(css="ul>li>a[title='Listen History']")  protected WebElement  more_listenHistory;
	
	
	//center part

	@FindBy(css="div.player-center > div.player-controls > button:nth-child(1)") protected WebElement thumpDown;
	@FindBy(css="div.player-center > div.player-controls > button:nth-child(2)") protected WebElement thumpUp;
	@FindBy(xpath="//*[@id='player']/div[2]/div/button[1]") protected WebElement playControl;
	@FindBy(css="div.player-center > div.player-controls > button:nth-child(3)") protected WebElement scan;
	@FindBy(css=".slider-range-appearance") protected WebElement volumeBar;
	
	
	//right part
	@FindBy(css="div.player-right > button:nth-child(1)") protected WebElement myStations;
	@FindBy(css="div.player-center > button:nth-child(2)") protected WebElement listenHistory;
	@FindBy(css="div.player-center > button:nth-child(3)") protected WebElement fullScreen;
	
	
	public void play()
	{
		if (!isPlaying())
			playControl.click();
	}
	
	
	public boolean thumbUp()
	{
		if(thumpUp.isEnabled())
		{	
			thumpUp.click();
		    return true;
		}else
		{
			System.out.println("ThumbUp button is disabled.");
			return false;
		}
	}

	public boolean thumbDown()
	{
		if(thumpDown.isEnabled())
		{	
			thumpDown.click();
		    return true;
		}else
		{
			System.out.println("ThumbDown button is disabled.");
			return false;
		}
	}
	
	
	public void addToFavorite()
	{
	    Actions action = new Actions(driver);
	    action.moveToElement(more).moveToElement(more_addToFavorites).click().build().perform();
	  //  action.moveToElement(more).build().perform();
	   // action.moveToElement(more_addToFavorites).build().perform();
	    //action.moveToElement(more).moveToElement(WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector("ul>li>a[title='Add to Favorites']"), 10)).click().build().perform();
	    		
	}
	 
	
	
	public  boolean isPlaying()
	{   
		String classes = playControl.getAttribute("class");
		System.out.println("See button class:"+ classes) ;
		if (classes.contains("buffering"))
			WaitUtility.sleep(500);
		return playControl.getAttribute("class").contains("playing") || playControl.getAttribute("class").contains("loading");
	}

	public boolean isThumbUpFilled()
	{
		return  thumpUp.getAttribute("class").contains("-filled");
	}
	
	
	public boolean isThumbDownFilled()
	{
		return  thumpDown.getAttribute("class").contains("-filled");
	}
	
}
