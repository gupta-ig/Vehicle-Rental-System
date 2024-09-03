package com.wg.presentation;

import java.sql.SQLException;

import com.wg.app.App;
import com.wg.controller.BookingController;
import com.wg.controller.ComplaintController;
import com.wg.controller.ReviewController;
import com.wg.dao.BookingDAO;
import com.wg.dao.ComplaintDAO;
import com.wg.dao.ReviewDAO;
import com.wg.helper.StringConstants;
import com.wg.model.User;
import com.wg.service.BookingService;
import com.wg.service.ComplaintService;
import com.wg.service.ReviewService;

public class CustomerMenu {

	public static void displayCustomerMenu(User user) throws SQLException {
		
		int choice;
		
		BookingDAO bookingDAO = new BookingDAO();
		BookingService bookingService = new BookingService(bookingDAO);
		BookingController bookingController = new BookingController(bookingService);
		
		ReviewDAO reviewDAO = new ReviewDAO();
	    ReviewService reviewService = new ReviewService(reviewDAO);
	    ReviewController reviewController = new ReviewController(reviewService);

	    ComplaintDAO complaintDAO = new ComplaintDAO();
	    ComplaintService complaintService = new ComplaintService(complaintDAO);
	    ComplaintController complaintController = new ComplaintController(complaintService);

		while(true) {
			System.out.println(StringConstants.CUSTOMER_MENU);
	        System.out.print("Enter your choice: ");
	        
	        choice = App.scanner.nextInt();
	        App.scanner.nextLine();
	        
	        switch(choice) {
	        case 1:
	        	System.out.println("Booking a Vehicle");
	        	bookingController.makeBooking(App.scanner, user.getUserId());
	        	break;
	        case 2:
	        	System.out.println("View booking history");
	        	bookingController.viewHistory(user.getUserId());
	        	break;
	        case 3:
	        	System.out.println("Give a review");
	        	reviewController.addReview(user);
	        	break;
	        case 4:
	        	System.out.println("See all reviews");
	        	reviewController.getReviewById(user.getUserId());
	        	break;
	        case 5:
	        	System.out.println("Raising a complaint");
	        	complaintController.raiseComplaint(user);
	        	break;
	        case 6:
	        	System.out.println("View all complaints");
	        	complaintController.viewComplaintById(user.getUserId());
	        	break;
	        case 7:
	        	System.out.println("Cancel Booking");
	        	bookingController.cancelBooking(user.getUserId());
	        	break;
	        case 8:
	        	System.out.println("Logging out...");
	        	
	        	while(true) {
	        		System.out.print("Do you want to continue? (Y/N): ");
		        	
			        String continueChoice = App.scanner.next();
			        if(continueChoice.equalsIgnoreCase("y")) {
			        	continue;
			        }
			        else if(continueChoice.equalsIgnoreCase("n")) {
			        	System.out.println("Thank you for visiting...");
			        	System.exit(0);
			        	break;
			        }
			        else {
			        	System.out.println("Please enter valid input.");
			        }
	        	}
	        	break;
	        default:
	        	System.out.println("Invalid choice. Please enter valid choice.");
	        }
		}
    }
}
