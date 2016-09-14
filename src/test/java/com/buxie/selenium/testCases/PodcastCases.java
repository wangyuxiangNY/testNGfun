package com.buxie.selenium.testCases;

import org.openqa.selenium.WebDriver;

import com.buxie.selenium.testNGfun.Navigation;
import com.buxie.selenium.testNGfun.Page;

public class PodcastCases extends TestCase{
	
	public PodcastCases(WebDriver driver)
	{
		super(driver);
	}
	
	public void thumbDown()
	{   
		Page.login();
		Navigation.gotoPodcastPage_direct();
		podcastsPage.filterStation(1);
	    podcastsPage.playEpisodeByIndex(0, 0);
	    verifyPlayer.isPlaying();
        podcastsPage.thumbDownEpisode(0);
        verifyPodcasts.verifyThumbDown(1);
	    
	}
	
	public void searchJoshInAll()
	{   
		Page.login();
		Navigation.gotoPodcastPage_direct();
		podcastsPage.search("JOSH", -1);
	    verifyCommon.verifySearch_expectingResult();
	}
	
	public void searchJoshInPodcast()
	{   
		Page.login();
		Navigation.gotoPodcastPage_direct();
		podcastsPage.search("JOSH GROBAN", "Podcasts");
	    verifyCommon.verifySearch_notExpectingResult();
	}
	
}
