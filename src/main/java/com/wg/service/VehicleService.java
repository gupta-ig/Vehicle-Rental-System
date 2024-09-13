package com.wg.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.wg.dao.VehicleDAO;
import com.wg.model.Vehicle;
import com.wg.model.enums.AvailabilityStatus;

public class VehicleService {
	private VehicleDAO vehicleDAO;

	public VehicleService(VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}
	 
	// Register new Vehicle
	public void registerVehicle(Vehicle vehicle) throws SQLException {
        List<Vehicle> existingVehicle = vehicleDAO.getById(vehicle.getVehicleId());
        if(!existingVehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle already exists.");
        }
        vehicleDAO.add(vehicle);
    } 
	
	// Get Vehicle By Id
    public List<Vehicle> getVehicleById(String vehicleId) throws SQLException {
        return vehicleDAO.getById(vehicleId);
    }
    
    // Delete a vehicle
    public void removeVehicle(String vehicleId) throws SQLException {
        vehicleDAO.delete(vehicleId);
    }
    
    // Get all vehicle
    public List<Vehicle> getAllVehicles() throws SQLException {
        return vehicleDAO.getAll();
    }
//    
//    // Get all available vehicle
//    public List<Vehicle> getAllAvailableVehicles(AvailabilityStatus status) throws SQLException {
//        return vehicleDAO.getAllAvailableVehicles(status);
//    }
    
    public List<Vehicle> getAvailableVehicles(Timestamp startTime, Timestamp endTime) throws SQLException {
        return vehicleDAO.getAvailableVehicles(startTime, endTime);
    }
    
    // Get all maintenance vehicles
	public List<Vehicle> getAllMaintenanceVehicles(AvailabilityStatus status) throws SQLException {
		return vehicleDAO.getAllMaintenanceVehicles(status);
	}
	
	public void changeVehicleStatus(String vehicleId, AvailabilityStatus status) throws SQLException {
		vehicleDAO.updateStatusQuery(vehicleId, status);
	}
}
