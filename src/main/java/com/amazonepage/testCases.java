package com.amazonepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.property.PropertyUtility;

public class testCases {
	@Test
	public void amezonHomePage() throws Exception {
		Keyword.openBrowser(PropertyUtility.getProperty("browser"));
		Keyword.openUrl(PropertyUtility.getProperty("site"));
		Keyword.maximizeBrowser();
		Keyword.getTitle();
		
		Keyword.getHomePageScreenshot(PropertyUtility.getProperty("screenshotpath"));
		Keyword.getHomePageFullScreenshot(PropertyUtility.getProperty("fullScreenshot"));
		
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("hellosignin"));
		Thread.sleep(3000);
		Keyword.close_OpenedTab("CSS", PropertyUtility.getProperty("closeTab"));
		Thread.sleep(3000);

		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("amazonlogo"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("logotagline"));
		
		Keyword.selectDropdown("CSS", PropertyUtility.getProperty("search_dropdown"), 36);
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("searchtab"));
		
		Keyword.enterText("CSS", PropertyUtility.getProperty("searchbox"),
				PropertyUtility.getProperty("text_in_searchbox"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("searchtab"));

		List<WebElement> listOfCustomerPreferance = StaticVar.driver.findElements(By.cssSelector("#nav-tools>a"));
		for (WebElement toolList : listOfCustomerPreferance) {
			String list = toolList.getText();
			System.out.println(list);
		}
		
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("globle_image"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("account_lists"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("return_order"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("try_prime"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("cart"));
	
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("select_your_address"));
		Thread.sleep(10000);
		Keyword.enterText("CSS", PropertyUtility.getProperty("pincodeField"), PropertyUtility.getProperty("pincode"));
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("applyPincode"));

		String[] expectedShoppingContainer = { "Today's Deals", "Best Sellers", "Mobiles", "Amazon Pay", "New Releases",
				"Customer Service", "Pantry", "Sell" };
		List<WebElement> shopingContainer = StaticVar.driver
				.findElements(By.cssSelector(PropertyUtility.getProperty("shop_container")));
		ArrayList<String> actualShoppingContainer = new ArrayList(shopingContainer);
		java.util.Iterator<WebElement> itr = shopingContainer.iterator();
		while (itr.hasNext()) {
			actualShoppingContainer.add(itr.next().getText());
		}
		for (String ShoppingList : expectedShoppingContainer) {
			Assert.assertTrue(actualShoppingContainer.contains(ShoppingList));
		}

		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("amazon_pay"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("todays_deals"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("best_seller"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("mobile"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("customer_service"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("new_release"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("pantry"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("sell"));
		
		Keyword.closeBrowser();
		Keyword.closeAllBrowser();

	}
	
}