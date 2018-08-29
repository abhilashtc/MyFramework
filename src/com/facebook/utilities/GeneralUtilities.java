package com.facebook.utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GeneralUtilities {
	public static WebDriver openWebPage(WebDriver driver, String URL){
		driver.get(URL);
		System.out.println("Opened the WebPage " + URL);
		return driver;
	}
	
	
	public static String getValueOf(String fileName, String Key) throws IOException{
		System.out.println("<*** Inside getValueOf ****>");
		System.out.println("File Name :" + fileName + ", Key : " + Key);
//		FileReader file = new FileReader("./src/Configuration/" + fileName);
		FileReader file = new FileReader(fileName);
		Properties prop = new Properties();
		prop.load(file);
		String keyValue = prop.getProperty(Key);
		file.close();
		System.out.println(keyValue);
		
		return keyValue;
	}
	
	public static String getValueOfConfigProp(String fileName, String Key) throws IOException{
		System.out.println("<*** Inside getValueOf ****>");
		System.out.println("File Name :" + fileName + ", Key : " + Key);
		String currentDir = System.getProperty("user.dir");
		String configDir = currentDir + "/Configuration";
		FileReader file = new FileReader(configDir + "/" + fileName);
//		FileReader file = new FileReader(fileName);
		Properties prop = new Properties();
		prop.load(file);
		String keyValue = prop.getProperty(Key);
		file.close();
		System.out.println(keyValue);
		return keyValue;
	}
	
	

	public static void waitForElementToLoad(WebDriver driver, String elementXPath) throws Exception {
		//String PB_ProgramName = CommonUtilities.readElement("locatorPaths.properties","PB_ProgramName");
		int ctr = 0;
		boolean elementExist;
		
		while(true) {
			ctr++;
			try {
				if (ctr > 1000) {
					System.out.println("Wait for Element Timed Out!!!");
					System.out.println("Element XPath -> " + elementXPath);
					elementExist = false;
					break;
				}
				//driver.findElement(By.xpath("elementXPath"));
				Boolean isPresent = driver.findElements(By.xpath(elementXPath)).size() > 0;
				if (isPresent) {
					System.out.println("Element Visible... " + elementXPath);
					elementExist = true;
					
					if(elementXPath.equals("//button [@class='confirm'][@tabindex='1']")) {
						driver.findElement(By.xpath(elementXPath)).click();
						System.out.println("Clicked On Continue from <Other Sessions Open> PopUp");
					}
					break;
				}
	    	}
	    	catch (ElementNotVisibleException ENF) {
	    		System.out.println(ENF);
	    	}
		}
	}
	
	
	
	public static String tokenizeTime(String timeStamp) throws Exception{
		System.out.println("Inside tokenizeTime " + timeStamp);
		String year = timeStamp.substring(0, 4);
		String month = timeStamp.substring(4, 6);
		String day = timeStamp.substring(6, 8);
		
		String tIME = timeStamp.substring(9, 15);
		String time1 = tIME.substring(0, 2) + "-" + tIME.substring(2, 4) + "-" + tIME.substring(4, 6);
		System.out.println(month + "~" + day + "~" + year + "_" + time1);
		
		return(month + "~" + day + "~" + year + "_" + time1);
	}
	
	public static void highlightElement(WebDriver driver, WebElement element){
		System.out.println("Size of WebElement is : " + element.getSize());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

}


