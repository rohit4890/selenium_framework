package com.interview.listeners;

import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.interview.BaseTest.BaseTest;
import com.interview.util.ExtentManager;
import com.interview.util.Log;
import com.interview.util.TestUtil;

public class TestListeners implements ITestListener{
	
	public static ExtentReports extent = ExtentManager.createInstance();
	
//	We are writing threadLacal because we going make this class a thread safe so that if multiple classes running in parallel and they 
//	are trying to generate report in parallel then this class should be able to handle it.
	private static ThreadLocal<ExtentTest> extentTest =  new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		
		Log.startTestCase(result.getMethod().getMethodName());
		ExtentTest test = extent.createTest(result.getTestClass().getName()+" :: "+result.getMethod().getMethodName());
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		Log.info("Method: "+result.getMethod().getMethodName()+" is passed");
		Log.endTestCase(result.getMethod().getMethodName());
		String logText = "<b>Test Method::  "+ result.getMethod().getMethodName()+ " successful.</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		
	}

	public void onTestFailure(ITestResult result) {
		
		Log.info("Method: "+result.getMethod().getMethodName()+" is failed");
		Log.endTestCase(result.getMethod().getMethodName());
		String methodName = result.getMethod().getMethodName();
		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		
		extentTest.get().fail("<details><summary><b><font color=red>"+ "Exception Occured, click to see details:"+"</font></b></summary>"+
		exceptionMessage.replaceAll(",","<br>")+"</details> \n");
		
		WebDriver driver = ((BaseTest)result.getInstance()).driver;
		String path = TestUtil.takeScreenShoot(driver, methodName);
		
		try {
			extentTest.get().fail("<b><font color=red>"+"screenshot of failure"+"</font></b>", 
					MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}catch(IOException e) {
			extentTest.get().fail("Test failed, cannot attach screenshot");
		}
		
		String longText = "<b>Test Method::  "+ methodName + " failed.</b>";
		Markup m = MarkupHelper.createLabel(longText, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);
	}

	public void onTestSkipped(ITestResult result) {
		Log.info("Method: "+result.getMethod().getMethodName()+" is skipped");	
		String logText = "<b>Test Method::  "+ result.getMethod().getMethodName() +" skipped</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		
		if (extent != null) {
			extent.flush();
		}
	}
}
