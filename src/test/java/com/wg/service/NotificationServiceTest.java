package com.wg.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wg.dao.NotificationDAO;
import com.wg.model.Notification;

public class NotificationServiceTest {

	@Mock
	private NotificationDAO notificationDAO;
	
	@InjectMocks
	private NotificationService notificationService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	Notification notif = new Notification("12", "Service id Down", new Timestamp(System.currentTimeMillis()), "Ishag123");
	
	@Test
	public void testSendNotifications() throws SQLException {
		notificationService.sendNotification(notif);
		verify(notificationDAO, times(1)).add(notif);
	}
	
	@Test
	public void testGetNotificationByUserId() throws SQLException {
		List<Notification> expectedNotifications = notificationService.getNotificationsByUser("Ishag123");
		assertEquals(expectedNotifications, notificationDAO.getById("Ishag123"));
	}
	
}
