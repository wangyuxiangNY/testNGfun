package com.buxie.selenium.testCases;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.buxie.selenium.javascript.AllAboutJavascript;
import com.buxie.selenium.testNGfun.Navigation;
import com.buxie.selenium.testNGfun.Page;
import com.buxie.selenium.testNGfun.WaitUtility;

public class ArtistRadioCases extends TestCase{

	public ArtistRadioCases(WebDriver driver)
	{
		super(driver);
	}
	
	public void favorite()
	{   
		artistRadioPage.login();
		navigation.gotoArtistRadio();
	    artistRadioPage.playFirstStation();
	    artistRadioPage.waitForPreroll();
	    verifyPlayer.isPlaying();
	   
        artistRadioPage.getPlayer().addToFavorite();
	}
	
	public void filterAndPlayCustomAfterLogin() throws Exception
	{
		artistRadioPage.login();
		//navigation.gotoArtistRadio();
		navigation.gotoArtistRadioPage_direct();
		artistRadioPage.chooseGenre(1);
	    artistRadioPage.playFirstStation();
	    artistRadioPage.waitForPreroll();
	    verifyPlayer.isPlaying();
	    
	}
	
	public void playArtistRadio() throws Exception
	{
		artistRadioPage.login();
		navigation.gotoArtistRadio();
		//artistRadioPage.chooseGenre(1);
	    artistRadioPage.playFirstStation();
	   
	}
	
}
