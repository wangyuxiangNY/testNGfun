package com.buxie.selenium.testCases;

import org.openqa.selenium.WebDriver;

import com.buxie.selenium.testNGfun.Navigation;
import com.buxie.selenium.testNGfun.Page;

public class ProfileCases extends TestCase{
	
	public ProfileCases(WebDriver driver)
	{
		super(driver);
	}
	
	public void playMyStation( int stationIndex)
	{
	   //Need to login first
		profilePage.login();
	    navigation.gotoProfile();
	    profilePage.extendMyStaion();
	    profilePage.playStation(stationIndex);
	    profilePage.waitForPreroll();
	  //  verifyPlayer.verifyPlayerIsPlaying();
	    
	}

}
