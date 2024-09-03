package com.wg.controller;

import java.sql.SQLException;
import java.util.List;

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
            System.out.println("Notification sent successfully.");
        } 
		catch (SQLException e) {
            System.err.println("Error sending notification: " + e.getMessage());
        }
	}
	
	public void viewNotifications(String userId) throws SQLException {
		try {
            List<Notification> notifications = notifService.getNotificationsForUser(userId);
            notifications.forEach(notification -> System.out.println(notification.getNotificationMessage()));
        } 
		catch (SQLException e) {
            System.err.println("Error retrieving notifications: " + e.getMessage());
        }
    }
}
