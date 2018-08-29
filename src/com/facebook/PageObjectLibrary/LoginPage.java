package com.facebook.PageObjectLibrary;

public interface LoginPage {
	public static String loginIFrame = "//iframe[1]";
	public static String loginTextBox  = "//input[@name='email']";
	public static String loginPassword = "//input[@name='pass']";
	public static String loginButton = "//input[@value='Log In']";
	
	public static String createPage = "//a[contains(.,'Create a Page')]";
	public static String email = "//input[contains(@name,'email')]";
	
}
