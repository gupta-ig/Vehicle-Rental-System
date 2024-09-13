package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wg.model.enums.PaymentMethod;

public class PaymentModelTest {

	private Payment payment;
	
	@BeforeEach
	public void setUp() {
		payment = new Payment(
				"123",
				"booking123",
				new BigDecimal(2000.00),
				new BigDecimal(00.00),
				new Timestamp(System.currentTimeMillis()),
				PaymentMethod.CARD);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(payment.getPaymentId());
		assertEquals("booking123", payment.getBookingId());
		assertEquals(new BigDecimal(2000.00), payment.getAmountPaid());
		assertEquals(new BigDecimal(00.00), payment.getFinePaid());
		assertNotNull(payment.getPaymentDate());
		assertEquals("CARD", payment.getPaymentMethod().name());
	}
	
	@Test
	public void testGettersAndSetters() {
		payment.setPaymentId();
		payment.setBookingId("booking456");
		payment.setAmountPaid(new BigDecimal(2500.0));
		payment.setFinePaid(new BigDecimal(00.0));
		payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
		payment.setPaymentMethod(PaymentMethod.CASH);

		assertNotNull(payment.getPaymentId());
		assertEquals("booking456", payment.getBookingId());
		assertEquals(new BigDecimal(2500.00), payment.getAmountPaid());
		assertEquals(new BigDecimal(00.00), payment.getFinePaid());
		assertNotNull(payment.getPaymentDate());
		assertEquals("CASH", payment.getPaymentMethod().name());	
	}
	
	@Test
	public void testToString() {
		String paymentString = payment.toString();
		
		assertNotNull(paymentString);
		assertTrue(paymentString.contains("booking123"));
		assertFalse(paymentString.contains("UPI"));
	}
}
