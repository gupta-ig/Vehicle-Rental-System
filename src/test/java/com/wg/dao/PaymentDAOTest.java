package com.wg.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
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

import com.wg.model.Payment;
import com.wg.model.enums.PaymentMethod;

public class PaymentDAOTest {
	
	@Mock
	private Connection connection;
	
	@Mock 
	private PreparedStatement stmt;
	
	@Mock
	private ResultSet resultSet;
	
	@InjectMocks
	private PaymentDAO paymentDAO;
	
	private Payment mockPayment;

	@BeforeEach
	public void setup() throws SQLException {
		MockitoAnnotations.openMocks(this);
		
		mockPayment = new Payment(
				"12345",
				"booking12345",
				new BigDecimal(200.0),
				new BigDecimal(0.0),
				new Timestamp(System.currentTimeMillis()),
				PaymentMethod.CARD);
		
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
	}
	
	@Test
	public void testMapResultSetToEntity() throws SQLException {
		when(resultSet.getString("payment_id")).thenReturn("12345");
		when(resultSet.getString("booking_id")).thenReturn("booking12345");
		when(resultSet.getBigDecimal("amount_paid")).thenReturn(new BigDecimal(200.0));
		when(resultSet.getBigDecimal("fine")).thenReturn(new BigDecimal(0.0));
		when(resultSet.getTimestamp("payment_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(resultSet.getString("payment_method")).thenReturn(PaymentMethod.CARD.name());
		
		Payment payment = paymentDAO.mapResultSetToEntity(resultSet);
		
		assertNotNull(payment.getPaymentId());
		assertEquals("booking12345", payment.getBookingId());
		assertEquals(new BigDecimal(200.0), payment.getAmountPaid());
		assertEquals(new BigDecimal(0.0), payment.getFinePaid());
		assertNotNull(payment.getPaymentDate());
		assertEquals("CARD", payment.getPaymentMethod().name());
	}
	
	@Test
	public void testPreparedStatementForEntity() throws SQLException {
		doNothing().when(stmt).setString(anyInt(), anyString());
		
		paymentDAO.setPreparedStatementForEntity(stmt, mockPayment);
		
		verify(stmt, times(3)).setString(anyInt(), anyString());
		verify(stmt, times(1)).setTimestamp(anyInt(), any(Timestamp.class));
		verify(stmt, times(2)).setBigDecimal(anyInt(), any(BigDecimal.class));
	} 
}
