package com.property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.amazonepage.StaticVar;

public class PropertyUtility {

	public static String getProperty(String key) {
		String value = null;
		try {
			StaticVar.fis = new FileInputStream("Input/amazonsitepath.properties");
			Properties p = new Properties();
			p.load(StaticVar.fis);
			value = p.getProperty(key);
		} catch (IOException e) {
			System.out.println("Unable to load Properties File");
			e.printStackTrace();
		}
		return value;
	}
	
}
