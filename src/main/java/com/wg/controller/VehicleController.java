package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import com.wg.app.App;
import com.wg.helper.InputSanitizer;
import com.wg.helper.InputValidator;
import com.wg.helper.LoggingUtil;
import com.wg.helper.StringConstants;
import com.wg.helper.VehiclePrinter;
import com.wg.model.Vehicle;
import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.Role;
import com.wg.model.enums.VehicleType;
import com.wg.service.VehicleService;

public class VehicleController {
	
	private VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}

	private static final Logger logger = LoggingUtil.getLogger(VehicleController.class);
    
	public void registerVehicle(Scanner scanner, VehicleService vehicleService) {
		try {
			System.out.println(StringConstants.ADD_A_VEHICLE);
			
			String manufacturer;
    		while(true) {
    			System.out.print(StringConstants.VEHICLE_MANUFACTURER);
                manufacturer = InputSanitizer.sanitizeName(scanner.next());
                if(InputValidator.isNameValid(manufacturer)) {
                	break;
                }
                else {
                	System.out.println(StringConstants.INVALID_NAME);
                }
            }
            String model;
    		while(true) {
    			System.out.print(StringConstants.VEHICLE_MODEL);
    			model = InputSanitizer.sanitizeName(scanner.next());
                if(InputValidator.isNameValid(model)) {
                	break;
                }
                else {
                	System.out.println(StringConstants.INVALID_NAME);
                }
            }
			String registrationNumber;
    		while(true) {
    			System.out.print(StringConstants.VEHICLE_REGISTERATION_NUMBER);
    			registrationNumber = InputSanitizer.sanitizeRegistrationNumber(scanner.next());
                if(InputValidator.isRegistrationNumberValid(registrationNumber)) {
                	break;
                }
                else {
                	System.out.println(StringConstants.INVALID_NAME);
                }
            }
			
			int manufactureYear = 0;
	        int currentYear = LocalDate.now().getYear();
	        
	        while (true) {
	        	System.out.print(StringConstants.VEHICLE_MANUFACTURE_YEAR);
				try {
					manufactureYear = Integer.parseInt(scanner.next());
	                if (manufactureYear > currentYear) {
	                    System.out.println("Invalid input. The manufacture year cannot be in the future.");
	                }
	                else {
	                    break;
	                }
	            } 
				catch (NumberFormatException e) {
	                System.out.println("Invalid input. Please enter a valid year as an integer.");
	            }
	        }
	        
			String typeInput = "";
            boolean validType = false;
            while(!validType) {
            	System.out.print(StringConstants.VEHICLE_TYPE);
    			typeInput = scanner.next().toUpperCase();
                
                if(typeInput.equals("CAR") || typeInput.equals("BIKE")) {
                	validType = true;;
                }
                else {
                	System.out.println("Please enter valid vehicle type.");
                }
            }
            VehicleType vehicleType = VehicleType.valueOf(typeInput);
			
            AvailabilityStatus vehicleStatus = AvailabilityStatus.AVAILABLE;
			
			Vehicle newVehicle = new Vehicle();
			
			newVehicle.setVehicleId();
			newVehicle.setManufacturer(manufacturer);
			newVehicle.setModel(model);
			newVehicle.setRegisterationNumber(registrationNumber);
			newVehicle.setManufactureYear(manufactureYear);
			newVehicle.setMaintenanceDate(Timestamp.valueOf(LocalDateTime.now()));
			newVehicle.setType(vehicleType);
			newVehicle.setAvailabilityStatus(vehicleStatus);
			
			vehicleService.registerVehicle(newVehicle);
			
			logger.info(StringConstants.VEHICLE_REGISTERED_SUCCESSFULLY);
		}
		catch (SQLException e) {
            System.err.println(StringConstants.ERROR_WHILE_REGISTERING_VEHICLE + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
        }
	}
	
	// Remove vehicle
    public void removeVehicle(Scanner scanner) {
    	try {
    		List<Vehicle> vehicles = getAllVehicles();
        	
    		if(vehicles == null) {
    			return;
    		}
    		
    		int index;
    		while(true) {
    			System.out.print(StringConstants.ENTER_VEHICLE_S_SR_NO_TO_BE_DELETED);
        		index = App.scanner.nextInt();
        		if(index > 0 && index <= vehicles.size()) {
        			break;
        		}
        		else {
        			System.out.println("Please enter valid index.");
        		}
    		}
            vehicleService.removeVehicle(vehicles.get(index - 1).getVehicleId());
            logger.info(StringConstants.VEHICLE_REMOVED_SUCCESSFULLY);
        }
        catch (SQLException e) {
            System.err.println(StringConstants.ERROR_WHILE_DELETING_VEHICLE + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
        }
    }
    
    // View All Vehicles
    public List<Vehicle> getAllVehicles() {
    	try {
    		List<Vehicle> vehicles = vehicleService.getAllVehicles();
    		if(vehicles.isEmpty()) {
    			System.out.println(StringConstants.NO_VEHICLES_AVAILABLE);
    			return null;
    		}
    		else {
    			System.out.println(StringConstants.DISPLAYING_VEHICLES_LIST);
        		VehiclePrinter.printVehicles(vehicles);
        		return vehicles;
    		}
    	}
    	catch (Exception e) {
    		System.out.println(StringConstants.ERROR_RETRIEVING_VEHICLES + e.getMessage());
    	}
    	return null;
    }
    
    public void viewAvailableVehicles(Timestamp startTime, Timestamp endTime) {
        try {
            List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles(startTime, endTime);
            if (availableVehicles.isEmpty()) {
                System.out.println(StringConstants.NO_VEHICLES_AVAILABLE_FOR_THE_SPECIFIED_TIME);
            } else {
            	VehiclePrinter.printVehicles(availableVehicles);
            }
        } catch (SQLException e) {
            System.out.println(StringConstants.AN_ERROR_OCCURRED_WHILE_RETRIEVING_AVAILABLE_VEHICLES + e.getMessage());
        }
    }

	public List<Vehicle> getAllMaintenanceVehicles() {
		try {
			List<Vehicle> maintenanceVehicles = vehicleService.getAllMaintenanceVehicles(AvailabilityStatus.MAINTENANCE);

			if(maintenanceVehicles.isEmpty()) {
    			System.out.println(StringConstants.NO_VEHICLES_AVAILABLE);
    			return null;
    		}
			else {
				System.out.println(StringConstants.GETTING_ALL_MAINTENANCE_VEHICLES);
				VehiclePrinter.printVehicles(maintenanceVehicles);
				return maintenanceVehicles;
			}
		}
		catch (Exception e) {
    		System.out.println(StringConstants.ERROR_RETRIEVING_MAINTENANCE_VEHICLES + e.getMessage());
    	}
		return null;
		
	}

	public void changeVehicleStatus() {
		try {
        	
        	System.out.println(StringConstants.DISPLAYING_VEHICLES_LIST);
    		List<Vehicle> vehicles = getAllVehicles();
    		
    		int choice;
    		System.out.print(StringConstants.ENTER_VEHICLE_SR_NO_TO_CHANGE_STATUS);
    		choice = App.scanner.nextInt();
        	
    		AvailabilityStatus status = null;
    		while(true) {
    			System.out.print(StringConstants.VEHICLE_STATUS_TO_BE_CHANGED_INTO);
        		String input = App.scanner.next().toUpperCase();
                status = AvailabilityStatus.valueOf(input);
                
                if(status == AvailabilityStatus.AVAILABLE || status == AvailabilityStatus.BOOKED || status == AvailabilityStatus.MAINTENANCE) {
                	break;
                }
                else {
                	System.out.println(StringConstants.PLEASE_ENTER_A_VALID_STATUS);
                }
    		}
    		
            vehicleService.changeVehicleStatus(vehicles.get(choice - 1).getVehicleId(), status);
            logger.info(StringConstants.VEHICLE_STATUS_CHANGED_SUCCESSFULLY);
        }
        catch (SQLException e) {
            System.err.println(StringConstants.ERROR_WHILE_CHANGING_STATUS + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
        }
	}
}