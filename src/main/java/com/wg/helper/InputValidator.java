package com.wg.helper;

import java.util.regex.Pattern;

public class InputValidator {
	
	// Regular expression patterns
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    
    public static boolean isNameValid(String name) {
        return name != null && !name.trim().isEmpty() ;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber != null && PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches() && phoneNumber.length()==10; 
    }

    public static boolean isEmailValid(String userEmail) {
        return userEmail != null && EMAIL_PATTERN.matcher(userEmail).matches();
    }

	public static boolean isRegistrationNumberValid(String regNo) {
		return regNo != null && !regNo.isEmpty();
	}
}
