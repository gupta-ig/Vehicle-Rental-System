package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wg.model.enums.BookingStatus;

public class BookingModelTest {

	private Booking booking;
	
	@BeforeEach
	public void setup() {
		booking = new Booking(
				UUID.randomUUID().toString(),
				"Ishag233",
				"Tata233",
				new Timestamp(System.currentTimeMillis()),
				new Timestamp(System.currentTimeMillis() + 1000),
				new Timestamp(System.currentTimeMillis() + 1000),
				BookingStatus.BOOKED,
				new Timestamp(System.currentTimeMillis())
				);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(booking.getBookingId());
		assertEquals("Ishag233", booking.getCustomerId());
		assertEquals("Tata233", booking.getVehicleId());
		assertNotNull(booking.getBookingStartTime());
		assertNotNull(booking.getBookingEndTime());
		assertNotNull(booking.getVehicleReturnTime());
		assertEquals("BOOKED", booking.getBookingStatus().name());
		assertNotNull(booking.getCreatedAt());
	}
	
	@Test
	public void testGettersAndSetters() {
		booking.setBookingId();
		booking.setCustomerId("okm123456");
		booking.setVehicleId("veh12321");
		booking.setBookingStartTime(new Timestamp(System.currentTimeMillis()));
		booking.setBookingEndTime(new Timestamp(System.currentTimeMillis() + 100000));
		booking.setVehicleReturnTime(new Timestamp(System.currentTimeMillis() + 100000));
		booking.setBookingStatus(BookingStatus.COMPLETED);
		booking.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		
		assertNotNull(booking.getBookingId());
		assertEquals("okm123456", booking.getCustomerId());
		assertEquals("veh12321", booking.getVehicleId());
		assertNotNull(booking.getBookingStartTime());
		assertNotNull(booking.getBookingEndTime());
		assertNotNull(booking.getVehicleReturnTime());
		assertEquals("COMPLETED", booking.getBookingStatus().name());
		assertNotNull(booking.getCreatedAt());
	}
	
	@Test
	public void testToString() {
		String bookingString = booking.toString();
		
		assertTrue(bookingString.contains("Tata233"));
		assertTrue(bookingString.contains("Ishag233"));
	}
}
