package com.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarsPage extends BasePage{
	
	private By pickUpfield = By.xpath("//span[contains(text(),'Rental cars')]");

	public CarsPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getPickUpfield() {
		return getElement(pickUpfield);
	}
	
	public String verifyCarsPage() {
		return getPickUpfield().getText();
	}

}
