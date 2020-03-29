package com.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.amazonhomepage.Constants;

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

}
