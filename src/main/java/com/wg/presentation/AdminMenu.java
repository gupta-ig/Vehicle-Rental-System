package com.wg.presentation;

import com.wg.app.App;
import com.wg.controller.ComplaintController;
import com.wg.controller.ReviewController;
import com.wg.controller.UserController;
import com.wg.controller.VehicleController;
import com.wg.dao.ComplaintDAO;
import com.wg.dao.ReviewDAO;
import com.wg.dao.UserDAO;
import com.wg.dao.VehicleDAO;
import com.wg.helper.Choice;
import com.wg.helper.Logout;
import com.wg.helper.StringConstants;
import com.wg.model.User;
import com.wg.service.ComplaintService;
import com.wg.service.ReviewService;
import com.wg.service.UserRegisterService;
import com.wg.service.VehicleService;

public class AdminMenu {
	
	private static UserDAO userDAO = new UserDAO();
	private static UserRegisterService userRegisterService = new UserRegisterService(userDAO);
	private static UserController userController= new UserController(userRegisterService); 
	
	private static VehicleDAO vehicleDAO = new VehicleDAO();
	private static VehicleService vehicleService = new VehicleService(vehicleDAO);
	private static VehicleController vehicleController = new VehicleController(vehicleService); 
	
	private static ReviewDAO reviewDAO = new ReviewDAO();
	private static ReviewService reviewService = new ReviewService(reviewDAO);
	private static ReviewController reviewController = new ReviewController(reviewService); 
	
	private static ComplaintDAO complaintDAO = new ComplaintDAO();
	private static ComplaintService complaintService = new ComplaintService(complaintDAO);
	private static ComplaintController complaintController = new ComplaintController(complaintService); 
	
	public static void displayAdminMenu(User user) {
		
		int choice;
		
		while(true) {
			System.out.println(StringConstants.ADMIN_MENU);
	        
			System.out.print(StringConstants.ENTER_YOUR_CHOICE);
	        choice = Choice.enterChoice();
	        
	        switch(choice) {
	        
	        case 1: 
	        	displayUserManagementMenu();
	        	break;
	        	
	        case 2:
	        	displayVehicleManagementMenu();
	        	break;
	        	
	        case 3:
	        	displayReviewManagementMenu();
	        	break;
	        	
	        case 4: 
	        	displayComplaintManagementMenu();
	        	break;
	        	
	        case 5:
	        	Logout.logout();
	        	break;
	        default: 
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE);
	        }   
		}
    }

	private static void displayUserManagementMenu() {
		int choice;
		
		while(true) {
			System.out.println(StringConstants.USER_MANAGEMENT);
			
			System.out.print(StringConstants.ENTER_YOUR_CHOICE);
			choice = Choice.enterChoice();
			
			switch(choice) {
			case 1:
	        	userController.getAllUsers();
	        	break;
	        case 2:
	        	userController.getAllCustomers();
	        	break;
	        case 3:
	        	userController.getAllEmployees();
	        	break;
	        case 4:
	        	userController.getAllManagers();
	        	break;
	        case 5:
	        	UserController.registerUser(App.scanner, userRegisterService);
	        	break;
	        case 6:
	        	userController.deleteUser(App.scanner);
	        	break;
	        case 7:
	        	return; 
	        default: 
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE);
	        }
		}
	}
	
	private static void displayVehicleManagementMenu() {
		int choice;
		
		while(true) {
			System.out.println(StringConstants.VEHICLE_MANAGEMENT);
			
			System.out.print(StringConstants.ENTER_YOUR_CHOICE);
			choice = Choice.enterChoice();
			
			switch(choice) {
			case 1:
	        	vehicleController.getAllVehicles();
	        	break;
	        case 2:
	        	vehicleController.getAllMaintenanceVehicles();
	        	break;
	        case 3:
	        	vehicleController.registerVehicle(App.scanner, vehicleService);
	        	break;
	        case 4:
	        	vehicleController.removeVehicle(App.scanner);
	        	break;
	        case 5:
	        	return;
	        default:
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE);
			}
		}
	}
	

	private static void displayReviewManagementMenu() {
		int choice;
		
		while(true) {
			System.out.println(StringConstants.REVIEW_MANAGEMENT);
			
			System.out.print(StringConstants.ENTER_YOUR_CHOICE);
			choice = Choice.enterChoice();
			
			switch(choice) {
			case 1:
	        	reviewController.viewAllReviews();
	        	break;
	        case 2:
	        	System.out.println(StringConstants.DELETE_REVIEWS);
	        	reviewController.deleteReview();;
	        	break;
	        case 3:
	        	return;
	        default:
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE); 
			}
		}
	}

	private static void displayComplaintManagementMenu() {
		int choice;
		
		while(true) {
			System.out.println(StringConstants.COMPLAINT_MANAGEMENT);
			
			System.out.print(StringConstants.ENTER_YOUR_CHOICE);
			choice = Choice.enterChoice();
			
			switch(choice) {
			case 1:
	        	complaintController.viewAllComplaints();
	        	break;
	        case 2:
	        	complaintController.updateComplaintStatus();
	        	break;
			case 3:
				return;
	        default: 
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE); 
			}
		}
	}
}
