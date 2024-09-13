package com.wg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.wg.model.Booking;
import com.wg.model.enums.BookingStatus;

public class BookingDAOTest {

	@Mock
	private Connection connection;
	
	@Mock 
	private PreparedStatement stmt;
	
	@Mock
	private ResultSet resultSet;
	
	@InjectMocks
	private BookingDAO bookingDAO;
	
	private Booking mockBooking;
	
	@BeforeEach
	public void setUp() throws SQLException {
		MockitoAnnotations.openMocks(this);
		mockBooking = new Booking(
				"123",
				"cust123",
				"veh123",
				new Timestamp(System.currentTimeMillis()),
				new Timestamp(System.currentTimeMillis() + 1000),
				new Timestamp(System.currentTimeMillis() + 1000),
				BookingStatus.BOOKED,
				new Timestamp(System.currentTimeMillis())
				);
		
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
	}
	
	@Test
	public void testMapResultSetToEntity() throws SQLException {
		when(resultSet.getString("booking_id")).thenReturn("123");
		when(resultSet.getString("customer_id")).thenReturn("cust123");
		when(resultSet.getString("vehicle_id")).thenReturn("veh123");
		when(resultSet.getTimestamp("booking_start_time")).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(resultSet.getTimestamp("booking_end_time")).thenReturn(new Timestamp(System.currentTimeMillis() + 1000));
		when(resultSet.getTimestamp("vehicle_return_time")).thenReturn(new Timestamp(System.currentTimeMillis() + 1000));
		when(resultSet.getString("booking_status")).thenReturn(BookingStatus.BOOKED.name());
		when(resultSet.getTimestamp("created_at")).thenReturn(new Timestamp(System.currentTimeMillis()));
		
		Booking booking = bookingDAO.mapResultSetToEntity(resultSet);
		
		assertNotNull(booking);
		assertEquals("123", booking.getBookingId());
		assertEquals("cust123", booking.getCustomerId());
		assertEquals("veh123", booking.getVehicleId());
		assertNotNull(booking.getBookingStartTime());
		assertNotNull(booking.getBookingEndTime());
		assertNotNull(booking.getVehicleReturnTime());
		assertEquals("BOOKED", booking.getBookingStatus().name());
	}
	
	@Test
	public void testSetPreparedStatementForEntity() throws SQLException {
		doNothing().when(stmt).setString(anyInt(), anyString());
		
		bookingDAO.setPreparedStatementForEntity(stmt, mockBooking);
		
		verify(stmt, times(4)).setString(anyInt(), anyString());
		verify(stmt, times(4)).setTimestamp(anyInt(), any(Timestamp.class));
	}
	
	@Test
	public void testGetBookingsForVehicleWithinTimeRange() throws SQLException {
		when(stmt.executeQuery()).thenReturn(resultSet);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		
		when(resultSet.getString("vehicle_id")).thenReturn("veh123");
		when(resultSet.getTimestamp("booking_start_time")).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(resultSet.getTimestamp("booking_end_time")).thenReturn(new Timestamp(System.currentTimeMillis() + 1000));
		when(resultSet.getString("booking_status")).thenReturn(BookingStatus.BOOKED.name());
		
		List<Booking> bookings = new ArrayList<>();
		bookings.add(mockBooking);
		
		List<Booking> result = bookingDAO.getBookingsForVehicleWithinTimeRange("veh123", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 1000));
		
		assertNotNull(result);
		assertEquals("veh123", result.get(0).getVehicleId());
		assertNotNull(result.get(0).getBookingStartTime());
		assertNotNull(result.get(0).getBookingEndTime());
	}
	
	@Test
	public void testCancelBooking() throws SQLException {
		when(stmt.executeUpdate()).thenReturn(1);
		
		bookingDAO.cancelBooking("123");
		
		verify(stmt).setString(1, BookingStatus.CANCELED.name());
		verify(stmt).setString(2, "123");
		verify(stmt).executeUpdate();
	}
	
	@Test
	public void testReturnVehicle() throws SQLException {
		Timestamp returnTime = new Timestamp(System.currentTimeMillis() + 1000);
		when(stmt.executeUpdate()).thenReturn(1);
		
		bookingDAO.returnVehicle("123", returnTime);
		
		verify(stmt).setTimestamp(1, returnTime);
		verify(stmt).setString(2, "123");
		verify(stmt).executeUpdate();
	}
}
