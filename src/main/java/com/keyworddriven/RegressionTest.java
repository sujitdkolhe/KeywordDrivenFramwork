package com.keyworddriven;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.utility.PropertyUtility;

public class RegressionTest {
	Logger logger = Logger.getLogger(SmokeTest.class);

	@Test
	public void toVerifyContains() throws InterruptedException {
		Keyword.openBrowser(PropertyUtility.getProperty("browser"));
		Keyword.launchUrl(PropertyUtility.getProperty("appUrl"));
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("customer_service"));
		Keyword.switchToWindow(0);
		Thread.sleep(3000);
		List<WebElement> listOfElement = Constants.driver
				.findElements(By.cssSelector(PropertyUtility.getProperty("Recommended_Topics")));
		ArrayList<String> actualShoppingContainer = new ArrayList(listOfElement);
		//System.out.println(actualShoppingContainer.size());
		java.util.Iterator<WebElement> itr = listOfElement.iterator();
		while (itr.hasNext()) {
			actualShoppingContainer.add(itr.next().getText());
			System.out.println(actualShoppingContainer.size());
		}
		System.out.println(actualShoppingContainer.size());
		logger.info("verying 'Recommended_Topics' present or not");
	}

}
