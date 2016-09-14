package com.buxie.selenium.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.buxie.selenium.testNGfun.Page;

public class ForYouCases extends TestCase{
	
	
	public ForYouCases(WebDriver driver)
	{
		super(driver);
	}
	
	public void playStation(int genreIndex, int stationIndex)
	{ 
	    forYouPage.chooseGenre(genreIndex);
	    forYouPage.getStations();
	    verifyForYou.verifyStationsAreShown();
	    
	    forYouPage.playStation(stationIndex);
	    verifyForYou.verifyStationIsPlaying();
	    
	}

	//This shall really put in a class for local radio
		public void  flowAlong()
		{
			forYouPage.playFromZ100();
			
			forYouPage.switchWindow();
		
			//Here, need to wait for signup pop
			
			forYouPage.getSignUpPage().signUp();
			
		}
		
	
}
