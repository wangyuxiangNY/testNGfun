package com.buxie.selenium.testNGfun;

import com.buxie.selenium.testNGfun.WaitUtility;
import com.buxie.selenium.testNGfun.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;



public class DiceFileUpload extends Page{

	//@FindBy(css="#Login_1") private WebElement login;
	private WebElement Login_1;
	private WebElement Email_1;
	private WebElement Password_1;
	private WebElement LoginBtn_1;
	
	//Edit profile
	@FindBy(css="div.btn-group-lg:nth-child(1) > button:nth-child(1)") private WebElement editProfile;
	
	
	
	@FindBy(id="resumeFileInput") private WebElement  fileInput;
	
	
	public void login()
	{
		Login_1.click();
		Email_1.sendKeys("yuxiang.wang.ny@gmail.com");
		Password_1.sendKeys("flyHigh123");
		LoginBtn_1.click();
		Utils.waitForPageToLoad(driver);
	}
	
	
	public void uploadResume()
	{
		//editProfile.click();
		Utils.clickWhenReady(driver, editProfile, 15);
		
		Utils.waitForPageToLoad(driver);
		By by = By.id("resumeFileInput");
		Utils.uploadFile(driver, by, "/Users/yuxiangwang/Desktop/new_new_new_yuxiang_wang.rtf");
		
	}
	
}
