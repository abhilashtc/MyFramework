package com.facebook.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.facebook.library.EMailTestResults;
import com.facebook.library.ExtentReportLibrary;
import com.facebook.utilities.GeneralUtilities;


public class TestBase {
//**WebDriver, Properties File, Log4J, ExtentReports etc will be initialized here

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger logger;
	public static WebDriverWait wait;
	public static ExtentReportLibrary ExtRep;
	public static WebElement lastWebElement;
	
	public static String browser;
	public static String url;
	public static String reportName;
	public static String driverPath;
	
	public static boolean openJIRA_Ticket = false;
	public static String JIRA_Ticket = "";
	public static boolean jiraFlag = true;
	
/*	
	public static ExtentReportLibrary reports;
	public static ExtentTest test;
	public static ExtentTest testInfo;
	public static ExtentHtmlReporter htmlReporter;
*/
	
	//This method will configure log4j
	public void setupLog4j() {
		String currentDir = System.getProperty("user.dir");
		String configFile = currentDir + "\\Configuration\\Log4j.properties\\";
		logger = Logger.getLogger("LogDemo");
		PropertyConfigurator.configure(configFile);
		System.out.println("Log4J Configured successfully");
		logger.info("Log4J Configured successfully");
	}
	
	
	
	
	//This method will configure ExtentReports
	public void setupExtentReports() throws Exception {
		ExtRep = new ExtentReportLibrary();
		System.out.println("Inside BeforeTest");
		reportName = ExtRep.setupExtentReports("Login_F_QA");
		System.out.println("----------------" + reportName);
	}
	
	
	
	
	//This method will Select the Browser and open the URL from the Configuration file
	@BeforeSuite
	public void setUp() throws Exception {
		System.out.println("=======START===============TestBase.java  @BeforeSuite setUP()============================");
		setupLog4j();
		setupExtentReports();
		logger.info("Inside @BeforeSuite in TestBase.java");
		
		logger.info(">>> START >>> TestBase.java  ~~~ @BeforeSuite setUP()");
		String currentDir = System.getProperty("user.dir");
		String configFile = currentDir + "\\Configuration\\environmentDetails.properties\\";
		driverPath = currentDir + "\\resources\\executables\\";
		System.out.println("Directory is " + currentDir + " -- " + configFile);
		
		
		FileReader propertyFile = null;
		try {
			propertyFile = new FileReader(configFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Properties configProperty = new Properties();
		try {
			configProperty.load(propertyFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			browser = configProperty.getProperty("Browser");
		}
		catch (NullPointerException e) {
			System.out.println("NullPointerException " + e);
			logger.error(e);
		}
		System.out.println("Browser ->" + browser + ".");
		if (browser == null) {
			browser = "FireFox";
		}
		
		url = configProperty.getProperty("URL");
		logger.info(("Browser ->" + browser));
		logger.info(("URL ->" + url));
		
		initializeJiraTicketFile();
		
		System.out.println("=======END===============TestBase.java  @BeforeSuite setUP()============================");
		logger.info("Exiting @BeforeSuite in TestBase.java");
		
	}
	
	
	public static void initializeJiraTicketFile () throws Exception {
		String currentDir = System.getProperty("user.dir");
		String configDir = currentDir + "/TestReport";
		String archivedDir = configDir + "/Archived";
		String jiraFile = configDir + "//JiraTickets.txt";
		
		Path filePath = Paths.get(configDir);
		if (!Files.exists(filePath)) {
		    Files.createDirectory(filePath);
		    
		    filePath = Paths.get(archivedDir);
		    Files.createDirectory(filePath);

		    System.out.println("---> " + configDir + " has been created successfully with sub folders.");
		}
		else if (!Files.exists(Paths.get(archivedDir))) {
			filePath = Paths.get(archivedDir);
		    Files.createDirectory(filePath);
		    System.out.println("---> " + configDir + " has been created successfully.");
		}
		
		Path source = Paths.get(jiraFile);
		if (Files.exists(source)) {
			System.out.println("Inside File Exist, going to move it to Archived folder.");
			Path dest = Paths.get(archivedDir + "//JiraTickets.txt");
			Files.move(source, dest, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("---> " + jiraFile + " has been moved successfully to Archived folder.");
		}
			
		filePath = Paths.get(jiraFile);
		Files.createFile(filePath);
		System.out.println("---> " + jiraFile + " has been created successfully.");
	}


	@AfterSuite
	public void tearDown() {
		logger.info("Inside @tearDown in TestBase.java");
		System.out.println("=======START===============TestBase.java  @AfterSuite tearDown()============================");
		System.out.println("Inside @AfterSuie, Emailed Report " + reportName);
		try {
			EMailTestResults.emailResults(reportName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Test Results has been emailed to the intended recipients...");
		
		if (driver != null) {
			logger.info("Quiting WebDriver!!!");
			driver.quit();
		}
		logger.debug("Test execution completed !!!");
		logger.info("Exiting @tearDown in TestBase.java");
	}
}
	
