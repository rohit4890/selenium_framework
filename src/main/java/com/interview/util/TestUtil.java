package com.interview.util;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestUtil {
	
	static ExcelReader reader;
	static Properties prop;
	static FileReader fileReader;
	
	// Get data from excel sheet
	public static ArrayList<Object[]> getTestDataFromExcel(){
		
		String city = null;
		String location = null;
		String checkInDate = null;
		String checkOutDate = null;
		
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		try {
			reader = new ExcelReader(getDataFromPropertiesFile("testDataSheetLocation"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 1; i<reader.getRowCount("TestDataSheet1")+1; i++) {
			
			city = reader.getCellData("TestDataSheet1", "city", i);
			location = reader.getCellData("TestDataSheet1", "location", i);
			checkInDate = reader.getCellData("TestDataSheet1", "checkIn", i);
			checkOutDate = reader.getCellData("TestDataSheet1", "checkOut", i);	
			Object [] obj = {city, location, checkInDate, checkOutDate};
			list.add(obj);
		}
	
		return list;
	}
	
	// get data from properties file
	public static String getDataFromPropertiesFile(String key) {
		
		try {
			fileReader = new FileReader("/Users/rohitranjan/Documents/LearnSelenium/InterviewFramework/src/main/java/com/interview/config/config.properties");
			prop = new Properties();  
			prop.load(fileReader);  
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return prop.getProperty(key);		
	}
	
// Take screenshot for failed test case
	public static String takeScreenShoot(WebDriver driver, String methodName) {
		String fileName = getScreenShotName(methodName);
		String directory = System.getProperty("user.dir")+"/screeshots/";
		new File(directory).mkdirs();
		String path = directory + fileName;
		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(path));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenShotName(String methodName) {
		
		String fileName = java.time.LocalDate.now()+"-"+java.time.LocalTime.now()+"-"+methodName+".jpg";
		
		return fileName;
	}
}
