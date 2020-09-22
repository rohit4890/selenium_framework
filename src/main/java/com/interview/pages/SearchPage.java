package com.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage extends BasePage{
	
	private By filter = By.xpath("//span[@class='playback-pill-grid-item-label']");

	// Constructor
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	// Getter
	public WebElement getFilter() {
		return getElementWithWait(filter);
	}
	
	// Page level method
	// It will give filter text from the search page
	public String getFilterText() {
		
		return getFilter().getText();
	}
}
