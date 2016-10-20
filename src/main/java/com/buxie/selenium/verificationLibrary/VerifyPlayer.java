package com.buxie.selenium.verificationLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.Player;

public class VerifyPlayer extends Player implements Verify {
	
	private Player player;
	
	public VerifyPlayer(WebDriver driver)
	{
		super(driver);
		player = PageFactory.initElements(driver, Player.class);
	}
	
	
	public void verifyPlayerIsPlaying()
	{
		if (softAssert == null)
			System.out.println("softAssert is null...");
		
		if (player == null)
			System.out.println("player is null...");
		softAssert.assertTrue(player.isPlaying(), "Player is not playing.");
	}
	
	public void verifyThumbUpFilled()
	{
		softAssert.assertTrue(player.isThumbUpFilled(), "ThumbUp is not working.");
	}
	
	
	public void verifyThumbDownFilled()
	{
		softAssert.assertTrue(player.isThumbDownFilled(), "ThumbdD is not working.");
	}

}
