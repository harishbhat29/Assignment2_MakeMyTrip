package com.qa.baseclass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qa.utility.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static Properties prop;
	public static FileInputStream fis;
	public static WebDriver driver;

	public TestBase() {
		prop = new Properties();
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		String browser = prop.getProperty("browser");
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(capabilities);
			reportEvent("INFO", "Chrome Browser Launched");
		}

		else if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			reportEvent("INFO", "Firefox Browser Launched");
		}

		else if (browser.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			reportEvent("INFO", "Internet Explorer Browser Launched");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIME_SECONDS, TimeUnit.SECONDS);

		String url = prop.getProperty("url");

		driver.get(url);
		reportEvent("INFO", "Navigated to url -- " + url);
	}

	public static void reportEvent(String eventType, String eventMessage) {
		switch (eventType) {
		case "INFO":
			Logger.getLogger(TestBase.class).info(eventMessage);
			break;
		case "DEBUG":
			Logger.getLogger(TestBase.class).debug(eventMessage);
			break;
		case "WARNING":
			Logger.getLogger(TestBase.class).warn(eventMessage);
			break;
		case "ERROR":
			Logger.getLogger(TestBase.class).error(eventMessage);
			break;
		case "FATAL":
			Logger.getLogger(TestBase.class).fatal(eventMessage);
			break;

		}
	}

}
