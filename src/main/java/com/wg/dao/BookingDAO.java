package com.wg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.wg.config.DatabaseConfig;
import com.wg.model.Booking;
import com.wg.model.enums.BookingStatus;

public class BookingDAO extends GenericDAO<Booking, String>{

	@Override
	protected String getTableName() {
		return "BOOKING";
	}

	@Override
	protected Booking mapResultSetToEntity(ResultSet result) throws SQLException {
		return new Booking(
				result.getString("booking_id"),
				result.getString("customer_id"),
				result.getString("vehicle_id"),
				result.getTimestamp("booking_start_time"),
				result.getTimestamp("booking_end_time"),
				BookingStatus.valueOf(result.getString("booking_status")),
				result.getTimestamp("created_at"));
	}

	@Override
	protected void setPreparedStatementForEntity(PreparedStatement stmt, Booking booking) throws SQLException {
		stmt.setString(1, booking.getBookingId());
		stmt.setString(2, booking.getCustomerId());
		stmt.setString(3, booking.getVehicleId());
		stmt.setTimestamp(4, booking.getBookingStartTime());
		stmt.setTimestamp(5, booking.getBookingEndTime());
		stmt.setString(6, booking.getBookingStatus().name());
		stmt.setTimestamp(7, booking.getCreatedAt());
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "customer_id";
	}

	@Override
	protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String bookingId) throws SQLException {
		stmt.setString(1, bookingId);
		
	}

	@Override
	protected String getPlaceholders() {
		return "?, ?, ?, ?, ?, ?, ?";
	}

	public List<Booking> getBookingsForVehicleWithinTimeRange(String vehicleId, Timestamp startTime, Timestamp endTime) throws SQLException {
        String query = "SELECT * FROM booking WHERE vehicle_id = ? AND NOT (booking_end_time <= ? OR booking_start_time >= ?)";
        
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, vehicleId);
            preparedStatement.setTimestamp(2, startTime);
            preparedStatement.setTimestamp(3, endTime);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Booking> bookings = new ArrayList<>();
            
            while (resultSet.next()) {
                Booking booking = mapResultSetToEntity(resultSet);
                
                bookings.add(booking);
            }
            return bookings;
        }
    }
	
	 public void cancelBooking(String bookingId) throws SQLException {
	        String UPDATE_QUERY = "UPDATE BOOKING SET booking_status = ? WHERE booking_id = ?";
	        System.out.println(UPDATE_QUERY);
	        System.out.println(bookingId);
	        try (Connection connection = DatabaseConfig.getConnection();
	             PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)) {

	            stmt.setString(1, BookingStatus.CANCELED.name());
	            stmt.setString(2, bookingId);

	            stmt.executeUpdate();
	        }
	    }
}
