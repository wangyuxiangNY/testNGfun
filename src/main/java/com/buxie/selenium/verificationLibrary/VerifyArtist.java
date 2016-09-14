package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.ArtistRadioPage;

public class VerifyArtist extends ArtistRadioPage implements Verify{
	
	public void verifyStationIsPlaying()
	{   
		softAssert.assertTrue(getPlayer().isPlaying(), "Station  is not playing.");
		softAssert.assertAll();
	}
	
	public void verifyAddToFavorite()
	{
		softAssert.assertTrue(isFavorited(), "Station is not favorited.");
		softAssert.assertAll();
	}

}
