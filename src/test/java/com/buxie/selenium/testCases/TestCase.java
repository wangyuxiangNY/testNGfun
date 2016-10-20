package com.buxie.selenium.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.*;
import com.buxie.selenium.verificationLibrary.*;

public class TestCase {

	protected  ForYou forYouPage;
	protected  LiveRadioPage liveRadioPage;
	protected PodcastsPage podcastsPage;
	protected  PerfectForPage perfectForPage;
	protected  Player player;
	protected  ArtistRadioPage artistRadioPage;
	protected  SignUpPage signUpPage;
	protected  Navigation navigation;
	
	protected ProfilePage profilePage;
	
	
	protected  VerfiyForYou verifyForYou;
	protected  VerifyPerfectFor verifyPerfectFor;
	protected  VerifySignup verifySignup;
	protected  VerifyPlayer verifyPlayer;
	protected  VerifyPodcasts verifyPodcasts;
	protected  VerifyCommon verifyCommon;
	
	
	public TestCase(WebDriver driver)
	{
	   navigation = PageFactory.initElements(driver, Navigation.class);
	   forYouPage = PageFactory.initElements(driver, ForYou.class);
	   liveRadioPage = PageFactory.initElements(driver, LiveRadioPage.class);
	   artistRadioPage = PageFactory.initElements(driver, ArtistRadioPage.class);
	   perfectForPage = PageFactory.initElements(driver, PerfectForPage.class);
	   podcastsPage = PageFactory.initElements(driver, PodcastsPage.class);
	   
	   player = PageFactory.initElements(driver, Player.class);
	   signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		
	   profilePage = PageFactory.initElements(driver, ProfilePage.class);
		
	   
	   verifyForYou = new VerfiyForYou(driver);
	   verifyPerfectFor = new VerifyPerfectFor(driver);
	   verifyPlayer = new VerifyPlayer(driver);
	   verifySignup = new VerifySignup(driver);
	   verifyPodcasts = new VerifyPodcasts(driver);
	   verifyCommon  = new VerifyCommon(driver);
	}
	
	 
	public void setBrowser(String browser)
	{
		forYouPage.setBrowser(browser);
	}
	
	public StringBuffer getErrors()
	{
		return forYouPage.getErrors();
	}
}
