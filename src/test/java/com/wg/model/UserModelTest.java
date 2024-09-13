package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wg.model.enums.Gender;
import com.wg.model.enums.Role;

public class UserModelTest {

	private User user;
	
	@BeforeEach
	public void setup() {
		user = new User(
				UUID.randomUUID().toString(),
				"Raj",
				"Kapoor",
				"5678901234",
				"raj@gmail.com",
				Gender.MALE,
				Role.CUSTOMER,
				new Timestamp(System.currentTimeMillis()),
				"password123");
	}
	
	@Test
	public void testContructor() {
		assertNotNull(user.getUserId());
		assertEquals("Raj", user.getFirstName());
		assertEquals("Kapoor", user.getLastName());
		assertEquals("5678901234", user.getPhoneNumber());
		assertEquals("raj@gmail.com", user.getUserEmail());
		assertEquals("MALE", user.getGender().name());
		assertEquals("CUSTOMER", user.getRole().name());
		assertNotNull(user.getCreatedAt());
		assertEquals("password123", user.getPassword());
	}
	
	@Test
	public void testGettersAndSetters() {
		user.setUserId();
		user.setFirstName("Madhur");
		user.setLastName("Gupta");
		user.setPhoneNumber("6789054321");
		user.setUserEmail("madhur@gmail.com");
		user.setGender(Gender.MALE);
		user.setRole(Role.EMPLOYEE);
		user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		user.setPassword("password@#123");
		
		assertNotNull(user.getUserId());
		assertEquals("Madhur", user.getFirstName());
		assertEquals("Gupta", user.getLastName());
		assertEquals("6789054321", user.getPhoneNumber());
		assertEquals("madhur@gmail.com", user.getUserEmail());
		assertEquals("MALE", user.getGender().name());
		assertEquals("EMPLOYEE", user.getRole().name());
		assertNotNull(user.getCreatedAt());
		assertEquals("password@#123", user.getPassword());
	}
	
	@Test
	public void testToString() {
		String userString = user.toString();
		
		assertTrue(userString.contains("Kapoor"));
		assertTrue(userString.contains("raj@gmail.com"));
	}
}
