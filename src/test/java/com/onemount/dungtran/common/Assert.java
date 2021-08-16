package com.onemount.dungtran.common;

public class Assert {
	
	public static void assertContains(String actualText, String expectText, String message) {
		if (actualText.contains(expectText)) {
			System.out.println(message + "PASSED");
		} else {
			System.out.println(message + "FAILED \n\t Don't go to the expected page.");
		}
	}
	public static void assertEquals(String actualText, String expectText, String message) {
		if ( actualText.equals(expectText)) {
			System.out.println(message + "PASSED");
		} else {
			System.out.println(message + "FAILED \n\t Expect is \"" + expectText + "\" but actual is \"" + actualText +"\"");
		}
	}
}
