package com.buxie.selenium.testNGfun;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class XpathAndCSS extends Page{
	
	
	public XpathAndCSS(WebDriver driver)
	{
		super(driver);
	}		
	//Can we use // inside xpath?
	public void xpath()
	{
		//WebElement footer = driver.findElement(By.xpath("//p[@class='large-7 columns copyright-left']"));
		//WebElement footer = driver.findElement(By.xpath("//div[@class='genre-game-footer']"));
	//	li.genre:nth-child(11) > div:nth-child(1) > div:nth-child(3)
		///html/body/div[1]/div/div[5]/div/div/div/div/div[1]/ul/li[11]/div/div[2]
		WebElement footer = driver.findElement(By.xpath("//ul[@class='genres']/li[11]//div"));
				//By.cssSelector(".genre-game-footer > div:nth-child(2) > p:nth-child(1)"));
				//("//body//p[class='large-7 columns copyright-left']"));
		System.out.println("See footer:" + footer.getAttribute("outerHTML"));
		System.out.println("See footer:" + footer.getAttribute("innerHTML"));
		System.out.println("See footer:" + footer.getText());		
	}
	
	
	public void css()
	{
		//WebElement footer = driver.findElement(By.xpath("//p[@class='large-7 columns copyright-left']"));
		//WebElement footer = driver.findElement(By.xpath("//div[@class='genre-game-footer']"));
	//	li.genre:nth-child(11) > div:nth-child(1) > div:nth-child(3)
		///html/body/div[1]/div/div[5]/div/div/div/div/div[1]/ul/li[11]/div/div[2]
		
	//	#dialog > div > div > div > div > div.genres-wrapper.wrapper > ul > li:nth-child(3) > div > div.genre-title
		WebElement footer = driver.findElement(By.cssSelector(" ul.genres > li:nth-child(3) .genre-title"));
				//.genres>li:nth-child(11)>div>div:nth-child(2)"));
		
	//	WebElement footer = driver.findElement(By.cssSelector(".genres>li:nth-child(11)>div>div:nth-child(2)"));
				//By.cssSelector(".genre-game-footer > div:nth-child(2) > p:nth-child(1)"));
				//("//body//p[class='large-7 columns copyright-left']"));
		System.out.println("See footer:" + footer.getAttribute("outerHTML"));
		System.out.println("See footer:" + footer.getAttribute("innerHTML"));
		System.out.println("See footer:" + footer.getText());		
	}

}
