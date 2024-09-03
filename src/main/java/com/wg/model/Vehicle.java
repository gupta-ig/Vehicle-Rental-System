package com.wg.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.VehicleType;

public class Vehicle {
	private String vehicleId;
	private String manufacturer;
	private String model;
	private String registerationNumber;
	private int manufactureYear;
	private Timestamp maintenanceDate;
	private VehicleType type;
	private AvailabilityStatus availabilityStatus;
	
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId() {
		this.vehicleId = UUID.randomUUID().toString();
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRegisterationNumber() {
		return registerationNumber;
	}
	public void setRegisterationNumber(String registerationNumber) {
		this.registerationNumber = registerationNumber;
	}
	public int getManufactureYear() {
		return manufactureYear;
	}
	public void setManufactureYear(int manufactureYear) {
		this.manufactureYear = manufactureYear;
	}
	public Timestamp getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(Timestamp maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	
	public Vehicle(String vehicleId, String manufacturer, String model, String registerationNumber, int manufactureYear,
			Timestamp maintenanceDate, VehicleType type, AvailabilityStatus availabilityStatus) {
		super();
		this.vehicleId = vehicleId == null ? UUID.randomUUID().toString() : vehicleId;
		this.manufacturer = manufacturer;
		this.model = model;
		this.registerationNumber = registerationNumber;
		this.manufactureYear = manufactureYear;
		this.maintenanceDate = maintenanceDate;
		this.type = type;
		this.availabilityStatus = availabilityStatus;
	}
	
	public Vehicle() {
		
	}
	
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", manufacturer=" + manufacturer + ", model=" + model
				+ ", registerationNumber=" + registerationNumber + ", manufactureYear=" + manufactureYear
				+ ", maintenanceDate=" + maintenanceDate + ", type=" + type + ", availabilityStatus="
				+ availabilityStatus + "]";
	}
	
}
