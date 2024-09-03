package com.wg.presentation;

import com.wg.app.App;
import com.wg.controller.ComplaintController;
import com.wg.controller.UserController;
import com.wg.controller.VehicleController;
import com.wg.dao.ComplaintDAO;
import com.wg.dao.UserDAO;
import com.wg.dao.VehicleDAO;
import com.wg.helper.StringConstants;
import com.wg.model.User;
import com.wg.service.ComplaintService;
import com.wg.service.UserRegisterService;
import com.wg.service.VehicleService;

public class EmployeeMenu {

	public static void displayEmployeeMenu(User user) {
	
		
		UserDAO userDAO = new UserDAO();
		UserRegisterService userRegisterService = new UserRegisterService(userDAO);
		UserController userController= new UserController(userRegisterService); 
		
		VehicleDAO vehicleDAO = new VehicleDAO();
		VehicleService vehicleService = new VehicleService(vehicleDAO);
		VehicleController vehicleController = new VehicleController(vehicleService); 
		
		ComplaintDAO complaintDAO = new ComplaintDAO();
	    ComplaintService complaintService = new ComplaintService(complaintDAO);
	    ComplaintController complaintController = new ComplaintController(complaintService);

		int choice;
		
        while(true) {
        	System.out.println(StringConstants.EMPLOYEE_MENU);
            System.out.print("Enter your choice: ");
            
            choice = App.scanner.nextInt();
            
            switch(choice) {
	        case 1:
	        	System.out.println("Displaying customers list");
	        	userController.getAllCustomers();
	        	break;
	        case 2:
	        	System.out.println("Displaying vehicles list");
	        	vehicleController.getAllVehicles();
	        	break;
	        case 3:
	        	System.out.println("Raising a complaint");
	        	complaintController.raiseComplaint(user);
	        	break;
	        case 4:
	        	System.out.println("Updating vehicle status");
	        	break;
	        case 5:
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
