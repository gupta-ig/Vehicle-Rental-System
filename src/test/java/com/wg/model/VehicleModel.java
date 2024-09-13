package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.VehicleType;

public class VehicleModel {

	private Vehicle vehicle;
	
	@BeforeEach
	public void setup() {
		vehicle = new Vehicle(
				UUID.randomUUID().toString(),
				"Tata",
				"Tiago",
				"UP80IJ9860",
				2002,
				new Timestamp(System.currentTimeMillis()),
				VehicleType.CAR,
				AvailabilityStatus.AVAILABLE);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(vehicle.getVehicleId());
		assertEquals("Tata", vehicle.getManufacturer());
		assertEquals("Tiago", vehicle.getModel());
		assertEquals("UP80IJ9860", vehicle.getRegisterationNumber());
		assertEquals(2002, vehicle.getManufactureYear());
		assertNotNull(vehicle.getMaintenanceDate());
		assertEquals("CAR", vehicle.getType().name());
		assertEquals("AVAILABLE", vehicle.getAvailabilityStatus().name());
	}
	
	@Test
	public void testGettersAndSetters() {
		vehicle.setVehicleId();
		vehicle.setManufacturer("Honda");
		vehicle.setModel("City");
		vehicle.setRegisterationNumber("UP98UN7382");
		vehicle.setManufactureYear(2000);
		vehicle.setMaintenanceDate(new Timestamp(System.currentTimeMillis()));
		vehicle.setType(VehicleType.CAR);
		vehicle.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
		
		assertNotNull(vehicle.getVehicleId());
		assertEquals("Honda", vehicle.getManufacturer());
		assertEquals("City", vehicle.getModel());
		assertEquals("UP98UN7382", vehicle.getRegisterationNumber());
		assertEquals(2000, vehicle.getManufactureYear());
		assertNotNull(vehicle.getMaintenanceDate());
		assertEquals("CAR", vehicle.getType().name());
		assertEquals("AVAILABLE", vehicle.getAvailabilityStatus().name());
	}
	
	@Test
	public void testToString() {
		String vehicleString = vehicle.toString();
		
		assertTrue(vehicleString.contains("Tata"));
		assertTrue(vehicleString.contains("UP80IJ9860"));
		assertFalse(vehicleString.contains("2344"));
	}
	
	@Test
	public void testEmptyConstructor() {
		Vehicle vehicle = new Vehicle();
		
		assertNull(vehicle.getVehicleId());
		assertNull(vehicle.getManufacturer());
		assertNull(vehicle.getModel());
		assertNull(vehicle.getRegisterationNumber());
		assertNull(vehicle.getMaintenanceDate());
		assertNull(vehicle.getAvailabilityStatus());
		assertNull(vehicle.getType());
		assertEquals(0, vehicle.getManufactureYear());
	}
}
