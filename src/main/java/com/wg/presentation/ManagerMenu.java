package com.wg.presentation;

import com.wg.app.App;
import com.wg.controller.ComplaintController;
import com.wg.controller.VehicleController;
import com.wg.dao.ComplaintDAO;
import com.wg.dao.VehicleDAO;
import com.wg.helper.StringConstants;
import com.wg.model.User;
import com.wg.service.ComplaintService;
import com.wg.service.VehicleService;

public class ManagerMenu {

	public static void displayManagerMenu(User user) {
	
		VehicleDAO vehicleDAO = new VehicleDAO();
		VehicleService vehicleService = new VehicleService(vehicleDAO);
		VehicleController vehicleController = new VehicleController(vehicleService); 
		
		ComplaintDAO complaintDAO = new ComplaintDAO();
		ComplaintService complaintService = new ComplaintService(complaintDAO);
		ComplaintController complaintController = new ComplaintController(complaintService); 
		
		int choice;
		
        while(true) {
        	System.out.println(StringConstants.MANAGER_MENU);
            System.out.print("Enter your choice: ");
            
            choice = App.scanner.nextInt();
            
            switch(choice) {
	        case 1:
	        	System.out.println("Display all the vehicles");
	        	vehicleController.getAllVehicles();
	        	break;
	        case 2:
	        	System.out.println("Display all the maintenance vehicle");
	        	vehicleController.getAllMaintenanceVehicles();
	        	break;
	        case 3:
	        	System.out.println("Update a vehicle status");
	        	vehicleController.changeVehicleStatus();
	        	break;
	        case 4:
	        	System.out.println("Raising a complaint");
	        	complaintController.raiseComplaint(user);
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
