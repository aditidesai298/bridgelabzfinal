package com.bridgeLabz.basic;




import com.bridgeLabz.utility.Util;

public class leapyear {
	public static void main(String[] args) {
		System.out.println("Enter a year");
		int year = Util.scanner.nextInt();

		if (Util.isLeapYear(year) == true) {
			System.out.println(year + " is a leap year");
		} else {
			System.out.println(year + " not a leap year");
		}

	}

}