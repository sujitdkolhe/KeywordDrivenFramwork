package com.keyworddriven;

import java.io.FileInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import ru.yandex.qatools.ashot.AShot;

public class Constants {
	// This is the list of  variables
	// Declared as 'public', so that it can be used in other classes
	// Declared as 'static', so that we do not need to instantiate a class object
	public static WebDriver driver;
	public static WebElement element;
	public static FileInputStream fis;
	public static String actual;
	public static String expected;
	public static Select select;
	public static AShot ashot;
	public static Actions action;
	public static Object obj;
	public static JSONObject jsonObj;
	public static JSONArray jsonArray;
}
