package com.interview.testclasses;

import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import com.interview.BaseTest.BaseTest;
import com.interview.pages.HotelPage;
import com.interview.pages.SearchPage;
import com.interview.util.Log;
import com.interview.util.TestUtil;


public class HotelPageTest extends BaseTest{

	@DataProvider
	public Iterator<Object[]> getTestData() {
		
		ArrayList<Object[]> testData = TestUtil.getTestDataFromExcel();
		return testData.iterator();
	}
	
	@Test(dataProvider="getTestData")
	public void doHotelBooking(String city, String location, String checkInDate, String checkOutDate) {
		Log.info("doHotelBooking method executed with test data");
		Log.info("city: "+city);
		Log.info("city: "+location);
		Log.info("city: "+checkInDate);
		Log.info("city: "+checkOutDate);
		SearchPage searchPage = page.getInstanceOf(HotelPage.class).searchHotel(city,location,checkInDate,checkOutDate);
		String filterText = searchPage.getFilterText();
		Assert.assertEquals(filterText, location, "Filter text didn't matched");
	}
}
