package com.buxie.selenium.testNGfun;


import com.buxie.selenium.testNGfun.WaitUtility;
import com.buxie.selenium.testNGfun.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class PerfectForPage extends Page {
	
	@FindBy(css="[name='activity']") private WebElement activity;
	@FindBy(css=".station-tiles")  private WebElement stationTiles;
	

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
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css="li.tile"),
	    @FindBy(css="div.station-text")
	    })
	protected List<WebElement> stationTexts;
	
	@FindBys({
	    @FindBy(css = "ul.station-tiles"),
	    @FindBy(css="li.tile"),
	    @FindBy(css="div.station-text"),
	    @FindBy(css="a.title")
	    })
	protected List<WebElement> stationNames;
	
	
	
	
	
	public PerfectForPage()
    {
    	this(driver);
    }
    
    public PerfectForPage(WebDriver driver)
    {
    	super(driver);
    }
    
    public void chooseCategory(int index)
    {
    	new Select(WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector("[name='activity']"), 30)).selectByIndex(index);//activity).selectByIndex(index);
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
		System.out.println("station count:" + stations.size());
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
