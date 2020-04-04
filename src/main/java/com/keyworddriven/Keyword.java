package com.keyworddriven;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Keyword {
	/**
	 * This method is used to open specific browser. Browser name should be as
	 * follow :<br>
	 * <ol>
	 * <li>Chrome</li>
	 * <li>Firefox</li>
	 * <li>IE</li> only above mention browsers can be open using this method
	 * 
	 * @param browserName
	 */
	public static void openBrowser(String browserName) {
		switch (browserName) {
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
			System.err.println("Invalid browser name : " + browserName);
		}

	}

	/**
	 * This method is used to launched the url in the browser is looking at. .
	 * 
	 * @param url
	 */
	public static void launchUrl(String url) {
		Constants.driver.get(url);

	}

	/**
	 * This method is used to maximizes the current window which recently launched
	 * url.
	 * 
	 */
	public static void maximizeBrowser() {
		Constants.driver.manage().window().maximize();
	}

	/**
	 * This method is used to capture the screenshot and store it in the specified
	 * location. Constructs a SimpleDateFormat using the given pattern and pattern
	 * the pattern describing the date and time format
	 * <ol>
	 * <li>Visible portion of the current frame</li>
	 * <li>Pattern describing the date and time format</li>
	 * </ol>
	 * 
	 * @OutputType : jpg Type for the screenshot output
	 */
	public static void captureScreenshot(String screenshotPath, String fileName, String extension) {
		TakesScreenshot ts = (TakesScreenshot) Constants.driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss").format(new java.util.Date());
		try {
			FileUtils.copyFile(src, new File(screenshotPath + fileName + " " + dateTimeFormat + extension));
		} catch (IOException e) {
			System.out.println("Screenshot result failed");
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to capture the screenshot and store it in the specified
	 * location. Constructs a SimpleDateFormat using the given pattern and the
	 * default date format symbols for the default
	 * <ol>
	 * <li>Entire page</li>
	 * <li>Pattern describing the date and time format</li>
	 * </ol>
	 * 
	 * @OutputType : jpg Type for the screenshot output
	 */
	public static void captureEntireScreenshot(String FullScreenshot, String fileName, String extension) {
		Constants.ashot = new AShot();
		Constants.ashot.shootingStrategy(ShootingStrategies.viewportPasting(2000));
		Screenshot sc = Constants.ashot.takeScreenshot(Constants.driver);
		BufferedImage image = sc.getImage();
		String dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss").format(new java.util.Date());
		try {
			ImageIO.write(image, "jpg", new File(FullScreenshot + fileName + " " + dateTimeFormat + extension));
		} catch (IOException e) {
			System.err.println("Full screenshot result faild");
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to get the matching web element on the current page
	 * follow :<br>
	 * <ol>
	 * <li>XPATH</li>
	 * <li>ID</li>
	 * <li>CSS</li>
	 * <li>NAME</li>
	 * <li>CLASS</li>only above mention locators type can be select using this
	 * method
	 * 
	 * @return WebElement
	 * 
	 */
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

	/**
	 * This method is used to click on target web element on current page.
	 * 
	 */
	public static void clickOnElement(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to click on element");
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to close, the opened tab in current window.
	 * 
	 */
	public static void closedTab(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
	}

	/**
	 * This method is used to verify, is this element displayed or not?
	 *
	 * @return Whether or not the element is displayed
	 */
	public static void getLogo(String locatorType, String locatorValue) {
		boolean expected = getWebElement(locatorType, locatorValue).isDisplayed();
		Assert.assertTrue(expected);
	}

	/**
	 * This method is used to get the visible text of target element.
	 * 
	 */
	public static void getElementText(String locatorType, String locatorValue) {
		Constants.element = getWebElement(locatorType, locatorValue);
		Constants.actual = Constants.element.getText();
	}

	/**
	 * This method is used to get the value of a given CSS property. Color values
	 * should be returned.
	 * 
	 * @param propertyName the css property name of the element
	 * @return The current, computed value of the property.
	 * 
	 */
	public static void getColor(String locatorType, String locatorValue, String propertyValue) {
		Constants.element = getWebElement(locatorType, locatorValue);
		Constants.actual = Constants.element.getCssValue(propertyValue);
	}

	/**
	 * This method is used to hover on target element to move to on current window.
	 * 
	 */
	public static void hoverOnElement(String locatorType, String locatorValue) {
		boolean selected = getWebElement(locatorType, locatorValue).isSelected();
		Constants.action = new Actions(Constants.driver);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("unbable to move on element");
			e.printStackTrace();
		}
		Constants.action.moveToElement(Constants.element).perform();
	}

	/**
	 * This method is used to get the value of a given CSS property after hover on
	 * web element. Color values should be returned as rgba strings
	 * 
	 * @param propertyName the css property name of the element
	 * @return The current, computed value of the property.
	 * 
	 */
	public static void getColor_After_Hover(String locatorType, String locatorValue, String propertyValue) {
		hoverOnElement(locatorType, locatorValue);
		Constants.actual = Constants.element.getCssValue(propertyValue);

	}

	/**
	 * This method is used to scroll.
	 * 
	 */
	public static void scrolling() {
		Actions action = new Actions(Constants.driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.err.println("unable to scroll");
			e.printStackTrace();
		}
		action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
	}

	/**
	 * This method is used to select the option from dropdown at the given index.
	 * 
	 */
	public static void selectDropdown(String locatorType, String locatorValue, int indexToEnter) {
		Constants.element = getWebElement(locatorType, locatorValue);
		Constants.select = new Select(Constants.element);
		Constants.select.selectByIndex(indexToEnter);

	}

	/**
	 * This method is used to typing the text in the target element.
	 *
	 * @param keysToSend character sequence to send to the element
	 */
	public static void enterText(String locatorType, String locatorValue, String textToEnter) {
		getWebElement(locatorType, locatorValue).sendKeys(textToEnter);
	}

	/**
	 * This method is used switch to on a different frame or window and to navigate
	 * to back on previous window.
	 *
	 * 
	 */
	public static void navigateToBackPage(String locatorType, String locatorValue) {
		getWebElement(locatorType, locatorValue).click();
		Constants.actual = Constants.driver.getCurrentUrl();
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigate back");
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to read JSON file but JSON file in Key value pairs are
	 * unordered.
	 *
	 * @param filepath the path of the file to read
	 * @throws IOException
	 * @throws FileNotFoundException
	 * 
	 */

	public static void read_Json(String filePath, String key)
			throws ParseException, FileNotFoundException, IOException {
		Constants.obj = new JSONParser().parse(new FileReader(filePath));
		Constants.jsonObj = (JSONObject) Constants.obj;
		Constants.jsonArray = (JSONArray) Constants.jsonObj.get(key);
		Iterator<String> itr = Constants.jsonArray.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
	}

	/**
	 * This method is used to switch to a different frame or window.
	 *
	 * @return A TargetLocator which can be used to select a frame or window
	 */
	public static void switchToWindow(int windowIndex) {
		Set<String> newWindow = Constants.driver.getWindowHandles();
		System.out.println(newWindow);
		ArrayList<String> getWindow = new ArrayList(newWindow);
		Constants.driver.switchTo().window(getWindow.get(windowIndex));

		/**
		 * This method is used to create logging operations, except configuration and See
		 * {@getLogger(String)} detailed information.
		 * 
		 */
	}

	public static void loggerInfo(String massage) {
		Logger logger = Logger.getLogger(Keyword.class);
		logger.info(massage);
	}

	/**
	 * This method is used to Close the current window, quitting the browser if it's
	 * the last window currently open.
	 * 
	 */
	public static void closeBrowser() {
		Constants.driver.close();
	}

	/**
	 * This method is used to quits this driver, closing every associated window.
	 */
	public static void closeAllBrowser() {
		Constants.driver.quit();
	}

}