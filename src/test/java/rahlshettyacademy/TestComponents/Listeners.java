package rahlshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.gerReportObject();
	
	// ThreadLocal is used for handling concurrent issue when threads are running parallely.
	// Each object creation is have it own thread, so it won't interrupt the other override test.
	
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal(); 
	
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); // unique thread id
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		// test.fail(result.getThrowable());
		
		extentTest.get().fail(result.getThrowable());
		
		// Taking a screenshot, Attached to a report
		
		
		try {
			
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {
			filePath = getScreenShot(result.getMethod().getMethodName(),driver);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
	}
	
	@Override
	public void onTestSkipped(ITestResult result) 
	{
		// TO DO
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		// TO DO
	}
	
	
	@Override
	public void onStart(ITestContext result) 
	{
		// TO DO
	}
	
	
	@Override
	public void onFinish(ITestContext result) 
	{
		extent.flush();
	}
	

}
