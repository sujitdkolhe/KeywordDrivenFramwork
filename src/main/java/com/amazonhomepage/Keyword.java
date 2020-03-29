package com.amazonhomepage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Keyword {

	public static void openBrowser(String openBrowser) {
		switch (openBrowser) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			Constants.driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			Constants.driver = new FirefoxDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			Constants.driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Invalid browser name : " + openBrowser);
		}
	}

	public static void openUrl(String url) {
		Constants.driver.get(url);
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(url.contains(Constants.actual));
	}

	public static void maximizeBrowser() {
		Constants.driver.manage().window().maximize();
	}

	public static void getHomePageTitle(String expectedTitle) {
		Constants.actual = Constants.driver.getTitle();
		Assert.assertTrue(expectedTitle.contains(Constants.actual));
	}

	public static void getHomePageScreenshot(String screenshotPath) {
		TakesScreenshot ts = (TakesScreenshot) Constants.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(src, new File(screenshotPath));
		} catch (IOException e) {
			System.out.println("Screenshot result failed");
			e.printStackTrace();
		}
	}

	public static void getHomePageFullScreenshot(String fullScreenshot) {
		Constants.ashot = new AShot();
		Constants.ashot.shootingStrategy(ShootingStrategies.viewportPasting(2000));
		java.util.Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		String fileName=dateFormat.format(date);
		System.out.println(fileName);
		Screenshot sc = Constants.ashot.takeScreenshot(Constants.driver);
		BufferedImage image = sc.getImage();
		try {
			ImageIO.write(image, "jpg", new File(fullScreenshot));
		} catch (IOException e) {
			System.out.println("Full screenshot result faild");
			e.printStackTrace();
		}
	}

	public static WebElement getWebElement(String locatorType, String locatorValue) {
		Constants.element = null;
		switch (locatorType) {
		case "XAPTH":
			Constants.element = Constants.driver.findElement(By.xpath(locatorValue));
			break;
		case "ID":
			Constants.element = Constants.driver.findElement(By.id(locatorValue));
			break;
		case "CSS":
			Constants.element = Constants.driver.findElement(By.cssSelector(locatorValue));
			break;
		case "NAME":
			Constants.element = Constants.driver.findElement(By.name(locatorValue));
			break;
		case "CLASS":
			Constants.element = Constants.driver.findElement(By.className(locatorValue));
			break;
		default:
			System.err.println("Invalid Locator Type");
		}
		return Constants.element;
	}

	public static void clickOnElement(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to click on element");
			e.printStackTrace();
		}
	}

	public static void clickOnElement_and_NavigatToBack(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigat back");
			e.printStackTrace();
		}
	}

	public static void getElementText(String locatorType, String locatorValue, String expected) {
		Constants.element = getWebElement(locatorType, locatorValue);
		Constants.actual = Constants.element.getText();
		Assert.assertEquals(Constants.actual, expected, "search result text failed");
	}

	public static void close_OpenedTab(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
	}

	public static void selectDropdown(String locatorType, String locatorValue, int indexToEnter) {
		Constants.element = getWebElement(locatorType, locatorValue);
		Constants.select = new Select(Constants.element);
		Constants.select.selectByIndex(indexToEnter);
	}

	public static void enterText(String locatorType, String locatorValue, String textToEnter) {
		getWebElement(locatorType, locatorValue).sendKeys(textToEnter);
	}

	public static void closeBrowser() {
		Constants.driver.close();
	}

	public static void closeAllBrowser() {
		Constants.driver.quit();
	}

}