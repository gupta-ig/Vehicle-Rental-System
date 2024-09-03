package com.wg.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.wg.dao.NotificationDAO;
import com.wg.model.Notification;

public class NotificationService {

    private final NotificationDAO notificationDAO;

	public NotificationService(NotificationDAO notificationDAO) {
		super();
		this.notificationDAO = notificationDAO;
	}

	public void sendNotification(String userId, String message) throws SQLException {
		Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setNotificationId();
        notification.setNotificationMessage(message);
        notification.setNotificationDate(new Timestamp(System.currentTimeMillis()));
        notificationDAO.add(notification);
		
	}

	public List<Notification> getNotificationsForUser(String userId) throws SQLException {
		return notificationDAO.getById(userId);
	}

}
