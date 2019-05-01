package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseclass.TestBase;
import com.qa.utility.TestUtil;

public class SearchPage extends TestBase {

	public SearchPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, xpath = "(//span[@class='tabsCircle appendRight5'])[2]")
	WebElement roundTripbutton;

	@FindBy(how = How.ID, id = "fromCity")
	WebElement fromLocation;

	@FindAll({ @FindBy(how = How.XPATH, xpath = "//p[@class='font16 appendBottom8']") })
	List<WebElement> fromCitySuggestions;

	@FindBy(how = How.ID, id = "toCity")
	WebElement toLocation;

	@FindAll({ @FindBy(how = How.XPATH, xpath = "//p[@class='font16 appendBottom8']") })
	List<WebElement> toCitySuggestions;

	@FindBy(how = How.XPATH, xpath = "//span[@class='lbl_input latoBold appendBottom10' and contains(text(),'DEPARTURE')]")
	WebElement departureDate;

	@FindBy(how = How.XPATH, xpath = "//span[@class='lbl_input latoBold appendBottom10' and contains(text(),'RETURN')]")
	WebElement returnDate;

	@FindBy(how = How.XPATH, xpath = "//a[@class='primaryBtn font24 latoBlack widgetSearchBtn ']")
	WebElement searchBtn;

	@FindAll({ @FindBy(how = How.XPATH, xpath = "//div[@role='gridcell' and @aria-disabled='false']//p") })
	List<WebElement> dateList;
	
	@FindBy(how = How.XPATH, xpath = "//button[@class='btn fli_primary_btn text-uppercase']")
	WebElement bookNowBtn;
	
	public String getTextOfBookNowBtn() {
		return bookNowBtn.getText().trim();
	}

	public void clickOnRounTripButton() {
		roundTripbutton.click();
	}

	public void selectFromAndToCity(String fromCity, String toCity) {
		TestUtil.selectLocationFromSuggestions(fromLocation, fromCitySuggestions, fromCity);
		TestUtil.selectLocationFromSuggestions(toLocation, toCitySuggestions, toCity);
	}

	public void selectDepartureDate(String departureDay) {
		departureDate.click();
		String[] dateArr = departureDay.split("/");
		String day = dateArr[0];

		for (WebElement ele : dateList) {
			if (ele.getText().equals(day)) {
				ele.click();
				reportEvent("INFO", "Clicked on Departure Date");
				break;
			}
		}
	}

	public void selectReturnDate(String returnDay) {
		returnDate.click();
		String[] dateArr = returnDay.split("/");
		String day = dateArr[0];

		for (WebElement ele : dateList) {
			if (ele.getText().equals(day)) {
				ele.click();
				reportEvent("INFO", "Clicked on Return Date");
				break;
			}
		}
	}

	public ResultPage clickOnSearchBtn() {
		Actions actions = new Actions(driver);
		actions.moveToElement(searchBtn).click().build().perform();
		reportEvent("INFO", "Clicked on Search button");
		return new ResultPage();
	}
}
