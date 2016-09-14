package com.buxie.selenium.testCases;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.buxie.selenium.testNGfun.*;


public class UploadDownloadCases {

    DiceFileUpload uploadPage;
	
	public UploadDownloadCases(WebDriver driver)
	{
		uploadPage = PageFactory.initElements(driver, DiceFileUpload.class);
	}
	
    public void uploadResume()
    {
    	uploadPage.login();
    	uploadPage.uploadResume();
    }

	
}
