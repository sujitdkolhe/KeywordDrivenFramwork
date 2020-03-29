package com.amazonhomepage;

import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import ru.yandex.qatools.ashot.AShot;

public class Constants {
	public static WebDriver driver;
	public static WebElement element;
	public static FileInputStream fis;
	public static String actual;
	public static String expected;
	public static Select select;
	public static AShot ashot;
}
