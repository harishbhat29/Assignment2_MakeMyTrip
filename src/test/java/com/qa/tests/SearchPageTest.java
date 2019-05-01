package com.qa.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.baseclass.TestBase;
import com.qa.pages.SearchPage;
import com.qa.utility.TestUtil;

public class SearchPageTest extends TestBase {

	SearchPage searchPage;

	public SearchPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		initialize();
		searchPage = new SearchPage();

	}

	@Test
	public void searchForFlights_Test() {
		searchPage.clickOnRounTripButton();
		searchPage.selectFromAndToCity(prop.getProperty("fromCity"), prop.getProperty("toCity"));

		/*
		 * driver.switchTo().frame("notification-frame-~19714b447");
		 * driver.findElement(By.id(
		 * "webklipper-publisher-widget-container-notification-close-div")).click();
		 * driver.switchTo().defaultContent();
		 */

		String currentdate = TestUtil.getCurrentDate();
		String returnDate = TestUtil.addNumberOfDaysToCurrentDate(7);

		searchPage.selectDepartureDate(currentdate);
		searchPage.selectReturnDate(returnDate);
		driver.manage().deleteAllCookies();
		searchPage.clickOnSearchBtn();
		driver.manage().deleteAllCookies();

		String textFromSearchResult = searchPage.getTextOfBookNowBtn();
		String expectedText = "BOOK NOW";
		Assert.assertEquals(textFromSearchResult, expectedText);
	}
	
	

	@AfterMethod
	public void tearDown() {

		if (driver != null)
			driver.close();

	}

}


