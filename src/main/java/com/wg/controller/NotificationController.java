package com.wg.controller;

import java.sql.SQLException;
import java.util.List;

import com.wg.helper.StringConstants;
import com.wg.model.Notification;
import com.wg.service.NotificationService;

public class NotificationController {
	private NotificationService notifService;

	public NotificationController(NotificationService notifService) {
		super();
		this.notifService = notifService;
	}
	
	public void sendNotification(String userId, String message) throws SQLException {
		try {
            notifService.sendNotification(userId, message);
            System.out.println(message);
//            System.out.println(StringConstants.NOTIFICATION_SENT_SUCCESSFULLY);
        } 
		catch (SQLException e) {
            System.err.println(StringConstants.ERROR_SENDING_NOTIFICATION + e.getMessage());
        }
	}
	
	public void viewNotifications(String userId) throws SQLException {
		try {
            List<Notification> notifications = notifService.getNotificationsForUser(userId);
            notifications.forEach(notification -> System.out.println(notification.getNotificationMessage()));
        } 
		catch (SQLException e) {
            System.err.println(StringConstants.ERROR_RETRIEVING_NOTIFICATIONS + e.getMessage());
        }
    }
}
