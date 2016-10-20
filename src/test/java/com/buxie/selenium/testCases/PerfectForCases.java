package com.buxie.selenium.testCases;

import org.openqa.selenium.WebDriver;
import com.buxie.selenium.testNGfun.Navigation;

public class PerfectForCases extends TestCase {

	
	
	public PerfectForCases(WebDriver driver)
	{
		super(driver);
	}
	
	private void browsePerfect(int activityIndex, int stationIndex)
	{  
		if (activityIndex > 0)
	       perfectForPage.chooseCategory(activityIndex);
	    perfectForPage.playStation(stationIndex);
	    
	    perfectForPage.waitForPreroll();

	    verifyPlayer.isPlaying();
	  //  verifySignup.isSoftGateShown(); //signup is not shown
	    
	    
	}
	
	
	public void browsePerfectFor( )
	{  
		//Navigation.gotoPerfectFor();
		Navigation.gotoPerfectFor_direct();
		browsePerfect(-1, 0);
		//signUpPage.closeDialog();
		System.out.println("first round is done.");
		//Navigation.gotoPerfectFor_direct();
		Navigation.gotoPerfectFor();
		System.out.println("2nd round is started.");
		browsePerfect(3,0);
		
	}
	
	
}
