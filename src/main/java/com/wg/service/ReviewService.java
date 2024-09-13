package com.wg.service;

import java.sql.SQLException;
import java.util.List;

import com.wg.dao.ReviewDAO;
import com.wg.model.Review;

public class ReviewService {

    private ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public void addReview(Review review) throws SQLException {
        reviewDAO.add(review);
    }

    public void deleteReview(String reviewId) throws SQLException {
        reviewDAO.delete(reviewId);
    }

    public List<Review> getReviewById(String userId) throws SQLException {
        List<Review> reviews = reviewDAO.getById(userId);
        return reviews.isEmpty() ? null : reviews;
    }

    public List<Review> getAllReviews() throws SQLException {
        return reviewDAO.getAll();
    }
}

