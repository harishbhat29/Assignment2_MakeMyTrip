package com.qa.rough;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rough {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		String currentDate = dateFormat.format(date);
		String[] dateArr = currentDate.split("/");
		String day = dateArr[2];
		String month = dateArr[1];
		String year = dateArr[0];
		
		
	}

}
