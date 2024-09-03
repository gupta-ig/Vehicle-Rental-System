package com.wg.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wg.model.Notification;

public class NotificationDAO extends GenericDAO<Notification, String> {

	@Override
	protected String getTableName() {
		return "NOTIFICATION";
	}

	@Override
	protected Notification mapResultSetToEntity(ResultSet result) throws SQLException {
		return new Notification(
				result.getString("user_id"),
				result.getString("notification_id"),
				result.getTimestamp("notification_date"),
				result.getString("notification_message")
				);
	}

	@Override
	protected void setPreparedStatementForEntity(PreparedStatement stmt, Notification notif) throws SQLException {
		stmt.setString(1, notif.getUserId());
		stmt.setString(2, notif.getNotificationId());
		stmt.setTimestamp(3, notif.getNotificationDate());
		stmt.setString(4, notif.getNotificationMessage());
		
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "notification_id";
	}

	@Override
	protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String notificationId) throws SQLException {
		stmt.setString(1, notificationId);
	}

	@Override
	protected String getPlaceholders() {
		return "?, ?, ?, ?";
	}

	
}
