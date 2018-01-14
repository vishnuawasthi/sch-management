package com.sch.mngt.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class UserValidationUtils {
	
	public static String generateAccessKey() {
		int length = 10;
		boolean useLetters = true;
		boolean useNumbers = false;
		return RandomStringUtils.random(length, useLetters, useNumbers);
	}

	public static void main(String... strings) {
		System.out.println(" generateAccessKey()  => " + generateAccessKey());
	}

}
