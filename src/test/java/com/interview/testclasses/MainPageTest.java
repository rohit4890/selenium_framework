package com.interview.testclasses;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.interview.BaseTest.BaseTest;
import com.interview.pages.CarsPage;
import com.interview.pages.FlightPage;
import com.interview.pages.HotelPage;
import com.interview.pages.MainPage;



public class MainPageTest extends BaseTest{

	
	@Test()
	public void verifyMainPage() {
		String title = page.getInstanceOf(MainPage.class).getMainPageTitle();
		Assert.assertEquals(title, "Travell with Expedia: Vacation Homes, Hotels, Car Rentals, Flights & More", "Title didn't matched");
	}
	
	@Test()
	public void verifyHotelBookingPage() {
		HotelPage hotelPage = page.getInstanceOf(MainPage.class).openHotelBookingPage();
		String hotelBookingPage = hotelPage.verifyHotelPage();
		Assert.assertEquals(hotelBookingPage, "Add a flight","Hotel booking Page text didn't matched");
	}
	
	@Test()
	public void verifyFlightlBookingPage() {
		FlightPage flightPage= page.getInstanceOf(MainPage.class).openFlightBookingPage();
		String flightBookingPage = flightPage.verifyFlightPage();
		Assert.assertEquals(flightBookingPage, "Leaving from","Flight booking Page text didn't matched");
	}
	
	@Test()
	public void verifyCarsBookingPage() {
		CarsPage carPage = page.getInstanceOf(MainPage.class).openCarBookingPage();
		String carBookingPage = carPage.verifyCarsPage();
		Assert.assertEquals(carBookingPage, "Rental cars","Cars booking Page text didn't matched");
	}
}
