package com.wg.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wg.dao.ReviewDAO;
import com.wg.model.Review;

public class ReviewServiceTest {

	@Mock
	private ReviewDAO reviewDAO; 
	
	@InjectMocks
	private ReviewService reviewService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	Review newReview = new Review("123", "Ishag123", new Timestamp(System.currentTimeMillis()), 4, "Service is amazing");
	
	@Test
	public void testAddReview() throws SQLException {
		reviewService.addReview(newReview);
		verify(reviewDAO, times(1)).add(newReview);
	}
	
	@Test
	public void testDeleteReview() throws SQLException {
		reviewService.deleteReview("123");
		verify(reviewDAO, times(1)).delete("123");
	}
	
	@Test
	public void testGetReviewById() throws SQLException {
		when(reviewDAO.getById("Ishag123")).thenReturn(Arrays.asList(newReview));
		
		List<Review> result = reviewService.getReviewById("Ishag123");
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Ishag123", result.get(0).getCustomerId());
	}
	
	@Test
	public void testGetAllReviews() throws SQLException {
		Review newReview2 = new Review("345", "Aman345", new Timestamp(System.currentTimeMillis()), 5, "Service is fabulous");
		
		when(reviewDAO.getAll()).thenReturn(Arrays.asList(newReview, newReview2));
		
		List<Review> result = reviewService.getAllReviews();
		
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Ishag123", result.get(0).getCustomerId());
		assertEquals("Aman345", result.get(1).getCustomerId());
	}
}

