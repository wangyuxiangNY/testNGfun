package com.buxie.selenium.testNGfun;

import com.buxie.selenium.testNGfun.WaitUtility;
import com.buxie.selenium.testNGfun.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;



public  class LiveRadioPage extends Page{
	
	
	//Filter station
	@FindBy(css=".country-filter > div:nth-child(1) > select:nth-child(2)") private WebElement country;
	@FindBy(css=".market-filter > div:nth-child(1) > select:nth-child(2)") private WebElement city;
	@FindBy(css="div.form-group:nth-child(3) > div:nth-child(1) > select:nth-child(2)") private WebElement genres_dropDown;
	@FindBy(css=".header-menu-main > li:nth-child(4) > a:nth-child(1)")  private WebElement genres;
	
    
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
    
    public LiveRadioPage()
    {
    }
    
    public LiveRadioPage(WebDriver driver)
    {
    //	super(driver);
    	this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
    }
    
   public void playRandomStation()
   {
	   //Generate random number, and play that station
   }
   
	
	
	public void filterStation_good()
	{   
	    new Select(country).selectByIndex(1);
	    WaitUtility.waitForPageToLoad(driver);
	//	new Select(driver.findElement(By.name("city"))).selectByIndex(1);
		WaitUtility.selectDropDown(driver, By.name("city"), 1);
		WaitUtility.waitForPageToLoad(driver);
		  
	}	
	
	
	public void filterStation()
	{   
	    new Select(country).selectByIndex(1);
	    new Select(city).selectByIndex(1);
		//WaitUtility.selectDropDown(driver, By.name("city"), 1);
	}	
	
	
	public void filterStation(int countryIndex, int cityIndex)
	{   
	    new Select(country).selectByIndex(countryIndex);
	   
	   // new Select(WaitUtility.waitForElementToPresent(driver, By.name("city"), 30)).selectByIndex(cityIndex);
	   // new Select(city).selectByIndex(cityIndex);
		WaitUtility.selectDropDown(driver, By.name("city"), cityIndex);
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
	  // List<WebElement>  stations = stations.findElements(By.className("station-thumb"));//By.className("icon-play"));
       System.out.println("See  STATION count:" + stations.size());
       String chosenStationName = WaitUtility.waitForElementToBeVisible(driver, stations.get(index), 30).getAttribute("alt");
      // String chosenStationName = stations.get(index).getAttribute("alt");
       stations.get(index).click();
       
       //handlePreroll();
       pausePreroll();
       resumePreroll();
      
       return  chosenStationName;
       
	}
	
	
	public void addToFavorite()
	{
		WebElement firstMask = driver.findElement(By.cssSelector("#main > div.directory-live > section > ul > li:nth-child(1) > div > div.station-thumb-wrapper.ui-on-dark > a > div.hover > div"));
		WebElement buttonCircle = driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > button:nth-child(1)"));
		WebElement favorite = driver.findElement(By.cssSelector("li.tile:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > nav:nth-child(2) > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)"));
		
	    Actions action = new Actions(driver);
	    action.moveToElement(firstMask).moveToElement(buttonCircle).moveToElement(favorite).click().build().perform();
	    
				
	}
	
	
	
}