package com.wg.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wg.dao.PaymentDAO;
import com.wg.helper.BillingUtil;
import com.wg.model.Booking;
import com.wg.model.Payment;
import com.wg.model.enums.BookingStatus;
import com.wg.model.enums.PaymentMethod;

public class PaymentServiceTest {

	@Mock
	private PaymentDAO paymentDAO;
	
	@Mock
	private Booking booking;
	
	@Mock
	private BillingUtil billingUtil;
	
	@InjectMocks
	private PaymentService paymentService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testProcessPayment() throws SQLException {
		Booking booking = new Booking("456", "Ishag123", "veh123", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 10000), new Timestamp(System.currentTimeMillis() + 10000), BookingStatus.BOOKED, new Timestamp(System.currentTimeMillis()));
		Payment newPayment = new Payment("123", "booking123", new BigDecimal(200.00), new BigDecimal(0.00), new Timestamp(System.currentTimeMillis()), PaymentMethod.CASH);
		
		paymentService.processPayment(booking, newPayment);
		
		verify(paymentDAO, times(1)).add(newPayment);
	}

}
