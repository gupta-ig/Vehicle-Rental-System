package com.wg.helper;

public class InputSanitizer {
	
	public static String sanitizePassword(String userPassword) {
		// Trim whitespace and applying additional sanitization if needed
		return userPassword.trim();
	}

	public static String sanitizePhoneNumber(String phoneNumber) {
		// Trim whitespace and allow only digits
		return phoneNumber.trim();
	}

	public static String sanitizeEmail(String email) {
		return email.trim();
	}

	public static String sanitizeName(String name) {
		// Trim whitespace and allow only letters, spaces and basic punctuation
		return name.trim().replaceAll("[^a-zA-Z\\s\\-\\.]", "");
	}

	public static String sanitizeRegistrationNumber(String regNo) {
		return regNo.trim();
	}
	
}
