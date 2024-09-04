package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.wg.app.App;
import com.wg.helper.InputSanitizer;
import com.wg.helper.StringConstants;
import com.wg.helper.VehiclePrinter;
import com.wg.model.Vehicle;
import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.Role;
import com.wg.model.enums.VehicleType;
import com.wg.service.VehicleService;

public class VehicleController {
	private final VehicleService vehicleService;

	public VehicleController(VehicleService vehicleService) {
		super();
		this.vehicleService = vehicleService;
	}
	
	public void registerVehicle(Scanner scanner, VehicleService vehicleService) {
		try {
			System.out.print(StringConstants.VEHICLE_MANUFACTURER);
            String manufacturer = InputSanitizer.sanitizeName(scanner.next());
 
            System.out.print(StringConstants.VEHICLE_MODEL);
			String model = InputSanitizer.sanitizeName(scanner.next());
			
			System.out.print(StringConstants.VEHICLE_REGISTERATION_NUMBER);
			String registrationNumber = scanner.next();
			
			System.out.print(StringConstants.VEHICLE_MANUFACTURE_YEAR);
			int manufactureYear = scanner.nextInt();
			
			System.out.print(StringConstants.VEHICLE_TYPE);
			String typeInput = scanner.next().toUpperCase();
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
			System.out.println(StringConstants.VEHICLE_REGISTERED_SUCCESSFULLY);
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
        	
        	System.out.println(StringConstants.DISPLAYING_VEHICLES_LIST);
    		List<Vehicle> vehicles = getAllVehicles();
    		
    		int choice;
    		System.out.print(StringConstants.ENTER_VEHICLE_S_SR_NO_TO_BE_DELETED);
    		choice = App.scanner.nextInt();
        	
            vehicleService.removeVehicle(vehicles.get(choice - 1).getVehicleId());
            System.out.println(StringConstants.VEHICLE_REMOVED_SUCCESSFULLY);
 
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
    		if(vehicles == null && vehicles.size() < 0) {
    			System.out.println(StringConstants.NO_VEHICLES_AVAILABLE);
    		}
    		
    		VehiclePrinter.printVehicles(vehicles);
    		return vehicles;
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
			List<Vehicle> maintenanceVehicles = vehicleService.getAllMaintenanceVehicles(AvailabilityStatus.AVAILABLE);
			
			if(maintenanceVehicles.size() == 0) {
    			System.out.println(StringConstants.NO_VEHICLES_AVAILABLE);
    			return null;
    		}
			
			System.out.println(StringConstants.GETTING_ALL_MAINTENANCE_VEHICLES);
			VehiclePrinter.printVehicles(maintenanceVehicles);
			return maintenanceVehicles;
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
            System.out.println(StringConstants.VEHICLE_STATUS_CHANGED_SUCCESSFULLY);
 
        }
        catch (SQLException e) {
            System.err.println(StringConstants.ERROR_WHILE_CHANGING_STATUS + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
        }
	}
}