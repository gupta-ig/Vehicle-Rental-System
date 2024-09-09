package com.wg.presentation;

import com.wg.app.App;
import com.wg.controller.ComplaintController;
import com.wg.controller.UserController;
import com.wg.controller.VehicleController;
import com.wg.dao.ComplaintDAO;
import com.wg.dao.UserDAO;
import com.wg.dao.VehicleDAO;
import com.wg.helper.Logout;
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
	        	userController.getAllCustomers();
	        	break;
	        case 2:
	        	vehicleController.getAllVehicles();
	        	break;
	        case 3:
	        	vehicleController.getAllMaintenanceVehicles();
	        	break;
	        case 4:
	        	complaintController.raiseComplaint(user);
	        	break;
	        case 5:
	        	vehicleController.changeVehicleStatus();
	        	break;
	        case 6:
	        	Logout.logout();
	        	break;
	        default:
	        	System.out.println(StringConstants.INVALID_CHOICE_PLEASE_ENTER_VALID_CHOICE);
	        }
        }
    }
 
}
