package com.Mercury;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Demo 
{
	WebDriver driver;
	@BeforeTest
	public void Open_Browser() 
	{
		System.setProperty("webdriver.chrome.driver", "F:\\Testing\\Class\\Driver\\chromedriver.exe");
		 driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/newtours/login.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	@BeforeMethod
	public void cookies()
	{
		Set<Cookie> cookie = driver.manage().getCookies();
		for (Cookie cookie2 : cookie) 
		{
		System.out.println("Cookies Name "+cookie2.getName());	
		}
	}
	@Test
	public void Login_Mercury()
	{
	driver.findElement(By.name("userName")).sendKeys("RRRRR");
	driver.findElement(By.name("password")).sendKeys("IIIII");
	driver.findElement(By.name("submit")).click();
	System.out.println("Login Success");
}
	@AfterMethod
	public void TakeScreenShoty() throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(src, new File("F:\\Testing\\Class\\ScreenShot\\Amze.jpeg"));
		System.out.println("ScreenShot Done");
	}
	@AfterTest
	public void logog()
	{
		String text = driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[1]/font/b")).getText();
		System.out.println(text);
	if(text.equalsIgnoreCase("Thank you for Loggin."))
	{
		System.out.println("Successfully Login");
	}
	else
	{
		System.out.println("Login Fail");
	}
	}
}