package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import com.wg.app.App;
import com.wg.helper.InputSanitizer;
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
			System.out.print("Enter vehicle manufacturer: ");
            String manufacturer = InputSanitizer.sanitizeName(scanner.next());
 
            System.out.print("Enter vehicle model: ");
			String model = InputSanitizer.sanitizeName(scanner.next());
			
			System.out.print("Enter vehicle registeration number: ");
			String registrationNumber = scanner.next();
			
			System.out.print("Enter vehicle manufacture year: ");
			int manufactureYear = scanner.nextInt();
			
			System.out.print("Enter vehicle type (BIKE, CAR): ");
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
			System.out.println("Vehicle registered successfully!\n");
		}
		catch (SQLException e) {
            System.err.println("Error while registering vehicle: " + e.getMessage());
        }
        catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        }
	}
	
	// Remove vehicle
    public void removeVehicle(Scanner scanner) {
    	try {
        	
        	System.out.println("List of all the vehicles: ");
    		List<Vehicle> vehicles = getAllVehicles();
    		
    		int choice;
    		System.out.print("Enter user's sr. No. to delete: ");
    		choice = App.scanner.nextInt();
        	
            vehicleService.removeVehicle(vehicles.get(choice - 1).getVehicleId());
            System.out.println("Vehicle removed successfully!");
 
        }
        catch (SQLException e) {
            System.err.println("Error while deleting user: " + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        }
    }
    
    // View All Vehicles
    public List<Vehicle> getAllVehicles() {
    	try {
    		List<Vehicle> vehicles = vehicleService.getAllVehicles();
    		if(vehicles == null && vehicles.size() < 0) {
    			System.out.println("No vehicles available.");
    		}
    		System.out.println("Available vehicles");
    		VehiclePrinter.printVehicles(vehicles);
    		return vehicles;
    	}
    	catch (Exception e) {
    		System.out.println("Error retrieving vehicles: " + e.getMessage());
    	}
    	return null;
    }
    
    public void viewAvailableVehicles(Timestamp startTime, Timestamp endTime) {
        try {
            List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles(startTime, endTime);
            if (availableVehicles.isEmpty()) {
                System.out.println("No vehicles available for the specified time.");
            } else {
            	VehiclePrinter.printVehicles(availableVehicles);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving available vehicles: " + e.getMessage());
        }
    }
    
    // View All Available Vehicles
//    public List<Vehicle> getAllAvailableVehicles() {
//    	try {
//    		List<Vehicle> availableVehicles = vehicleService.getAllAvailableVehicles(AvailabilityStatus.AVAILABLE);
//
//    		if(availableVehicles.size() == 0) {
//    			System.out.println("There are no available vehicles");
//    			return null;
//    		}
//    		
//    		System.out.println("Listing all available vehicles");
//    		VehiclePrinter.printVehicles(availableVehicles);
//    		return availableVehicles;
//    	}
//    	catch (Exception e) {
//    		System.out.println("Error retrieving available vehicles: " + e.getMessage());
//    	}
//    	return null;
//    }

	public List<Vehicle> getAllMaintenanceVehicles() {
		try {
			List<Vehicle> maintenanceVehicles = vehicleService.getAllMaintenanceVehicles(AvailabilityStatus.AVAILABLE);
			
			if(maintenanceVehicles.size() == 0) {
    			System.out.println("There are no available vehicles");
    			return null;
    		}
			
			System.out.println("Getting all maintenance vehicles");
			VehiclePrinter.printVehicles(maintenanceVehicles);
			return maintenanceVehicles;
		}
		catch (Exception e) {
    		System.out.println("Error retrieving maintenance vehicles: " + e.getMessage());
    	}
		return null;
		
	}

	public void changeVehicleStatus() {
		try {
        	
        	System.out.println("List of all the vehicles: ");
    		List<Vehicle> vehicles = getAllVehicles();
    		
    		int choice;
    		System.out.print("Enter vehicle sr. No. to change status: ");
    		choice = App.scanner.nextInt();
        	
    		AvailabilityStatus status = null;
    		while(true) {
    			System.out.print("Enter vehicle status to be changed into(AVAILABLE, BOOKED, MAINTENANCE): ");
        		String input = App.scanner.next().toUpperCase();
                status = AvailabilityStatus.valueOf(input);
                
                if(status == AvailabilityStatus.AVAILABLE || status == AvailabilityStatus.BOOKED || status == AvailabilityStatus.MAINTENANCE) {
                	break;
                }
                else {
                	System.out.println("Please enter a valid role.");
                }
    		}
    		
            vehicleService.changeVehicleStatus(vehicles.get(choice - 1).getVehicleId(), status);
            System.out.println("Vehicle status changed successfully!");
 
        }
        catch (SQLException e) {
            System.err.println("Error while deleting user: " + e.getMessage());
        } 
        catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        }
	}
}