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

public class LoginUI_Test extends TestBase{
	
	public static String reportName;
	public static String homePage = ""; // = "http://Asus-India:8080/UI_Proj/src/HomePage/MainFrame.html";
//	public String homePage = "http://192.168.2.104:8080/UI_Proj/src/HomePage/Menu.html";

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
		
		homePage = url;
//		logger.info("Exiting @BeforeTest in ExtentReportTest.java");
//		System.out.println("URL is->"+ url);
//		driver.get(url);
	}
	
	@BeforeMethod
	public void register(Method method) {
		ExtRep.register(method);
		logger.info("@BeforeMethod URL is " + homePage );
		driver.get(homePage);
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
		driver.switchTo().frame(0);
		System.out.println("**********Switched to Frame 1");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a1')]";
		driver.findElement(By.xpath(UI1)).click();
		
		System.out.println("**********Clicked on UI 1");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		String userName = "//input[contains(@name,'username')]";
		String password = "//input[contains(@name,'pass')]";
		
		driver.findElement(By.name("username")).sendKeys("Abhi1");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		Thread.sleep(3000);
		lastWebElement = driver.findElement(By.xpath(password));
		Assert.assertTrue(true);
	}
	
	@Test (priority=2, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testOneTwo() throws Exception {
		logger.info("Inside testOneTwo");
		driver.switchTo().frame(0);
		System.out.println("**********Switched to Frame 1");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a1')]";
		driver.findElement(By.xpath(UI1)).click();
		
		System.out.println("**********Clicked on UI 1");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		String userName = "//input[contains(@name,'username')]";
		String password = "//input[contains(@name,'pass')]";
		
		driver.findElement(By.name("username")).sendKeys("Abhi1.5");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		lastWebElement = driver.findElement(By.xpath(password));
//		Assert.assertTrue(false);
		Assert.assertTrue(true);
	}
	

	@Test (priority=5, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testTwo() throws Exception {
		logger.info("Inside testTwo");
		driver.switchTo().frame(0);
		System.out.println("**********Switched to Frame 1");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a2')]";
		driver.findElement(By.xpath(UI1)).click();
		
		System.out.println("**********Clicked on UI 2");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		String userName = "//input[contains(@name,'email')]";
		String password = "//input[contains(@name,'pass')]";
		
		driver.findElement(By.name("email")).sendKeys("Abhi2");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		lastWebElement = driver.findElement(By.xpath(password));
		Assert.assertTrue(true);
	}
	
	
	
	@Test (priority=10, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testThree() {
		logger.info("Inside testThree");
		driver.switchTo().frame(0);
		logger.info("**********Switched to Frame 1");
		logger.info(driver.getTitle());
		logger.info(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a3')]";
		driver.findElement(By.xpath(UI1)).click();
		
		logger.info("**********Clicked on UI 3");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		String userName = "//input[@name='username']";
		String password = "//input[@name='pass']";
		
		driver.findElement(By.name("username")).sendKeys("Abhi3");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		lastWebElement = driver.findElement(By.xpath(password));
		Assert.assertTrue(true);
	}
	
	
	
	@Test(priority=15, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testFour() {
		logger.info("Inside testFour");
		driver.switchTo().frame(0);
		logger.info("**********Switched to Frame 1");
		logger.info(driver.getTitle());
		logger.info(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a4')]";
		driver.findElement(By.xpath(UI1)).click();
		
		logger.info("**********Clicked on UI 4");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		String userName = "//input[@name='username']";
		String password = "//input[@name='pass']";
		
		driver.findElement(By.name("username")).sendKeys("Abhi4");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		lastWebElement = driver.findElement(By.xpath(password));
		Assert.assertTrue(true);
	}
	
	@Test(priority=16, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testFive() {
		openJIRA_Ticket = true;
		logger.info("Inside testFive");
		driver.switchTo().frame(0);
		logger.info("**********Switched to Frame 1");
		logger.info(driver.getTitle());
		logger.info(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a4')]";
		driver.findElement(By.xpath(UI1)).click();
		
		logger.info("**********Clicked on UI 4");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		String userName = "//input[@name='username']";
		String password = "//input[@name='pass']";
		
		driver.findElement(By.name("username")).sendKeys("Abhi4");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		lastWebElement = driver.findElement(By.xpath(password));
//		Assert.assertTrue(false);
		Assert.assertTrue(true);
	}
	
	@Test(priority=20, enabled=true, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testSix() {
		logger.info("Inside testSix");
		driver.switchTo().frame(0);
		logger.info("**********Switched to Frame 1");
		logger.info(driver.getTitle());
		logger.info(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a6')]";
		driver.findElement(By.xpath(UI1)).click();
		
		logger.info("**********Clicked on UI 6");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		String userName = "//input[@name='username']";
		String password = "//input[@name='pass']";
		
		driver.findElement(By.name("username")).sendKeys("Abhi4");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		lastWebElement = driver.findElement(By.xpath(password));
		Assert.assertTrue(true);
	}
	
	
	
	@Test(priority=25, enabled=false, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testSeven() throws Exception {
		logger.info("Inside testSeven...");
		driver.switchTo().frame(0);
		logger.info("**********Switched to Frame 1");
		logger.info(driver.getTitle());
		logger.info(driver.getCurrentUrl());
		String UI1 = "//a[contains(@name,'a5')]";
		driver.findElement(By.xpath(UI1)).click();
		
		logger.info("**********Clicked on UI 5");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		
		Thread.sleep(1000);
		Actions keyboardAndMouseActions = new Actions(driver);
		keyboardAndMouseActions.moveByOffset(20, 30);
		System.out.println("Moved the mouse to 20:30");
		Thread.sleep(10000);
		
		String userName = "//input[@name='username']";
		String password = "//input[@name='pass']";
		
		
		
		driver.findElement(By.name("username")).sendKeys("Abhi5");
		driver.findElement(By.name("pass")).sendKeys("Abhilash");
		lastWebElement = driver.findElement(By.xpath(password));
		Assert.assertTrue(true);
		
	}
	
	
	
/*	
	@Test(priority=25, enabled=false, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testSix() {
		logger.info("Inside testSix...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Six.");
	}
	
	
	
	
	@Test(priority=30, enabled=false, retryAnalyzer=com.facebook.listeners.CustomRetryListener.class)
	public void testSeven() {
		logger.info("Inside testSeven...");
		logger.info(driver.getTitle());
		Assert.assertTrue(true);
		ExtRep.testInfo.log(Status.INFO, "This is a Sample Test Seven.");
	}*/
	
}
