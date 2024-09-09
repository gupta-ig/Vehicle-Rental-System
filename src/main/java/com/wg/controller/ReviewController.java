package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import com.wg.app.App;
import com.wg.dao.NotificationDAO;
import com.wg.helper.LoggingUtil;
import com.wg.helper.ReviewPrinter;
import com.wg.helper.StringConstants;
import com.wg.model.Review;
import com.wg.model.User;
import com.wg.service.NotificationService;
import com.wg.service.ReviewService;

public class ReviewController {
	private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    private static final Logger logger = LoggingUtil.getLogger(ReviewController.class);
    
    NotificationDAO notificationDAO = new NotificationDAO();
    NotificationService notificationService = new NotificationService(notificationDAO);
    NotificationController notificationController = new NotificationController(notificationService);

    // Add a Review
    public void addReview(User user) {
    	System.out.println(StringConstants.GIVE_A_REVIEW);
    	
    	try {
    		int rating;
    		while(true) {
    			System.out.println(StringConstants.ENTER_RATING);
                rating = App.scanner.nextInt();
                if(rating >= 1 && rating <= 5) {
                	break;
                }
                else {
                	System.out.println(StringConstants.PLEASE_ENTER_A_VALID_NUMBER);
                	continue;
                }
    		}
            
            App.scanner.nextLine();
            
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
            
            System.out.println(StringConstants.YOUR_REVIEW_DESCRIPTION);
            String description = App.scanner.nextLine();
            
        	Review newReview = new Review();
        	newReview.setReviewId();
        	newReview.setCustomerId(user.getUserId());
        	newReview.setCreationDate(createdAt);
        	newReview.setRating(rating);
        	newReview.setDesciption(description);
    		
            reviewService.addReview(newReview);
            
            // Send notification after review is added
            notificationController.sendNotification(user.getUserId(), StringConstants.REVIEW_ADDED_SUCCESSFULLY);
            logger.info(StringConstants.REVIEW_ADDED_SUCCESSFULLY);
        }
        catch (SQLException e) {
            System.out.println(StringConstants.ERROR_ADDING_REVIEW + e.getMessage());
        }
    }

    // Delete a review
    public void deleteReview() {
    	try {
        	List<Review> reviews = viewAllReviews();
    		
        	if(reviews == null) {
        		System.out.println("No reviews to be deleted.");
        		return;
        	}
        	
    		int choice;
    		choice = App.scanner.nextInt();
 
    		int index;
    		while(true) {
    			System.out.print(StringConstants.ENTER_REVIEW_SR_NO_TO_DELETE);
        		index = App.scanner.nextInt();
        		if(index > 0 && index <= reviews.size()) {
        			break;
        		}
        		else {
        			System.out.println(StringConstants.PLEASE_ENTER_VALID_INDEX);
        		}
    		}
    		
            reviewService.deleteReview(reviews.get(choice - 1).getReviewId());
            System.out.println(StringConstants.REVIEW_DELETED_SUCCESSFULLY);
            
        }
        catch (SQLException e) {
            System.err.println(StringConstants.ERROR_WHILE_DELETING_REVIEW + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
        }
    }

    // View All Reviews
    public List<Review> viewAllReviews() {
    	try {
    		List<Review> reviews = reviewService.getAllReviews();
    		if(reviews.isEmpty()) {
    			System.out.println(StringConstants.NO_REVIEWS_PUBLISHED);
    			return null;
    		}
    		else {
    			System.out.println(StringConstants.LIST_OF_ALL_THE_REVIEWS);
        		ReviewPrinter.printReviews(reviews);
        		return reviews;
    		}
    	}
    	catch (Exception e) {
    		System.out.println(StringConstants.ERROR_RETRIEVING_REVIEWS + e.getMessage());
    		return null;
    	}
    }
    
    public void getReviewById(String userId) {
    	System.out.println(StringConstants.SEE_ALL_REVIEWS);
    	
        try {
        	List<Review> review = reviewService.getReviewById(userId);
            if (review != null) {
                ReviewPrinter.printReviews(review);
            } else {
                System.out.println("No reviews published by you.");
            }
        } catch (SQLException e) {
            System.out.println(StringConstants.ERROR_RETRIEVING_REVIEWS + e.getMessage());
        }
    }
}
