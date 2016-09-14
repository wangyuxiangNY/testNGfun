package com.buxie.selenium.testCases;

import org.openqa.selenium.WebDriver;

import com.buxie.selenium.testNGfun.Navigation;
import com.buxie.selenium.testNGfun.Page;

public class ArtistRadioCases extends TestCase{

	public ArtistRadioCases(WebDriver driver)
	{
		super(driver);
	}
	
	public void favorite()
	{   
		Page.login();
		Navigation.gotoArtistRadio();
	    artistRadioPage.playFirstStation();
	    artistRadioPage.waitForPreroll();
	    verifyPlayer.isPlaying();
	   
        artistRadioPage.getPlayer().addToFavorite();
	}
	
	public void filterAndPlayCustomAfterLogin()
	{
		Page.login();
		Navigation.gotoLive();
		artistRadioPage.chooseGenre(1);
	    artistRadioPage.playFirstStation();
	    artistRadioPage.waitForPreroll();
	    verifyPlayer.isPlaying();
	}
	
}
