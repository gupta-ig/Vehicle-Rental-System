package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.wg.helper.InputValidator;
import com.wg.model.User;

class UserTest {

	private User user = new User();
	
	@Test
	void testPhoneNumberLength() {
		assertEquals(10, user.getPhoneNumber().length());
	}
	
	@Test
	void testCharactersInPhoneNumber() {
		assertFalse(InputValidator.isPhoneNumberValid("abc1234567"));
	}

	@Test
	void isUserEmailValid() {
		assertEquals(true, user.getUserEmail().endsWith("@gmail.com"));
	}
	
	@Test
	void isUserNameStartsWithUpperCase() {
		assertEquals(true, Character.isUpperCase(user.getFirstName().charAt(0)));
	}

}
