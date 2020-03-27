package com.amazonepage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
			StaticVar.driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			StaticVar.driver = new FirefoxDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			StaticVar.driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Invalid browser name : " + openBrowser);
		}
	}

	public static void openUrl(String url) {
		StaticVar.driver.get(url);
		String s = StaticVar.driver.getCurrentUrl();
		Assert.assertTrue(url.contains(s));
	}

	public static void maximizeBrowser() {
		StaticVar.driver.manage().window().maximize();
	}

	public static void getTitle() {
		String expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String title = StaticVar.driver.getTitle();
		Assert.assertTrue(expected.contains(title));
	}

	public static void getHomePageScreenshot(String screenshotPath) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) StaticVar.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(screenshotPath));
	}

	public static void getHomePageFullScreenshot(String fullScreenshot) throws IOException {
		AShot ashot = new AShot();
		ashot.shootingStrategy(ShootingStrategies.viewportPasting(2000));
		Screenshot sc = ashot.takeScreenshot(StaticVar.driver);
		BufferedImage image = sc.getImage();
		ImageIO.write(image, "jpg", new File(fullScreenshot));
	}

	public static WebElement getWebElement(String locatorType, String locatorValue) {
		StaticVar.element = null;
		switch (locatorType) {
		case "XAPTH":
			StaticVar.element = StaticVar.driver.findElement(By.xpath(locatorValue));
			break;
		case "ID":
			StaticVar.element = StaticVar.driver.findElement(By.id(locatorValue));
			break;
		case "CSS":
			StaticVar.element = StaticVar.driver.findElement(By.cssSelector(locatorValue));
			break;
		case "NAME":
			StaticVar.element = StaticVar.driver.findElement(By.name(locatorValue));
			break;
		case "CLASS":
			StaticVar.element = StaticVar.driver.findElement(By.className(locatorValue));
			break;
		default:
			System.err.println("Invalid Locator Type");
		}
		return StaticVar.element;
	}

	public static void clickOnElement(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
	}
	
	public static void clickOnElement_and_NavigatToBack(String locatorType, String locatorValue) throws InterruptedException {
		getWebElement(locatorType, locatorValue).click();
		StaticVar.driver.navigate().back();
		Thread.sleep(3000);
	}
	
	public static void close_OpenedTab(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
	}

	public static void selectDropdown(String locatorType, String locatorValue, int indexToEnter) {
		StaticVar.element = getWebElement(locatorType, locatorValue);
		Select select = new Select(StaticVar.element);
		select.selectByIndex(indexToEnter);
	}

	public static void enterText(String locatorType, String locatorValue, String textToEnter) {
		getWebElement(locatorType, locatorValue).sendKeys(textToEnter);
	}

	public static void closeBrowser() {
		StaticVar.driver.close();
	}

	public static void closeAllBrowser() {
		StaticVar.driver.quit();
	}
	
}