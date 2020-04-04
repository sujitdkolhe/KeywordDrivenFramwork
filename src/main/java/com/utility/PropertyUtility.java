package com.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.keyworddriven.Constants;

/**
 * This method will open FacebookPath.Propertie and return value associate with
 * key
 * 
 * @param key
 * @return value in form of{@code String}
 */
public class PropertyUtility {

	public static String getProperty(String key) {
		String value = null;
		try {
			Constants.fis = new FileInputStream("Input/amazonhome_pagepath.properties");
			Properties p = new Properties();
			p.load(Constants.fis);
			value = p.getProperty(key);
		} catch (IOException e) {
			System.out.println("Unable to load Properties File");
			e.printStackTrace();
		}
		return value;
	}
	public static void captureScreenshot(WebDriver driver, String fileName) {
		TakesScreenshot ts = (TakesScreenshot) Constants.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss").format(new java.util.Date());
		try {
			FileUtils.copyFile(src, new File("Screenshot/" + fileName + " " + dateTimeFormat +".jpg"));
		} catch (IOException e) {
			System.out.println("Screenshot result failed");
			e.printStackTrace();
		}
	}

}
