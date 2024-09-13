package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationModelTest {

	private Notification notification;
	
	@BeforeEach
	public void setup() {
		notification = new Notification(
				"Ishag123",
				UUID.randomUUID().toString(),
				new Timestamp(System.currentTimeMillis()),
				"Service is down"
				);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(notification.getNotificationId());
		assertEquals("Service is down", notification.getNotificationMessage());
		assertNotNull(notification.getNotificationDate());
		assertEquals("Ishag123", notification.getUserId());
	}
	
	@Test
	public void testGettersAndSetters() {
		notification.setNotificationId();
		notification.setNotificationMessage("Vehicle is booked successfully");
		notification.setNotificationDate(new Timestamp(System.currentTimeMillis()));
		notification.setUserId("Aman234");
		
		assertNotNull(notification.getNotificationId());
		assertTrue(notification.getNotificationMessage().contains("successfully"));
		assertNotNull(notification.getNotificationDate());
		assertEquals("Aman234", notification.getUserId());
	}
	
	@Test
	public void testToString() {
		String notifString = notification.toString();
		
		assertTrue(notifString.contains("Ishag123"));
		assertTrue(notifString.contains("Service"));
	}
}
