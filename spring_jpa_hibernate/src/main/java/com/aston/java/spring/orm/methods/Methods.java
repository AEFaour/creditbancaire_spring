package com.aston.java.spring.orm.methods;

import java.time.LocalDate;
import java.time.Period;

public class Methods {
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isBlank();
	};

	public static String capitalizeFirstCase(String str) {
		return str.toUpperCase().substring(0, 1) + str.toLowerCase().substring(1);
	}

	public static int calculeAgeNow(LocalDate birthday) {
		LocalDate now = LocalDate.now();
		return Period.between(birthday, now).getYears();
	}

}
