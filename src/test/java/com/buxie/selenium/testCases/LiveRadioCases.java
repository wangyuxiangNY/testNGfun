package com.buxie.selenium.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.Navigation;
import com.buxie.selenium.testNGfun.Page;
import com.buxie.selenium.testNGfun.Player;

public class LiveRadioCases extends TestCase {

	public LiveRadioCases(WebDriver driver)
	{
		super(driver);
	}
	
	public void thumbUp()
	{   
		liveRadioPage.login();
		navigation.gotoLive();
	    liveRadioPage.playFirstStation();
	    verifyPlayer.isPlaying();
	    liveRadioPage.waitForPreroll();
        if( liveRadioPage.getPlayer().thumbUp())
          verifyPlayer.isThumbUpFilled();
	    
	}
	
	public void playLive()
	{   
		navigation.gotoLive();
	    liveRadioPage.playFirstStation();
	}
	
	
	public void playFilteredLive()
	{   
		navigation.gotoLive();
		liveRadioPage.filterStation(1, 3);
	    liveRadioPage.playFirstStation();
	}
}
