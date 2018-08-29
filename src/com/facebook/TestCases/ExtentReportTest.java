package com.facebook.TestCases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.facebook.TestBase.TestBase;
import com.facebook.functionalities.LoginToFaceBook;
import com.facebook.library.EMailTestResults;
import com.facebook.library.ExtentReportLibrary;
import com.facebook.utilities.GeneralUtilities;
import com.facebook.listeners.CustomRetryListener;

public class ExtentReportTest extends TestBase{
	
	public static String reportName;

	/*@BeforeTest
	public void setupExtentReports() throws Exception {
		ExtRep = new ExtentReportLibrary();
		System.out.println("Inside BeforeTest");
		reportName = ExtRep.setupExtentReports("Login_F_QA");
		System.out.println("----------------" + reportName);
	}*/
	
	@BeforeTest
	public static void BeforeTest() {
		logger.info("Inside @BeforeTest in ExtentReportTest.java");
		logger.info("Browser Type is " + browser);
		if (browser.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
			driver = new FirefoxDriver();
			logger.debug("FireFox Launched !!!");
		} else if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
			System.getProperty("user.dir") + driverPath + "chromedriver.exe");
			driver = new ChromeDriver();
			logger.debug("Chrome Launched !!!");
		} else if (browser.equals("InternetExplorer")) {
			System.setProperty("webdriver.ie.driver",
			System.getProperty("user.dir") + driverPath + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
//		logger.info("Exiting @BeforeTest in ExtentReportTest.java");
//		System.out.println("URL is->"+ url);
//		driver.get(url);
	}
	
	@BeforeMethod
	public void register(Method method) {
		ExtRep.register(method);
		logger.info("@BeforeMethod URL is " + url );
		driver.get(url);
	}
	
	@AfterMethod
	public void captureStatus(ITestResult result) throws Exception {
		ExtRep.captureStatus(result, driver);
	}
	
	@AfterTest
	public void cleanUp() {
		ExtRep.cleanUp();
	}
	
	@AfterSuite
	public void afterSuite() throws Exception {
		System.out.println("Inside @AfterSuite in ExtentReportTest");
//		EMailTestResults.emailResults(reportName);

	}
	
	
	@Test (priority=1, enabled=true)
	public void ExtTestOne() throws Exception {
		logger.info("Inside testOne");
		String currentDir = System.getProperty("user.dir");
		String configFile = currentDir + "\\Configuration\\environmentDetails.properties";
		logger.info("Directory is " + " -- " + configFile);
		
		String url = GeneralUtilities.getValueOf(configFile, "URL").trim();
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
	}
	

	@Test (priority=5, enabled=true)
	public void ExtTestTwo() throws Exception {
		logger.info("Inside TestTwo...");
//		LoginToFaceBook.loginToApp(driver, logger);
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Two.");
	}
	
	
	
	@Test (priority=10, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void ExtTestThree() {
		logger.info("Inside testThree...");
		logger.info(driver.getTitle());
		Assert.assertTrue(false);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Two.");
	}
	
	
	
	@Test(priority=15, enabled=true)
	public void ExtTestFour() {
		logger.info("Inside testFour...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Four.");
	}
	
	
	
	@Test(priority=20, enabled=true)
	public void ExtTestFive() {
		logger.info("Inside testFive...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Five.");
	}
	
	
	
	
	@Test(priority=25, enabled=true)
	public void ExtTestSix() {
		logger.info("Inside testSix...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Six.");
	}
	
	
	
	
	@Test(priority=30, enabled=true)
	public void ExtTestSeven() {
		logger.info("Inside testSeven...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Seven.");
	}
	
}
