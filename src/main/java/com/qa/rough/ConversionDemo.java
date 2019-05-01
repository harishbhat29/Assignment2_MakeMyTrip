package com.qa.rough;

public class ConversionDemo {
	public static void main(String[] args) {
		String price = "Rs 12,816";
		int p = Integer.parseInt(price.replaceAll("[ ,a-zA-Z]", ""));
		System.out.println(p);
	}

}
