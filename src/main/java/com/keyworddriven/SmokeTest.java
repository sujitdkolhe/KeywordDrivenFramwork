package com.keyworddriven;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.utility.PropertyUtility;

public class SmokeTest {
	

	@BeforeTest
	public void openBrowser() {
		Keyword.openBrowser(PropertyUtility.getProperty("browser"));
		Keyword.loggerInfo("launching chrome browser");
	}

	@Test
	public void tc_01() {
		Keyword.launchUrl(PropertyUtility.getProperty("appUrl"));
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(PropertyUtility.getProperty("appUrl").contains(Constants.actual));
		Keyword.maximizeBrowser();
		Keyword.loggerInfo("entering appliction url and maximizing browser");
	}

	@Test
	public void tc_02() {
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue("https://www.amazon.in/".contains(Constants.actual));
		Keyword.loggerInfo("verifying current page url");
	}

	@Test
	public void tc_03() {
		Constants.actual = Constants.driver.getTitle();
		Constants.expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		Assert.assertTrue(Constants.expected.contains(Constants.actual));
		Keyword.loggerInfo("verifying current page title");
	}

	@Test
	public void tc_04() {
		Keyword.captureScreenshot(PropertyUtility.getProperty("screenshotPath"), "Screenshot", ".png");
		Keyword.loggerInfo("capturing visible portion of the current frame and describing the date and time format");

	}

	@Test
	public void tc_05() {
		Keyword.captureEntireScreenshot(PropertyUtility.getProperty("FullScreenshot"), "Full_Screenshot", ".jpg");
		Keyword.loggerInfo("capturing entire portion of the current page and describing the date and time format");
	}

	@Test
	public void tc_06() {
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("openMenu"));
		Keyword.closedTab("CSS", PropertyUtility.getProperty("closeTab"));
		Keyword.loggerInfo("verifying by clicking on munu tab it open or not and closing the menu tab");
	}

	@Test
	public void tc_07() {
		Keyword.getLogo("CSS", PropertyUtility.getProperty("amazonalogo"));
		Keyword.loggerInfo("verify 'amazon' logo visible or not");
	}

	@Test
	public void tc_08() {
		Keyword.getWebElement("CSS", PropertyUtility.getProperty("amazon")).click();
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(PropertyUtility.getProperty("navigatToAmazonLogo").contains(Constants.actual));
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigate back");
			e.printStackTrace();
		}
		Keyword.loggerInfo(
				"verifying by clicking on 'amazon logo' will it switch to on next page and navigate back on previous page or not");

	}

	@Test
	public void tc_09() {
		Keyword.getLogo("CSS", PropertyUtility.getProperty("inlogo"));
		Keyword.loggerInfo("verify 'in' logo visible or not");
	}

	@Test
	public void tc_10() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("tryPrime"));
		Assert.assertEquals(Constants.actual, "Try Prime", "search result text failed");
		Keyword.loggerInfo("verifying 'Try Prime' text visible or not");
	}

	@Test
	public void tc_11() {
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("tryPrime"));
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(PropertyUtility.getProperty("navigatToPrime").contains(Constants.actual));
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigate back");
			e.printStackTrace();
		}
		Keyword.loggerInfo(
				"verifying by clicking on 'Try Prime logo' will it switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_12() {
		Keyword.getColor("CSS", PropertyUtility.getProperty("search_dropdown"),
				PropertyUtility.getProperty("search_dropdownBackgroundColor"));
		Assert.assertEquals(Constants.actual, PropertyUtility.getProperty("search_dropdownExpectedColor"));
		Keyword.loggerInfo("verifing background color of 'All Categories' dropdown befor hover");
	}

	@Test
	public void tc_13() {
		Keyword.getColor_After_Hover("CSS", PropertyUtility.getProperty("search_dropdown"),
				PropertyUtility.getProperty("afterSearch_dropdownColor"));
		Assert.assertEquals(Constants.actual, PropertyUtility.getProperty("afterSearchExpectedColor"));
		Keyword.loggerInfo("verifing background color of 'All Categories' dropdown after hovering");
	}

	@Test
	public void tc_14() {
		Keyword.selectDropdown("CSS", PropertyUtility.getProperty("search_dropdown"), 36);
		Constants.element = Constants.select.getFirstSelectedOption();
		Constants.actual = Constants.element.getText();
		Assert.assertEquals(Constants.actual, PropertyUtility.getProperty("expectedValueInDropdown"));
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("searchtab"));
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(PropertyUtility.getProperty("navigatToSoftware").contains(Constants.actual));
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigate back");
			e.printStackTrace();
		}
		Keyword.loggerInfo(
				"verifying by selecting option from dropdown will it  switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_15() {
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("search_dropdown"));
		Keyword.scrolling();
		Keyword.loggerInfo("scrolling All departrment dropdown");
	}

	@Test
	public void tc_16() {
		Keyword.getColor("CSS", PropertyUtility.getProperty("searchtab"),
				PropertyUtility.getProperty("search_tabBackgroundColor"));
		Assert.assertEquals(Constants.actual, PropertyUtility.getProperty("search_tabExpectedColor"));
		Keyword.loggerInfo("verifing background color of 'search button' dropdown befor hover");
	}

	@Test
	public void tc_17() {
		Keyword.getColor_After_Hover("CSS", PropertyUtility.getProperty("searchtab"),
				PropertyUtility.getProperty("afterSearchTab_dropdownColor"));
		Assert.assertEquals(Constants.actual, PropertyUtility.getProperty("afterSearchTab_ExpectedColor"));
		Keyword.loggerInfo("verifing background color of 'search button' dropdown after hovering");
	}

	@Test
	public void tc_18() {
		Keyword.enterText("CSS", PropertyUtility.getProperty("searchbox"),
				PropertyUtility.getProperty("text_in_searchbox"));
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("searchtab"));
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(PropertyUtility.getProperty("navigateToSearchText").contains(Constants.actual));
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigate back");
			e.printStackTrace();
		}
		Keyword.loggerInfo(
				"verifying by typing text on search box will it switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_19() {
		Keyword.getLogo("CSS", PropertyUtility.getProperty("searchLogo"));
		Keyword.loggerInfo("verify 'search' logo visible or not");
	}

	@Test
	public void tc_20() {
		Keyword.getLogo("CSS", PropertyUtility.getProperty("ENimage"));
		Keyword.loggerInfo("verify 'global' logo visible or not");
	}

	@Test
	public void tc_21() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("language"));
		Assert.assertEquals(Constants.actual, "EN", "search result text failed");
		Keyword.loggerInfo("verifying 'EN' text visible or not");
	}

	@Test
	public void tc_22() {
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("globle_image"));
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(PropertyUtility.getProperty("navigatToLanguage").contains(Constants.actual));
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigate back");
			e.printStackTrace();
		}

		Keyword.loggerInfo("verifying will it switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_23() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("HelloSignIn"));
		Assert.assertEquals(Constants.actual, "Hello. Sign in", "search result text failed");
		Keyword.loggerInfo("verifying 'Hello. Sign in' text visible or not");
	}

	@Test
	public void tc_24() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("accountList"));
		Assert.assertEquals(Constants.actual, "Account & Lists", "search result text failed");
		Keyword.loggerInfo("verifying 'Account & Lists' text visible or not");
	}

	@Test
	public void tc_25() {
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("account_lists"));
		Constants.actual = Constants.driver.getCurrentUrl();
		Assert.assertTrue(PropertyUtility.getProperty("navigatToHelloSignIn").contains(Constants.actual));
		Constants.driver.navigate().back();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.out.println("Unable to navigate back");
			e.printStackTrace();
		}
		Keyword.loggerInfo(
				"verifying by clicking on 'Hello sign.in' will it switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_26() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("return"));
		Assert.assertEquals(Constants.actual, "Returns", "search result text failed");
		Keyword.loggerInfo("verifying 'Returns' text visible or not");
	}

	@Test
	public void tc_27() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("order"));
		Assert.assertEquals(Constants.actual, "& Orders", "search result text failed");
		Keyword.loggerInfo("verifying '& Orders' text visible or not");
	}

	@Test
	public void tc_28() {
		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("return_order"));
		Assert.assertTrue(Constants.actual.contains(PropertyUtility.getProperty("navigatToReturn")));
		Keyword.loggerInfo(
				"verifying by clicking on 'returns and orders' will it switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_29() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Try"));
		Assert.assertEquals(Constants.actual, "Try", "search result text failed");
		Keyword.loggerInfo("verifying 'Try' text visible or not");
	}

	@Test
	public void tc_30() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Prime"));
		Assert.assertEquals(Constants.actual, "Prime", "search result text failed");
		Keyword.loggerInfo("verifying 'Prime' text visible or not");
	}

	@Test
	public void tc_31() {
	Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("try_prime"));
	Assert.assertTrue(Constants.actual.contains(PropertyUtility.getProperty("navigatToTry")));
		Keyword.loggerInfo(
				"verifying by clicking on 'Try' will it switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_32() {
		Keyword.getLogo("CSS", PropertyUtility.getProperty("CartIcon"));
		Keyword.loggerInfo("verifying 'Cart' Icon visible or not");
	}

	@Test
	public void tc_33() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Cart"));
		Assert.assertEquals(Constants.actual, "Cart", "search result text failed");
		Keyword.loggerInfo("verifying 'Cart' text visible or not");
	}

	@Test
	public void tc_34() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Zero"));
		Assert.assertEquals(Constants.actual, "0", "search result text failed");
		Keyword.loggerInfo("verifying '0' number visible or not");
	}

	@Test
	public void tc_35() {
		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("cart"));
		Assert.assertTrue(Constants.actual.contains(PropertyUtility.getProperty("navigatToCart")));
		Keyword.loggerInfo(
				"verifying by clicking on 'cart' will it switch to on next page and navigate back on previous page or not");
	}

	@Test
	public void tc_36() {
		Keyword.getLogo("CSS", PropertyUtility.getProperty("adressglow_icon"));
		Keyword.loggerInfo("verifying 'adress glow icon'  visible or not");
	}

	@Test
	public void tc_37() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("Hello"));
		Assert.assertEquals(Constants.actual, "Hello", "search result text failed");
		Keyword.loggerInfo("verifying 'Hello' text visible or not");
	}

	@Test
	public void tc_38() {
		Keyword.getElementText("CSS", PropertyUtility.getProperty("SelectAddress"));
		Assert.assertEquals(Constants.actual, "Select your address", "search result text failed");
		Keyword.loggerInfo("verifying 'Select your address' text visible or not");
	}

	@Test
	public void tc_39() {
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("select_your_address"));
		Keyword.enterText("CSS", PropertyUtility.getProperty("pincodeField"), PropertyUtility.getProperty("pincode"));
		Keyword.clickOnElement("CSS", PropertyUtility.getProperty("applyPincode"));
		Keyword.loggerInfo(
				"verifying by clicking on 'select_your_address' element popup will visible or not and entering text on 'address' box and click on 'Apply tab'");
	}

	@Test
	public void tc_40() {
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("openMenu"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("amazon"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("tryPrime"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("search_dropdown"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("searchtab"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("globle_image"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("account_lists"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("return_order"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("try_prime"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("cart"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("select_your_address"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("amazon_pay"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("todays_deals"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("best_seller"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("mobile"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("customer_service"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("new_release"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("pantry"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("sell"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("computer"));
		Keyword.hoverOnElement("CSS", PropertyUtility.getProperty("book"));
		Keyword.loggerInfo("all test cases in this method hover on element");
	}
//	@Test
//	public void tc_42() {
//		String[] expectedShoppingContainer = { "Today's Deals", "Best Sellers", "Mobiles", "Amazon Pay", "New Releases",
//				"Customer Service", "Pantry", "Sell", "Computers", "Books" };
//		List<WebElement> shopingContainer = Constants.driver
//				.findElements(By.cssSelector(PropertyUtility.getProperty("shop_container")));
//		ArrayList<String> actualShoppingContainer = new ArrayList(shopingContainer);
//		java.util.Iterator<WebElement> itr = shopingContainer.iterator();
//		while (itr.hasNext()) {
//			actualShoppingContainer.add(itr.next().getText());
//		}
//		for (String ShoppingList : expectedShoppingContainer) {
//			Assert.assertTrue(actualShoppingContainer.contains(ShoppingList));
//			Keyword.loggerInfo("Verifying elements present in string array, is present in shop container or not");
//		}
//
//	}
//	@Test
//	public void tc_40() {
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("amazon_pay"),
//				PropertyUtility.getProperty("navigatToamazon_pay"));
//
//		Keyword.switchToOnNextPage_and_NavigateToback("CSS", PropertyUtility.getProperty("todays_deals"),
//				PropertyUtility.getProperty("navigatTotodays_deals"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("best_seller"),
//				PropertyUtility.getProperty("navigatTobest_seller"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("mobile"),
//				PropertyUtility.getProperty("navigatTomobile"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("customer_service"),
//				PropertyUtility.getProperty("navigatTocustomer_service"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("new_release"),
//				PropertyUtility.getProperty("navigatTonew_release"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("pantry"),
//				PropertyUtility.getProperty("navigatTopantry"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("sell"),
//				PropertyUtility.getProperty("navigatTosell"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("computer"),
//				PropertyUtility.getProperty("navigatTocomputer"));
//
//		Keyword.navigateToBackPage("CSS", PropertyUtility.getProperty("book"),
//				PropertyUtility.getProperty("navigatTobook"));
//
//		Keyword.loggerInfo(
//				"verifying all the test cases in this method by clicking on element will it switch to on next page and navigate to on previous page or not");
//	}

	@Test
	public void tc_41() {
		Keyword.getLogo("CSS", PropertyUtility.getProperty("COVID-19"));
		Keyword.loggerInfo("verifying 'COVID-19'label visible or not");
	}


	@AfterTest
	public void CloseAllBrowser() {
		Keyword.closeBrowser();
		Keyword.closeAllBrowser();
		Keyword.loggerInfo("Close the current window and quits this driver, closing every associated window");
	}

	@AfterMethod
	public void tearTest(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			PropertyUtility.captureScreenshot(Constants.driver, result.getName());
}
 }
}