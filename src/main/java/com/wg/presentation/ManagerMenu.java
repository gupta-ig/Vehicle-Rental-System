package com.wg.presentation;

import com.wg.app.App;
import com.wg.controller.ComplaintController;
import com.wg.controller.VehicleController;
import com.wg.dao.ComplaintDAO;
import com.wg.dao.VehicleDAO;
import com.wg.helper.Choice;
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
            
            choice = Choice.enterChoice();
            
            switch(choice) {
	        case 1:
	        	System.out.println(StringConstants.DISPLAYING_VEHICLES_LIST);
	        	vehicleController.getAllVehicles();
	        	break;
	        case 2:
	        	System.out.println(StringConstants.DISPLAY_ALL_THE_MAINTENANCE_VEHICLE);
	        	vehicleController.getAllMaintenanceVehicles();
	        	break;
	        case 3:
	        	System.out.println(StringConstants.UPDATING_VEHICLE_STATUS);
	        	vehicleController.changeVehicleStatus();
	        	break;
	        case 4:
	        	System.out.println(StringConstants.RAISING_A_COMPLAINT);
	        	complaintController.raiseComplaint(user);
	        	break;
	        case 5:
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
