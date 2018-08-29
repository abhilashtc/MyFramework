package com.facebook.functionalities;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.facebook.PageObjectLibrary.LoginPage;
import com.facebook.utilities.GeneralUtilities;

public class LoginToFaceBook implements LoginPage {
	
	public static void loginToApp(WebDriver driver, Logger logger) throws IOException {
		String currentDir = System.getProperty("user.dir");
		String configFile = currentDir + "\\Configuration\\environmentDetails.properties";		
		String loginID = GeneralUtilities.getValueOf(configFile, "ValidUserName").trim();
		String password = GeneralUtilities.getValueOf(configFile, "ValidPassword").trim();
		
		System.out.println("Page Title is -> " + driver.getTitle());
		driver.switchTo().frame(driver.findElement(By.xpath(loginIFrame)));
		logger.info("Switched to iFrame on the login page...");
		
		driver.findElement(By.xpath(loginTextBox)).sendKeys(loginID);
		logger.info("Entered the User Name as " + loginID);
		
		driver.findElement(By.xpath(loginPassword)).sendKeys(password);
		logger.info("Entered the Password for @" + loginID);
		
		driver.findElement(By.xpath(loginButton)).click();
		logger.info("Clicked on SignIn Button");
		
		logger.info("Logged into the App...");
		System.out.println("=======END===============TestBase.java loginToApp()============================");
	}

}
