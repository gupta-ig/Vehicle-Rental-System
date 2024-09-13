package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReviewModelTest {

	private Review review;
	
	@BeforeEach
	public void setup() {
		review = new Review(
				UUID.randomUUID().toString().toString(),
				"Ishag123",
				new Timestamp(System.currentTimeMillis()),
				4,
				"Vehicle rented was well maintained");
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(review.getReviewId());
		assertEquals("Ishag123", review.getCustomerId());
		assertNotNull(review.getCreationDate());
		assertEquals(4, review.getRating());
		assertTrue(review.getDesciption().contains("maintained"));
	}
	
	@Test
	public void testGettersAndSetter() {
		review.setReviewId();
		review.setCustomerId("Aman234");
		review.setCreationDate(new Timestamp(System.currentTimeMillis()));
		review.setRating(3);
		review.setDesciption("Vehicle is not in good condition");
		
		assertNotNull(review.getReviewId());
		assertEquals("Aman234", review.getCustomerId());
		assertNotNull(review.getCreationDate());
		assertEquals(3, review.getRating());
		assertTrue(review.getDesciption().contains("good condition"));
	}
	
	@Test
	public void testToString() {
		String reviewString = review.toString();
		
		assertTrue(reviewString.contains("Ishag123"));
		assertTrue(reviewString.contains("maintained"));
	}
}
