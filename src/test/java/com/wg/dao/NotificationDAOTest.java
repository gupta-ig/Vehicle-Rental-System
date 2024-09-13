package com.wg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wg.model.Notification;

public class NotificationDAOTest {
	
	@Mock
	private Connection connection;
	
	@Mock 
	private PreparedStatement stmt;
	
	@Mock
	private ResultSet resultSet;
	
	@InjectMocks
	private NotificationDAO notificationDAO;
	
	private Notification mockNotif;
	
	@BeforeEach
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		
		mockNotif = new Notification(
				"Ishag1234",
				"1234",
				new Timestamp(System.currentTimeMillis()),
				"The server is down today");
		
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
	}
	
	@Test
	public void testMapResultSetToEntity() throws SQLException {
		when(resultSet.getString("user_id")).thenReturn("Ishag1234");
		when(resultSet.getString("notification_id")).thenReturn("1234");
		when(resultSet.getTimestamp("notification_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(resultSet.getString("notification_message")).thenReturn("The server is down today");
		
		Notification notif = notificationDAO.mapResultSetToEntity(resultSet);
		
		assertNotNull(notif);
		assertEquals("Ishag1234", notif.getUserId());
		assertEquals("1234", notif.getNotificationId());
		assertNotNull(notif.getNotificationDate());
		assertTrue(notif.getNotificationMessage().endsWith("down today"));
	}
	
	@Test
	public void testSetPreparedStatementForEntity() throws SQLException {
		doNothing().when(stmt).setString(anyInt(), anyString());
		
		notificationDAO.setPreparedStatementForEntity(stmt, mockNotif);
		
		verify(stmt, times(3)).setString(anyInt(), anyString());
		verify(stmt, times(1)).setTimestamp(anyInt(), any(Timestamp.class));
	}
}
