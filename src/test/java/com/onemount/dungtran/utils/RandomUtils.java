package com.onemount.dungtran.utils;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {
	
	static int number = new Random().nextInt();
	public static String randomEmail() {	
		return String.format("%s@gmail.com",randomText(10));
	}
	public static String randomPassword(int length) {
		return String.format(randomText(length)+"%d",Math.abs(number));
	}
	public static String randomText(int length) {
		return RandomStringUtils.randomAlphabetic(length);
	}
	
}
