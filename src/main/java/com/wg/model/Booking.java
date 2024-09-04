package com.wg.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.wg.model.enums.BookingStatus;


public class Booking {
	private String bookingId;
    private String customerId;
    private String vehicleId;
    private Timestamp bookingStartTime;
    private Timestamp bookingEndTime;
    private Timestamp vehicleReturnTime;
    private BookingStatus bookingStatus;
    private Timestamp createdAt;
    
	public Booking(String bookingId, String customerId, String vehicleId, Timestamp bookingStartTime,
			Timestamp bookingEndTime,Timestamp vehicleReturnTime, BookingStatus bookingStatus, Timestamp createdAt) {
		super();
		this.bookingId = (bookingId == null) ? UUID.randomUUID().toString() : bookingId;
		this.customerId = customerId;
		this.vehicleId = vehicleId;
		this.bookingStartTime = bookingStartTime;
		this.bookingEndTime = bookingEndTime;
		this.vehicleReturnTime = vehicleReturnTime;
		this.bookingStatus = bookingStatus;
		this.createdAt = createdAt;
	}
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId() {
		this.bookingId = UUID.randomUUID().toString();
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Timestamp getBookingStartTime() {
		return bookingStartTime;
	}
	public void setBookingStartTime(Timestamp bookingStartTime) {
		this.bookingStartTime = bookingStartTime;
	}
	public Timestamp getBookingEndTime() {
		return bookingEndTime;
	}
	public void setBookingEndTime(Timestamp bookingEndTime) {
		this.bookingEndTime = bookingEndTime;
	}
	public Timestamp getVehicleReturnTime() {
		return vehicleReturnTime;
	}
	public void setVehicleReturnTime(Timestamp vehicleReturnTime) {
		this.vehicleReturnTime = vehicleReturnTime;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = new Timestamp(System.currentTimeMillis());
	}
	
	public Booking() {
		super();
	}
	
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId +
				", customerId=" + customerId + 
				", vehicleId=" + vehicleId +
				", bookingStartTime=" + bookingStartTime +
				", bookingEndTime=" + bookingEndTime + 
				", bookingEndTime=" + vehicleReturnTime + 
				", vehicleReturnTime=" + bookingStartTime +
				", bookingEndTime=" + bookingEndTime + 
				", createdAt=" + createdAt + "]";
	}
	
}

