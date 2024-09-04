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
	        System.out.print(StringConstants.ENTER_YOUR_CHOICE);
	        
	        choice = App.scanner.nextInt();
	        App.scanner.nextLine();
	        
	        switch(choice) {
	        case 1:
	        	System.out.println(StringConstants.START_BOOKING_A_VEHICLE);
	        	bookingController.makeBooking(App.scanner, user.getUserId());
	        	break;
	        case 2:
	        	System.out.println(StringConstants.RETURNING_A_VEHICLE);
	        	bookingController.returnVehicle(user.getUserId());
	        	return;
	        case 3:
	        	System.out.println(StringConstants.YOUR_BOOKING_HISTORY);
	        	bookingController.viewHistory(user.getUserId());
	        	break;
	        case 4:
	        	System.out.println(StringConstants.GIVE_A_REVIEW);
	        	reviewController.addReview(user);
	        	break;
	        case 5:
	        	System.out.println(StringConstants.SEE_ALL_REVIEWS);
	        	reviewController.getReviewById(user.getUserId());
	        	break;
	        case 6:
	        	System.out.println(StringConstants.RAISING_A_COMPLAINT);
	        	complaintController.raiseComplaint(user);
	        	break;
	        case 7:
	        	System.out.println(StringConstants.VIEW_ALL_COMPLAINTS);
	        	complaintController.viewComplaintById(user.getUserId());
	        	break;
	        case 8:
	        	System.out.println(StringConstants.CANCEL_BOOKING);
	        	bookingController.cancelBooking(user.getUserId());
	        	break;
	        case 9:
	        	System.out.println(StringConstants.LOGGING_OUT);
	        	
	        	while(true) {
	        		System.out.print(StringConstants.DO_YOU_WANT_TO_CONTINUE);
		        	
			        String continueChoice = App.scanner.next();
			        if(continueChoice.equalsIgnoreCase("y")) {
			        	continue;
			        }
			        else if(continueChoice.equalsIgnoreCase("n")) {
			        	System.out.println(StringConstants.THANK_YOU_FOR_VISITING);
			        	System.exit(0);
			        	break;
			        }
			        else {
			        	System.out.println(StringConstants.PLEASE_ENTER_VALID_INPUT);
			        }
	        	}
	        	break;
	        default:
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE);
	        }
		}
    }
}
