package com.wg.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.wg.dao.BookingDAO;
import com.wg.dao.VehicleDAO;
import com.wg.model.Booking;
import com.wg.model.enums.AvailabilityStatus;
public class BookingService {
	
	BookingDAO bookingDAO;
	VehicleDAO vehicleDAO;
	
	public BookingService(BookingDAO bookingDAO, VehicleDAO vehicleDAO) {
		this.bookingDAO = bookingDAO;
		this.vehicleDAO = vehicleDAO;
	} 
	
	
	public void bookVehicle(Booking booking) throws SQLException {
		List<Booking> existingBooking = bookingDAO.getById(booking.getBookingId());
		if(existingBooking == null) {
			throw new IllegalArgumentException("Booking already exist.");
		}
        bookingDAO.add(booking);
    } 
	
	public List<Booking> getAllBookings(String userId) throws SQLException {
		return bookingDAO.getById(userId);
	}
	
	public boolean isVehicleAvailable(String vehicleId, Timestamp startTime, Timestamp endTime) throws SQLException {
        return bookingDAO.getBookingsForVehicleWithinTimeRange(vehicleId, startTime, endTime).isEmpty();
    }
	
	public void cancelBooking(String bookingId, String vehicleId) throws SQLException {
        bookingDAO.cancelBooking(bookingId);
		vehicleDAO.updateStatusQuery(vehicleId, AvailabilityStatus.AVAILABLE);
    }
	
	public void returnVehicle(String bookingId, Timestamp returnTime) throws SQLException {
        bookingDAO.returnVehicle(bookingId, returnTime);
    }
}
