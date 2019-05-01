package com.qa.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.qa.baseclass.TestBase;

public class TestUtil {
	public static int PAGE_LOAD_TIMEOUT_SECONDS = 20;
	public static int IMPLICIT_WAIT_TIME_SECONDS = 30;

	public static void selectLocationFromSuggestions(WebElement location, List<WebElement> citySuggestions,
			String cityName) {
		location.click();
		location.sendKeys(cityName);
		for (WebElement city : citySuggestions) {
			if (city.getText().contains(cityName)) {
				city.click();
				TestBase.reportEvent("INFO", "Selected city from the list");
				break;
			}
		}
	}

	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());// Now use today date.
		return sdf.format(c.getTime());
	}

	public static String addNumberOfDaysToCurrentDate(int numberOfDays) {
		SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());// Now use today date.
		c.add(Calendar.DATE, numberOfDays); // Adding 5 days
		return sdf.format(c.getTime());
	}

	public static void scrollDownToEnd(WebDriver driver) {
		try {
			long lastHeight = (long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

			while (true) {
				((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
				TestBase.reportEvent("INFO", "Webpage scrolled down to the end");
				Thread.sleep(2000);
				
				long newHeight = (long) ((JavascriptExecutor) driver)
						.executeScript("return document.body.scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void staticSleep(int seconds) {
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void scrollToTop(WebDriver driver) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);");
		TestBase.reportEvent("INFO", "Webpage Scrolled to the top");
	}
	
	public static void actionsClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.click().build().perform();
	}
	
	public static void scrollToElement(WebDriver driver, WebElement element) {
	
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		staticSleep(500);
		TestBase.reportEvent("INFO", "Scrolled to the element");
	}
	
	public static void clickElement(WebDriver driver, WebElement element) {
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		staticSleep(500);
		TestBase.reportEvent("INFO", "Clicked on element using JavaScript Executor");
	}

}
