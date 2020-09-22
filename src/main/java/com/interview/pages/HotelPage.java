package com.interview.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelPage extends BasePage{
	
	private By addFlightCheckBox = By.xpath("//label[contains(text(),'Add a flight')]");
	private By goingToField = By.cssSelector("button[aria-label^='Going to']");
	private By allLocations = By.xpath("//ul[@data-stid='location-field-destination-results']//li//strong");
	private By checkInDateField = By.cssSelector("button[aria-label^='Check-in']");
	private By allCheckInDate = By.xpath("//div[@class='uitk-calendar']//div[@class='uitk-new-date-picker-month'][1]//table//tbody//tr//td//button");
	private By checkOutDateField = By.cssSelector("button[aria-label^='Check-out']");
	private By allCheckOutDate = By.xpath("//div[@class='uitk-calendar']//div[@class='uitk-new-date-picker-month'][2]//table//tbody//tr//td//button");
	private By searchBtn = By.xpath("//button[contains(text(),'Search')]");
	private By doneBtn = By.xpath("//span[contains(text(),'Done')]");
	private By dates = By.xpath("//div[@class='uitk-calendar']//div[@class='uitk-new-date-picker-month']/h2");
	
	
	// Constructor of Hotel Page
	public HotelPage(WebDriver driver) {
		super(driver);
	}
	
	// Getter Methods
	public WebElement getAddFlightCheckBox() {
		return getElementWithWait(addFlightCheckBox);
	}
	public WebElement getGoingToField() {
		return getElement(goingToField);
	}
	public List<WebElement> getAllLocations(){
		return getElementList(allLocations);
	}
	public WebElement getCheckInDateField() {
		return getElement(checkInDateField);
	}
	public List<WebElement> getAllCheckInDate() {
		return getElementList(allCheckInDate);
	}
	public WebElement getCheckOutDateField() {
		return getElement(checkOutDateField);
	}
	
	public List<WebElement> getAllCheckOutDate() {
		return getElementList(allCheckOutDate);
	}
	
	public WebElement getDoneBtn() {
		return getElement(doneBtn);
	}
	
	public WebElement getSearchBtn() {
		return getElement(searchBtn);
	}
	
	public List<WebElement> getDates(){
		return getElementList(dates);
	}


	// Hotel Page Level Method
	public String verifyHotelPage() {
		return getAddFlightCheckBox().getText();
	}
	// Search hotel for the given location, checkInDate and checkOutDate
	public SearchPage searchHotel(String city, String location, String checkInDate, String checkOutDate) {
		enterLocation(city, location);
		enterCheckInDate(checkInDate);
		enterCheckOutDate(checkOutDate);
		getSearchBtn().click();
		return getInstanceOf(SearchPage.class);
	}
	// Enter location 
	public void enterLocation(String city, String location) {
		getGoingToField().sendKeys(city);
		for(WebElement place : getAllLocations()) {
			if(place.getText().equals(location)) {
				place.click();
				break;
			}
		}
	}
	// Enter CheckIn Date
	public void enterCheckInDate(String checkInDate) {
		getCheckInDateField().click();
		for(WebElement place : getAllCheckInDate()) {
			if(place.getAttribute("data-day").equals(checkInDate)) {
				place.click();
				break;
			}
		}
		getDoneBtn().click();
	}
	// Enter CheckOut Date
	public void enterCheckOutDate(String checkOutDate) {
		getCheckOutDateField().click();
		for(WebElement place : getAllCheckOutDate()) {
			if(place.getAttribute("data-day").equals(checkOutDate)) {
				place.click();
				break;
			}
		}
		getDoneBtn().click();
	}
}

