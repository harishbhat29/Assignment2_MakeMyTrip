package com.qa.rough;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

public class DateLogic {

	public static void main(String[] args) {
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());// Now use today date.
		String currDate = sdf.format(c.getTime());
		System.out.println(currDate);
		c.add(Calendar.DATE, 5); // Adding 5 days
		String output = sdf.format(c.getTime());
		
		System.out.println(output);		
	}

}
