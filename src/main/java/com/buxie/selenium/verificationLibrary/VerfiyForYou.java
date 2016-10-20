package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.ForYou;
import com.buxie.selenium.testNGfun.Player;

public class VerfiyForYou extends ForYou implements Verify{
	
	
	public VerfiyForYou(WebDriver driver)
	{
		super(driver);
	}
	public void verifyStationsAreShown()
	{
		softAssert.assertTrue(sectionHeader.isDisplayed(), "Section Header is not shown.");
		softAssert.assertTrue(stations.size() > 0, "No station is displayed.");
	}
	
	public void verifyStationIsPlaying()
	{   
		
		boolean result = PageFactory.initElements(driver, Player.class).isPlaying();
		System.out.println("iS playing?:" + result);
		
		softAssert.assertTrue(PageFactory.initElements(driver, Player.class).isPlaying(), "Station  is not playing.");
		softAssert.assertAll();

	}
}
