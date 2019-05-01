package com.qa.rough;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import com.qa.baseclass.TestBase;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class DemoLog4j {

	/* Get actual class name to be printed on */
	static Logger log = Logger.getLogger(DemoLog4j.class.getName());

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
	

	public static void main(String[] args) throws IOException, SQLException {		
		System.out.println(System.getProperty("user.dir"));;
		
	}
}