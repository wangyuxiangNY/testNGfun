package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.ForYou;
import com.buxie.selenium.testNGfun.Player;
import com.buxie.selenium.testNGfun.Page;
import com.buxie.selenium.testNGfun.PodcastsPage;

public class VerifyPodcasts extends PodcastsPage implements Verify{
	
	public VerifyPodcasts(WebDriver driver )
	{
		super(driver);
	}
	
	public void verifyEpisodeIsPlaying()
	{   
		softAssert.assertTrue(this.getPlayer().isPlaying(), "Episode  is not playing.");
		softAssert.assertAll();
	}

	public void verifyThumbUp(int index)
	{   
		softAssert.assertTrue(this.isThumbUpIconFilled(index), "Thumb Up failed.");
		softAssert.assertAll();
	}
	
	
	public void verifyThumbDown(int index)
	{ 
		String cssLocator = "#main > div:nth-child(2) > div > section > div > table > tbody > tr:nth-child(1) > td.track-actions > button:nth-child(" + index + ") > i";
		softAssert.assertTrue(this.isThumbDownIconFilled(driver.findElement(By.cssSelector(cssLocator))), "Thumb down failed.");
	
		softAssert.assertAll();
	}
	
}
