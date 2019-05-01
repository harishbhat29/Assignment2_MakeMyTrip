package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.baseclass.TestBase;
import com.qa.pages.ResultPage;
import com.qa.pages.SearchPage;
import com.qa.utility.TestUtil;

public class ResultPageTest extends TestBase {

	SearchPage searchPage;
	ResultPage resultPage;

	@BeforeMethod
	public void setUp() {
		initialize();
		searchPage = new SearchPage();
		resultPage = new ResultPage();
		searchPage.clickOnRounTripButton();
		searchPage.selectFromAndToCity(prop.getProperty("fromCity"), prop.getProperty("toCity"));
		String currentdate = TestUtil.getCurrentDate();
		String returnDate = TestUtil.addNumberOfDaysToCurrentDate(7);
		searchPage.selectDepartureDate(currentdate);
		searchPage.selectReturnDate(returnDate);
		driver.manage().deleteAllCookies();
		searchPage.clickOnSearchBtn();
		driver.manage().deleteAllCookies();

	}

	@Test(priority = 4)
	public void printTotalNumberOfFlights_Test() {

		TestUtil.scrollDownToEnd(driver);
		System.out.println("******************************");
		System.out.println("Count of Arraival flights: " + resultPage.countOfArraivalFlights());
		System.out.println("Count of Departure flights: " + resultPage.countOfDepartureFlights());
		System.out.println("******************************");
	}

	@Test(priority = 2)
	public void printTotalNumberOfNonStopFlights_Test() {

		TestUtil.scrollDownToEnd(driver);
		TestUtil.scrollToTop(driver);
		resultPage.clickOnNonStopFlightsFilter();
		System.out.println("******************************");
		System.out.println(
				"Count of Arraival flights after clicking on Non-Stop Filter: " + resultPage.countOfArraivalFlights());
		System.out.println("Count of Departure flights after clicking on Non-Stop Filter: "
				+ resultPage.countOfDepartureFlights());
		System.out.println("******************************");

	}

	@Test(priority = 3)
	public void printTotalNumberOfOneStopFlights_Test() {

		TestUtil.scrollDownToEnd(driver);
		TestUtil.scrollToTop(driver);
		resultPage.clickOnOneStopFlightsFilter();
		System.out.println("******************************");
		System.out.println(
				"Count of Arraival flights after clicking on 1-Stop Filter: " + resultPage.countOfArraivalFlights());
		System.out.println(
				"Count of Departure flights after clicking on 1-Stop Filter: " + resultPage.countOfDepartureFlights());
		System.out.println("******************************");
	}

	@Test(priority = 1)
	public void verifyFlightPrice_Test() {

		TestUtil.scrollDownToEnd(driver);
		int departureFlightRowNumber = Integer.parseInt(prop.getProperty("departureFlightRowNumber"));
		int returnFlightRowNumber = Integer.parseInt(prop.getProperty("returnFlightRowNumber"));
		resultPage.clickOnArraivalAndDepartureFlights(returnFlightRowNumber, departureFlightRowNumber);

		String departureFlightPriceFromTheList = resultPage
				.getDepartureFlightPriceFromTheList(departureFlightRowNumber);
		String returnFlightPriceFromTheList = resultPage.getReturnFlightPriceFromTheList(returnFlightRowNumber);

		System.out.println("*****************************************");
		System.out.println("Price of Departure Flight from list: " + departureFlightPriceFromTheList);
		System.out.println("Price of Return Flight from list: " + returnFlightPriceFromTheList);
		System.out.println("*****************************************");

		// Verifying flight prices comparing from list and footer

		String departureFlightPriceFromFooter = resultPage.getDepartureFlightPriceFromFooter();
		String returnFlightPriceFromFooter = resultPage.getReturnFlightPriceFromFooter();

		System.out.println("*****************************************");
		System.out.println("Price of Departure Flight from footer: " + departureFlightPriceFromFooter);
		System.out.println("Price of Return Flight from footer: " + returnFlightPriceFromFooter);
		System.out.println("*****************************************");

		// Validation of Price

		Assert.assertEquals(departureFlightPriceFromTheList, departureFlightPriceFromFooter,
				"Departure Price is mismatching between list and footer");
		Assert.assertEquals(returnFlightPriceFromTheList, returnFlightPriceFromFooter,
				"Retur price is mismatching between list and footer");

		// Verify if total amount is correct or not

		System.out.println("Total Flight Fare from footer: " + resultPage.getTotalFare());
		System.out.println("*****************************************");

		int departureFlightFare = Integer
				.parseInt(resultPage.getDepartureFlightPriceFromFooter().replaceAll("[ ,a-zA-Z]", ""));
		int returnFlightFare = Integer
				.parseInt(resultPage.getReturnFlightPriceFromFooter().replaceAll("[ ,a-zA-Z]", ""));

		int totalFareFromFooter = Integer.parseInt(resultPage.getTotalFare().replaceAll("[ ,a-zA-Z]", ""));

		// Verifying if total amount is correct or not

		Assert.assertEquals(totalFareFromFooter, returnFlightFare + departureFlightFare,
				"Total Amount is not matching with sum of departure flight fare and return flight fare");

	}

	@AfterMethod
	public void tearDown() {
		if(driver != null)
			driver.close();

	}

}
