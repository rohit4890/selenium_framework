package com.interview.BaseTest;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.interview.pages.BasePage;
import com.interview.pages.Page;
import com.interview.util.TestUtil;

public class BaseTest {
	
	public WebDriver driver;
	public Page page;

//	@BeforeClass
//	public void setUp() {
//		
//		
//	}

	@BeforeMethod
	public void setUp() {
		DOMConfigurator.configure("/Users/rohitranjan/Documents/LearnSelenium/InterviewFramework/src/main/resources/log4j.xml");
		System.setProperty(TestUtil.getDataFromPropertiesFile("chromeDriver"), TestUtil.getDataFromPropertiesFile("driverLocation"));
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.get(TestUtil.getDataFromPropertiesFile("appURL"));
		//driver.manage().window().fullscreen();
		page = new BasePage(driver);
	}
	
	@AfterMethod
	public void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
	
//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//	}	
//	

}
