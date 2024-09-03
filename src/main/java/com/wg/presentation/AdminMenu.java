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
	        System.out.print("Enter your choice: ");
	        
	        choice = App.scanner.nextInt();
	        App.scanner.nextLine();
	        
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
	        	while(true) {
	        		System.out.println("Logging out...");
		        	
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

	private static void displayUserManagementMenu() {
		int choice;
		
		while(true) {
			System.out.println(StringConstants.USER_MANAGEMENT);
			System.out.print("Enter your choice: ");
			
			choice = App.scanner.nextInt();
			App.scanner.nextLine();
			
			switch(choice) {
			case 1:
	        	System.out.println("Displaying all users list");
	        	userController.getAllUsers();
	        	break;
	        case 2:
	        	System.out.println("Displaying all customers list");
	        	userController.getAllCustomers();
	        	break;
	        case 3:
	        	System.out.println("Displaying all employees list");
	        	userController.getAllEmployees();
	        	break;
	        case 4:
	        	System.out.println("Displaying all managers list");
	        	userController.getAllManagers();
	        	break;
	        case 5:
	        	System.out.println("Register an user");
	        	UserController.registerUser(App.scanner, userRegisterService);
	        	break;
	        case 6:
	        	System.out.println("Delete a user");
	        	userController.deleteUser(App.scanner);
	        	break;
	        case 7:
	        	return; 
	        default: 
	        	System.out.println("Invalid choice. Please enter valid choice.");
	        }
		}
	}
	
	private static void displayVehicleManagementMenu() {
		int choice;
		
		while(true) {
			System.out.println(StringConstants.VEHICLE_MANAGEMENT);
			System.out.print("Enter your choice: ");
			
			choice = App.scanner.nextInt();
			App.scanner.nextLine();
			
			switch(choice) {
			case 1:
	        	System.out.println("Displaying all vehicles");
	        	vehicleController.getAllVehicles();
	        	break;
	        case 2:
	        	System.out.println("Displaying all maintenance vehicles");
	        	vehicleController.getAllMaintenanceVehicles();
	        	break;
	        case 3:
	        	System.out.println("Add a Vehicle");
	        	vehicleController.registerVehicle(App.scanner, vehicleService);
	        	break;
	        case 4:
	        	System.out.println("Remove a vehicle");
	        	vehicleController.removeVehicle(App.scanner);
	        	break;
	        case 5:
	        	return;
	        default:
	        	System.out.println("Invalid choice. Please enter valid choice.");
			}
		}
	}
	

	private static void displayReviewManagementMenu() {
		int choice;
		
		System.out.println(StringConstants.REVIEW_MANAGEMENT);
		System.out.print("Enter your choice: ");
		
		choice = App.scanner.nextInt();
		App.scanner.nextLine();
		
		switch(choice) {
		case 1:
        	System.out.println("Viewing Reviews");
        	reviewController.viewAllReviews();
        	break;
        case 2:
        	System.out.println("Delete Reviews");
        	reviewController.deleteReview();;
        	break;
        case 3:
        	return;
        default:
        	System.out.println("Invalid choice. Please enter valid choice."); 
		}
	}

	private static void displayComplaintManagementMenu() {
		int choice;
		
		System.out.println(StringConstants.COMPLAINT_MANAGEMENT);
		System.out.print("Enter your choice: ");
		
		choice = App.scanner.nextInt();
		App.scanner.nextLine();
		
		switch(choice) {
		case 1:
        	System.out.println("Viewing all complaints");
        	complaintController.viewAllComplaints();
        	break;
//        case 2:
//        	System.out.println("Updating complaint status");
//        	break;
		case 3:
			return;
        default: 
        	System.out.println("Invalid choice. Please enter valid choice."); 
		}
	}
}
