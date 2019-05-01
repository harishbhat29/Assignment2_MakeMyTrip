package com.qa.rough;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.pages.SearchPage;
import com.qa.utility.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestMethod {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT_SECONDS, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT_TIME_SECONDS, TimeUnit.SECONDS);
		
		String url = "https://www.makemytrip.com";
		
		driver.get(url);
		
		driver.findElement(By.xpath("(//span[@class='tabsCircle appendRight5'])[2]")).click();
		
		Date date = new Date();
		String currentDate = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.SHORT).format(date);
		
		
		String[] dateArr = currentDate.split(" ");
		String day = dateArr[1].replaceAll("[\\-\\+\\.\\^:,]","");
		String month = dateArr[2].replaceAll("[\\-\\+\\.\\^:,]","");
		String year = dateArr[0].replaceAll("[\\-\\+\\.\\^:,]","");
		
		System.out.println(currentDate);
		System.out.println(day);
		System.out.println(month);
		System.out.println(year);
		
		driver.findElement(By.xpath("//span[@class='lbl_input latoBold appendBottom10']")).click();
		List<WebElement> dateList = driver.findElements(By.xpath("//div[@role='gridcell' and @aria-disabled='false']//p"));
		for(int i = 0; i<7; i++) {
			if(i==0)
				dateList.get(i).click();
			break;
		}
		

	}

}
