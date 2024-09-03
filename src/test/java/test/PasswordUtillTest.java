package test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.wg.helper.PasswordUtil;

class PasswordUtillTest {

	@Test
	void testValidPassword() {
		assertTrue(PasswordUtil.isPasswordValid("Valid!123"));
	}

	@Test
	void testShortPassword() {
		assertFalse(PasswordUtil.isPasswordValid("Val!12"));
	}
	
	@Test
	void testPasswordWithoutDigit() {
		assertFalse(PasswordUtil.isPasswordValid("Valid!"));
	}
	
	@Test
	void testPasswordWithoutUppercase() {
		assertFalse(PasswordUtil.isPasswordValid("valid!123"));
	}
	
	@Test
	void testPasswordWithoutLowercase() {
		assertFalse(PasswordUtil.isPasswordValid("valid!123"));
	}
	
	@Test
	void testPasswordWithoutSpecialCharacter() {
		assertFalse(PasswordUtil.isPasswordValid("Valid123"));
	}
	
	@Test
	void testNullPassword() {
		assertFalse(PasswordUtil.isPasswordValid(null));
	}
	
	@Test
	void testEmptyPassword() {
		assertFalse(PasswordUtil.isPasswordValid(""));
	}
}
