package com.amazonhomepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.property.PropertyUtility;

public class SmokeTest {
	@Test
	public void tc_01() {
		Keyword.openBrowser(PropertyUtility.getProperty("browser"));
		Keyword.openUrl(PropertyUtility.getProperty("site"));
		Keyword.maximizeBrowser();
		Keyword.getHomePageTitle(
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
	}

	@Test
	public void tc_02() {
		Keyword.getHomePageScreenshot(PropertyUtility.getProperty("screenshotpath"));
		Keyword.getHomePageFullScreenshot(PropertyUtility.getProperty("fullScreenshot"));
	}

	@Test
	public void tc_03() {
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("hellosignin"));
		Keyword.close_OpenedTab("CSS", PropertyUtility.getProperty("closeTab"));
	}

	@Test
	public void tc_04() {
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("amazonlogo"));
	}

	@Test
	public void tc_05() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("tryPrime"), "Try Prime");
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("tryPrime"));
	}

	@Test
	public void tc_06() {
		Keyword.selectDropdown("CSS", PropertyUtility.getProperty("search_dropdown"), 36);
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("searchtab"));
	}

	@Test
	public void tc_07() {
		Keyword.enterText("CSS", PropertyUtility.getProperty("searchbox"),
				PropertyUtility.getProperty("text_in_searchbox"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("searchtab"));
	}

	@Test
	public void tc_08() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("language"), "EN");
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("globle_image"));
	}

	@Test
	public void tc_09() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("HelloSignIn"), "Hello. Sign in");
		Keyword.getElementText("CSS", PropertyUtility.getProperty("accountList"), "Account & Lists");
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("account_lists"));
	}

	@Test
	public void tc_10() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("return"), "Returns");
		Keyword.getElementText("CSS", PropertyUtility.getProperty("order"), "& Orders");
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("return_order"));
	}

	@Test
	public void tc_11() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Try"), "Try");
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Prime"), "Prime");
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("try_prime"));
	}

	@Test
	public void tc_12() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Zero"), "0");
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Cart"), "Cart");
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("cart"));
	}

	@Test
	public void tc_13() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Hello"), "Hello");
		Keyword.getElementText("CSS", PropertyUtility.getProperty("SelectAddress"), "Select your address");
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("select_your_address"));
	}

	@Test
	public void tc_14() {
		Keyword.enterText("CSS", PropertyUtility.getProperty("pincodeField"), PropertyUtility.getProperty("pincode"));
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("applyPincode"));
	}

	@Test
	public void tc_15() {
		String[] expectedShoppingContainer = { "Today's Deals", "Best Sellers", "Mobiles", "Amazon Pay", "New Releases",
				"Customer Service", "Pantry", "Sell", "Computers", "Books" };
		List<WebElement> shopingContainer = Constants.driver
				.findElements(By.cssSelector(PropertyUtility.getProperty("shop_container")));
		ArrayList<String> actualShoppingContainer = new ArrayList(shopingContainer);
		java.util.Iterator<WebElement> itr = shopingContainer.iterator();
		while (itr.hasNext()) {
			actualShoppingContainer.add(itr.next().getText());
		}
		for (String ShoppingList : expectedShoppingContainer) {
			Assert.assertTrue(actualShoppingContainer.contains(ShoppingList));
		}
	}

	@Test
	public void tc_16() {
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("amazon_pay"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("todays_deals"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("best_seller"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("mobile"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("customer_service"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("new_release"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("pantry"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("sell"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("computer"));
		Keyword.clickOnElement_and_NavigatToBack("CSS", PropertyUtility.getProperty("book"));
	}

	@Test
	public void tc_17() {
		Keyword.closeBrowser();
		Keyword.closeAllBrowser();
	}

}