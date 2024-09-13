package com.wg.presentation;

import java.sql.SQLException;

import com.wg.app.App;
import com.wg.controller.BookingController;
import com.wg.controller.ComplaintController;
import com.wg.controller.ReviewController;
import com.wg.dao.BookingDAO;
import com.wg.dao.ComplaintDAO;
import com.wg.dao.ReviewDAO;
import com.wg.dao.VehicleDAO;
import com.wg.helper.Logout;
import com.wg.helper.StringConstants;
import com.wg.model.User;
import com.wg.service.BookingService;
import com.wg.service.ComplaintService;
import com.wg.service.ReviewService;

public class CustomerMenu {

	public static void displayCustomerMenu(User user) throws SQLException {
		
		int choice;
		
		BookingDAO bookingDAO = new BookingDAO();
		VehicleDAO vehicleDAO = new VehicleDAO();
		BookingService bookingService = new BookingService(bookingDAO, vehicleDAO); 
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
	        	bookingController.makeBooking(App.scanner, user.getUserId());
	        	break;
	        case 2:
	        	bookingController.returnVehicle(user.getUserId());
	        	break;
	        case 3:
	        	bookingController.viewHistory(user.getUserId());
	        	break;
	        case 4:
	        	reviewController.addReview(user);
	        	break;
	        case 5:
	        	reviewController.getReviewById(user.getUserId());
	        	break;
	        case 6:
	        	complaintController.raiseComplaint(user);
	        	break;
	        case 7:
	        	complaintController.viewComplaintById(user.getUserId());
	        	break;
	        case 8:
	        	bookingController.cancelBooking(user.getUserId());
	        	break;
	        case 9:
	        	Logout.logout();
	        	break;
	        default:
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE);
	        }
		}
    }
}
