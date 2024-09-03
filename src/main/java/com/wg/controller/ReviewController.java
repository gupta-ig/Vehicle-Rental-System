package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.wg.app.App;
import com.wg.dao.NotificationDAO;
import com.wg.helper.ComplaintPrinter;
import com.wg.helper.ReviewPrinter;
import com.wg.model.Complaint;
import com.wg.model.Review;
import com.wg.model.User;
import com.wg.service.NotificationService;
import com.wg.service.ReviewService;

public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    
    NotificationDAO notificationDAO = new NotificationDAO();
    NotificationService notificationService = new NotificationService(notificationDAO);
    NotificationController notificationController = new NotificationController(notificationService);

    // Add a Review
    public void addReview(User user) {
    	try {
    		int rating;
    		while(true) {
    			System.out.println("Enter rating (1-5): ");
                rating = App.scanner.nextInt();
                if(rating >= 1 && rating <= 5) {
                	break;
                }
                else {
                	System.out.println("Please enter a valid number.");
                	continue;
                }
    		}
            
            App.scanner.nextLine();
            
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
            
            System.out.println("Enter your review description: ");
            String description = App.scanner.nextLine();
            
        	Review newReview = new Review();
        	newReview.setReviewId();
        	newReview.setCustomerId(user.getUserId());
        	newReview.setCreationDate(createdAt);
        	newReview.setRating(rating);
        	newReview.setDesciption(description);
    		
            reviewService.addReview(newReview);
            
            // Send notification after review is added
            notificationController.sendNotification(user.getUserId(), "Review added successfully.");
        }
        catch (SQLException e) {
            System.out.println("Error adding review: " + e.getMessage());
        }
    }

    // Delete a review
    public void deleteReview() {
    	try {
        	
        	System.out.println("List of all the reviews: ");
    		List<Review> reviews = viewAllReviews();
    		
    		int choice;
    		System.out.print("Enter rating sr. No. to delete: ");
    		choice = App.scanner.nextInt();
 
            reviewService.deleteReview(reviews.get(choice - 1).getReviewId());
            System.out.println("Review deleted successfully!");
            
 
        }
        catch (SQLException e) {
            System.err.println("Error while deleting user: " + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        }
    }

    // View All Reviews
    public List<Review> viewAllReviews() {
    	try {
    		List<Review> reviews = reviewService.getAllReviews();
    		System.out.println("Getting all reviews");
    		ReviewPrinter.printReviews(reviews);
    		return reviews;
    	}
    	catch (Exception e) {
    		System.out.println("Error retrieving users: " + e.getMessage());
    		return null;
    	}
    }
    
    public void getReviewById(String userId) {
        try {
        	List<Review> review = reviewService.getReviewById(userId);
            if (review != null) {
                ReviewPrinter.printReviews(review);
            } else {
                System.out.println("No complaint found with the provided ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving review: " + e.getMessage());
        }
    }
}
