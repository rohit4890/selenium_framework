package com.interview.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends BasePage{
	
	private By hotels = By.xpath("//span[contains(text(),'Stays')]");
	private By flights = By.xpath("//span[contains(text(),'Flights')]");
	private By cars = By.xpath("//span[contains(text(),'Cars')]");
	

	// Constructor of Main Page
	public MainPage(WebDriver driver) {
		super(driver);
	}

	// Getter Methods of Main Page
	public WebElement getHotels() {
		return getElement(hotels);
	}

	public WebElement getFlights() {
		return getElement(flights);
	}

	public WebElement getCars() {
		return getElement(cars);
	}

	
	// Page level methods
	public String getMainPageTitle() {
		return getPageTitle();
	}

	public HotelPage openHotelBookingPage() {
		getHotels().click();
		return getInstanceOf(HotelPage.class);
	}
	
	public FlightPage openFlightBookingPage() {
		getFlights().click();
		return getInstanceOf(FlightPage.class);
	}
	
	public CarsPage openCarBookingPage() {
		getCars().click();
		return getInstanceOf(CarsPage.class);
	}
}
