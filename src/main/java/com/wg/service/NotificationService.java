package com.wg.service;

import java.sql.SQLException;
import java.util.List;

import com.wg.dao.NotificationDAO;
import com.wg.model.Notification;

public class NotificationService {

    private NotificationDAO notificationDAO;

	public NotificationService(NotificationDAO notificationDAO) {
		super();
		this.notificationDAO = notificationDAO;
	}

	public void sendNotification(Notification notification) throws SQLException {
        notificationDAO.add(notification);
		
	}

	public List<Notification> getNotificationsByUser(String userId) throws SQLException {
		return notificationDAO.getById(userId);
	}

}
