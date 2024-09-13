package com.wg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wg.dao.BookingDAO;
import com.wg.dao.VehicleDAO;
import com.wg.model.Booking;
import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.BookingStatus;

public class BookingServiceTest {

    @Mock
    private BookingDAO bookingDAO;

    @Mock
    private VehicleDAO vehicleDAO;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBookVehicleSuccess() throws SQLException {
        Booking booking = new Booking("bookingId", "vehicleId", "userId", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), BookingStatus.BOOKED, new Timestamp(System.currentTimeMillis()));

        when(bookingDAO.getById("bookingId")).thenReturn(Collections.emptyList());

        bookingService.bookVehicle(booking);

        verify(bookingDAO, times(1)).add(booking);
    }

    @Test
    public void testGetAllBookings() throws SQLException {
        String userId = "userId";
        List<Booking> bookings = Collections.singletonList(new Booking("bookingId", userId, "vehicleId", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), BookingStatus.BOOKED, new Timestamp(System.currentTimeMillis())));

        when(bookingDAO.getById(userId)).thenReturn(bookings);

        List<Booking> result = bookingService.getAllBookings(userId);

        verify(bookingDAO, times(1)).getById(userId);
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getCustomerId());
    }

    @Test
    public void testIsVehicleAvailable() throws SQLException {
        String vehicleId = "vehicleId";
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        Timestamp endTime = new Timestamp(System.currentTimeMillis() + 3600 * 1000);

        when(bookingDAO.getBookingsForVehicleWithinTimeRange(vehicleId, startTime, endTime)).thenReturn(Collections.emptyList());

        boolean available = bookingService.isVehicleAvailable(vehicleId, startTime, endTime);

        assertTrue(available);
    }

    @Test
    public void testCancelBooking() throws SQLException {
        bookingService.cancelBooking("bookingId", "vehicleId");

        verify(bookingDAO, times(1)).cancelBooking("bookingId");
        verify(vehicleDAO, times(1)).updateStatusQuery("vehicleId", AvailabilityStatus.AVAILABLE);
    }

    @Test
    public void testReturnVehicle() throws SQLException {
        String bookingId = "bookingId";
        Timestamp returnTime = new Timestamp(System.currentTimeMillis());

        bookingService.returnVehicle(bookingId, returnTime);

        verify(bookingDAO, times(1)).returnVehicle(bookingId, returnTime);
    }
}
