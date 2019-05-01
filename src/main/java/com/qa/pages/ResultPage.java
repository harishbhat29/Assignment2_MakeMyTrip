package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseclass.TestBase;
import com.qa.utility.TestUtil;

public class ResultPage extends TestBase {

	public ResultPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBys({
			@FindBy(how = How.XPATH, xpath = "//div[@class='splitVw-sctn pull-left']//div[@class='fli-list splitVw-listing']") })
	List<WebElement> countOfArraivalFlights;

	@FindBys({
			@FindBy(how = How.XPATH, xpath = "//div[@class='splitVw-sctn pull-right']//div[@class='fli-list splitVw-listing']") })
	List<WebElement> countOfDepartureFlights;

	@FindBys({ @FindBy(how = How.XPATH, xpath = "//span[@class='labeltext']") })
	List<WebElement> listOfAllFilters;

	@FindBy(how = How.XPATH, xpath = "//div[@class='splitVw-footer-left ']//p[@class='actual-price']/span")
	WebElement departureFlightPriceFromFooter;

	@FindBy(how = How.XPATH, xpath = "//div[@class='splitVw-footer-right ']//p[@class='actual-price']/span")
	WebElement returnFlightPriceFromFooter;

	@FindBy(how = How.XPATH, xpath = "//span[@class='splitVw-total-fare']/span")
	WebElement totalFare;

	public String getTotalFare() {
		return totalFare.getText();
	}

	public String getReturnFlightPriceFromTheList(int returnFlightRowNumber) {
		String returnFlightPriceFromTheList = driver
				.findElement(By.xpath("(//div[@class='splitVw-sctn pull-right']//p[@class='actual-price']/span)["
						+ returnFlightRowNumber + "]"))
				.getText();
		return returnFlightPriceFromTheList;
	}

	public String getDepartureFlightPriceFromTheList(int departureFlightRowNumber) {
		String departureFlightPriceFromTheList = driver
				.findElement(By.xpath("(//div[@class='splitVw-sctn pull-left']//p[@class='actual-price']/span)["
						+ departureFlightRowNumber + "]"))
				.getText();
		return departureFlightPriceFromTheList;
	}

	public String getDepartureFlightPriceFromFooter() {
		return departureFlightPriceFromFooter.getText();
	}

	public String getReturnFlightPriceFromFooter() {
		return returnFlightPriceFromFooter.getText();
	}

	public void clickOnArraivalAndDepartureFlights(int returnFlightRowNumber, int departureFlightRowNumber) {

		String xpathOfDepartureFlights = "(//div[@class='splitVw-sctn pull-left']//label[@class='splitVw-radio clearfix cursor_pointer'])"
				+ "[" + departureFlightRowNumber + "]";
		TestUtil.scrollToElement(driver, driver.findElement(By.xpath(xpathOfDepartureFlights)));
		TestUtil.clickElement(driver, driver.findElement(By.xpath(xpathOfDepartureFlights)));
		reportEvent("INFO", "Clicked on " + departureFlightRowNumber + "th flight");

		TestUtil.staticSleep(2000);

		String xpathOfReturnFlights = "(//div[@class='splitVw-sctn pull-right']//label[@class='splitVw-radio clearfix cursor_pointer'])"
				+ "[" + returnFlightRowNumber + "]";
		TestUtil.scrollToElement(driver, driver.findElement(By.xpath(xpathOfReturnFlights)));
		TestUtil.clickElement(driver, driver.findElement(By.xpath(xpathOfReturnFlights)));
		reportEvent("INFO", "Clicked on " + returnFlightRowNumber + "th flight");

	}

	public int countOfArraivalFlights() {
		return countOfArraivalFlights.size();
	}

	public int countOfDepartureFlights() {
		return countOfDepartureFlights.size();

	}

	public void clickOnNonStopFlightsFilter() {
		for (WebElement element : listOfAllFilters) {
			if (element.getText().equals("Non Stop")) {
				element.click();
				reportEvent("INFO", "Clicked on Non Stop Filter");
				break;
			}
		}

	}

	public void clickOnOneStopFlightsFilter() {
		for (WebElement element : listOfAllFilters) {
			if (element.getText().equals("1 Stop")) {
				element.click();
				reportEvent("INFO", "Clicked on 1 Stop Filter");
				break;
			}
		}

	}

}
