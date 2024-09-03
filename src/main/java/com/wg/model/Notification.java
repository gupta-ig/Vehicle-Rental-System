package com.wg.model;

import java.sql.Timestamp;
import java.util.UUID;

public class Notification {
	private String notificationId;
    private String notificationMessage;
    private Timestamp notificationDate;
	private String userId;
    
	public String getNotificationId() {
		return notificationId;
	}
	public void setNotificationId() {
		this.notificationId = UUID.randomUUID().toString();
	}
	public String getNotificationMessage() {
		return notificationMessage;
	}
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	public Timestamp getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Timestamp notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

    public Notification(String userId, String notificationId,Timestamp notificationDate,  String notificationMessage) {
		super();
		this.notificationId = (notificationId == null) ? UUID.randomUUID().toString() : notificationId;
		this.notificationMessage = notificationMessage;
		this.notificationDate = notificationDate;
		this.userId = userId;
	}
	public Notification() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Notification [notificationId=" + notificationId + 
				", notificationMessage=" + notificationMessage + 
				", notificationDate=" + notificationDate + 
				", userId=" + userId + "]";
	}
}
