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
import static org.mockito.Mockito.*;

import com.wg.model.Review;

public class ReviewDAOTest {

	@Mock
	private Connection connection;
	
	@Mock 
	private PreparedStatement stmt;
	
	@Mock
	private ResultSet resultSet;
	
	@InjectMocks
	private ReviewDAO reviewDAO;
	
	private Review mockReview;

	@BeforeEach
	public void setup() throws SQLException {
		MockitoAnnotations.openMocks(this);
		
		mockReview = new Review(
				"12345",
				"cust12345",
				new Timestamp(System.currentTimeMillis()),
				5,
				"The vehicle needs service");
		
		when(connection.prepareStatement(anyString())).thenReturn(stmt);
	}
	
	@Test
	public void testMapResultSetToEntity() throws SQLException {
		when(resultSet.getString("review_id")).thenReturn("12345");
		when(resultSet.getString("customer_id")).thenReturn("cust12345");
		when(resultSet.getTimestamp("creation_date")).thenReturn(new Timestamp(System.currentTimeMillis()));
		when(resultSet.getInt("rating")).thenReturn(5);
		when(resultSet.getString("review_description")).thenReturn("The vehicle needs service");
		
		Review review = reviewDAO.mapResultSetToEntity(resultSet);
		
		assertNotNull(review.getReviewId());
		assertEquals("cust12345", review.getCustomerId());
		assertNotNull(review.getCreationDate());
		assertEquals(5, review.getRating());
		assertTrue(review.getDesciption().endsWith("needs service"));
	}
	
	@Test
	public void testPreparedStatementForEntity() throws SQLException {
		doNothing().when(stmt).setString(anyInt(), anyString());
		
		reviewDAO.setPreparedStatementForEntity(stmt, mockReview);
		
		verify(stmt, times(3)).setString(anyInt(), anyString());
		verify(stmt, times(1)).setTimestamp(anyInt(), any(Timestamp.class));
		verify(stmt, times(1)).setInt(anyInt(), anyInt());
	} 
}
