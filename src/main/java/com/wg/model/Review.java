package com.wg.model;

import java.sql.Timestamp;
import java.util.UUID;


public class Review {
	private String reviewId;
	private String customerId;
	private Timestamp CreationDate;
	private int rating;
	private String desciption;
	
	public Review(String reviewId, String customerId, Timestamp creationDate, int rating, String desciption) {
		super();
		this.reviewId = (reviewId == null) ? UUID.randomUUID().toString() : reviewId;
		this.customerId = customerId;
		CreationDate = creationDate;
		this.rating = rating;
		this.desciption = desciption;
	}
	
	public Review() {
		
	}

	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId() {
		this.reviewId = UUID.randomUUID().toString();
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Timestamp getCreationDate() {
		return CreationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		CreationDate = creationDate;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + 
				", customerId=" + customerId +
				", CreationDate=" + CreationDate + 
				", rating=" + rating + 
				", desciption=" + desciption + "]";
	}
	
}
