package com.buxie.selenium.testNGfun;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;


public class SignUpPage extends Page{
   
	//Do we really need this? Do we really indeed need this?
	 @FindBy(css="#page-view-container > div > div.header > div.header-wrapper > div > div.header-right > div > a > i") public WebElement loginButton;
	  
	
	@FindBy(css=".dialog")  private WebElement signupDialog;
	@FindBy(css=".dialog-header") private WebElement dialogHeader;
	
	@FindBy(css=".dialog-close > div:nth-child(1) > button:nth-child(1)") public WebElement icon_close;
	 @FindBy(css="header.dialog-section > div:nth-child(2) > span:nth-child(1) > a:nth-child(3)")  public WebElement signUpLink;
	@FindBy(css="#dialog > div > div.dialog.ui-on-grey > div.wrapper > header > h2") public WebElement signupHeader;
	@FindBy(css=".dialog-title")   public WebElement signupHint; //Have an account? Log In
	          
	@FindBy(css="[name='userName'][type='text']")  public WebElement email;
	@FindBy(css="[name='password'][type='password']")  public WebElement password;
	
	@FindBy(css="[name='zipCode']")
		public WebElement zipCode;
	
	@FindBy(name="birthYear") public WebElement birthYear; 
	
	@FindBy(css= "[name='gender'][value='Female']")
				public WebElement gender_female;		
	
	@FindBy(css="#dialog > div > div > div > div > div > form > button") public WebElement signUp;
	
	public SignUpPage(WebDriver driver)
	{
		super(driver);
	}
	
	public boolean isSoftGateShown()
	{
		//Give it some time to pop up
		
		return WaitUtility.waitForElementToBeVisible(driver, By.className("dialog-header"), 30).getText().length() > 1;

		//return WaitUtility.fluentWaitIgnoringAll(driver, By.className("dialog-header"), 30).getText().length() > 1;
	}
	
	public void closeDialog()
	{
		WaitUtility.fluentWaitIgnoreAll(driver, By.cssSelector(".dialog-close > div:nth-child(1) > button:nth-child(1)"), 60).click();
		//icon_close.click();
	}
	
	public void getSignupPage()
	{
		try{
		  loginButton.click();
		}catch(Exception e)
		{
			return;
		}
		
	   WaitUtility.waitForPageToLoad(driver);
	   
	   driver.findElement(By.cssSelector("#dialog > div > div > div > header > div.type-secondary > span > a")).click();
	   WaitUtility.waitForPageToLoad(driver);
	}
	
	 public void signUp()
	 {  
		    getSignupPage();
		   String  randomEmail_firstPart = Utils.getCurrentDateInMilli();
			String _email = randomEmail_firstPart + "@mailinator.com";
			System.out.println("See randomEmail:" + _email);
		    email.sendKeys(_email);
		    password.sendKeys("iheart001");
		    zipCode.sendKeys("10013");
		    new Select(birthYear).selectByVisibleText("1980");
		    WaitUtility.sleep(2000);
		    gender_female.click();
		    signUp.click();
		    WaitUtility.waitForPageToLoad(driver);
		    System.out.println("see signed account:" + WaitUtility.waitForElementToBeClickable(driver,signedAccount).getText() );
		    signedAccount.click();
		    
		   
		    
		}   
	
}
