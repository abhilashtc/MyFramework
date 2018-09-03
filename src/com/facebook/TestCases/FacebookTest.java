package com.facebook.TestCases;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
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
import com.facebook.PageObjectLibrary.HomePage;
import com.facebook.PageObjectLibrary.LoginPage;
import com.facebook.TestBase.TestBase;
import com.facebook.functionalities.LoginToFaceBook;
import com.facebook.library.EMailTestResults;
import com.facebook.library.ExtentReportLibrary;
import com.facebook.utilities.GeneralUtilities;
import com.facebook.listeners.CustomRetryListener;

public class FacebookTest extends TestBase{
	
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
		openJIRA_Ticket = false;
		JIRA_Ticket = "";
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
	
	
	@Test (priority=1, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testOne() throws Exception {
		logger.info("Inside testOne");
		String currentDir = System.getProperty("user.dir");
		String configFile = currentDir + "\\Configuration\\environmentDetails.properties";
		logger.info("Directory is " + " -- " + configFile);
		
		String url = GeneralUtilities.getValueOf(configFile, "URL").trim();
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
	}
	

	@Test (priority=5, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testTwo() throws Exception {
		logger.info("Inside TestTwo...");
//		LoginToFaceBook.loginToApp(driver, logger);
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Two.");
	}
	
	
	
	@Test (priority=10, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testThree() {
		openJIRA_Ticket = true;
		logger.info("Inside testThree...");
		logger.info(driver.getTitle());
		lastWebElement = driver.findElement(By.xpath(LoginPage.loginPassword));
		Assert.assertTrue(false);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Two.");
	}
	
	
	
	@Test(priority=15, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testFour() {
		logger.info("Inside testFour...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Four.");
	}
	
	
	
	@Test(priority=20, enabled=false, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testFive() throws Exception {
		logger.info("Inside testFive...");
		logger.info(driver.getTitle());
		/*
		String loginID = GeneralUtilities.getValueOfConfigProp("environmentDetails.properties", "ValidUserName");
		String password = GeneralUtilities.getValueOfConfigProp("environmentDetails.properties", "ValidPassword");
		
		//** Entering valid credentials
		driver.findElement(By.xpath(LoginPage.loginTextBox)).sendKeys(loginID);
		driver.findElement(By.xpath(LoginPage.loginPassword)).sendKeys(password);
		driver.findElement(By.xpath(LoginPage.loginButton)).click();
		
		Thread.sleep(10000);
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.sendKeys(Keys.ESCAPE).perform();
		*/
		
		/* Commenting out Temporarily
		driver.findElement(By.xpath(LoginPage.createPage)).click();
		GeneralUtilities.waitForElementToLoad(driver, LoginPage.email);
		lastWebElement = driver.findElement(By.xpath(LoginPage.email));
		Assert.assertTrue(false);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Five.");
		*/
		
		driver.get("http://www.ndtv.com/");
		/*
		String series = "(//span[@class='link-text'][contains(.,'Series')])[1]";
		GeneralUtilities.waitForElementToLoad(driver, series);
		driver.findElement(By.xpath(series)).click();
		
		String webElement = "//span[@class='link-text'][contains(.,'England v India')]";
		GeneralUtilities.waitForElementToLoad(driver, webElement);
		lastWebElement = driver.findElement(By.xpath(webElement));*/
		Assert.assertTrue(false);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Five.");
		
	}
	
	
	
	
	@Test(priority=25, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testSix() {
		logger.info("Inside testSix...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Six.");
	}
	
	
	
	
	@Test(priority=30, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testSeven() {
		logger.info("Inside testSeven...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Seven.");
	}
	
}
