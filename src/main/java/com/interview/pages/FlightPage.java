package com.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightPage extends BasePage{

	private By leavingFromField = By.xpath("//label[contains(text(),'Leaving from')]");
	
	public FlightPage(WebDriver driver) {
		super(driver);
	}

	public WebElement getLeavingFromField() {
		return getElement(leavingFromField);
	}
	
	public String verifyFlightPage() {
		return getLeavingFromField().getText();
	}
	
}
