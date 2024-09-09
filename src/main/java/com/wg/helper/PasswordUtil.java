package com.wg.helper;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
 
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
 
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    
//    public static String getPasswordFromUser() {
//        JPasswordField passwordField = new JPasswordField(20);
//        int option = JOptionPane.showConfirmDialog(null, passwordField, "Enter your password",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//
//        if (option == JOptionPane.OK_OPTION) {
//            char[] passwordChars = passwordField.getPassword();
//            return new String(passwordChars);
//        } else {
//            return null; // User cancelled the operation
//        }
//    }
    
    public static boolean isPasswordValid(String password) {
		if(password == null || password.isEmpty()) {
			return false;
		}
		
		// Check length (e.g. password should be atleast 12 characters
		if(password.length() < 12) {
			return false;
		}
		
		//Check if it contains atleast one digit
		if(!password.matches(".*\\d.*")) {
			return false;
		}
		
		//Check if it contains atleast one lowercase letter
		if(!password.matches(".*[a-z].*")) {
			return false;
		}
		
		// Check if it contains atleast one uppercase letter
		if(!password.matches(".*[A-Z].*")) {
			return false;
		}
		
		// Check if it contains atleast one special character
		if(!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
			return false;
		}
		
		// If all checks pass, the password is valid
		return true;
	}
}