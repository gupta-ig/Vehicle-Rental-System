package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MaintenanceModelTest {

	private Maintenance maintenance;
	
	@BeforeEach
	public void setup() {
		maintenance = new Maintenance(
				UUID.randomUUID().toString(),
				"veh123",
				new Timestamp(System.currentTimeMillis()),
				new Timestamp(System.currentTimeMillis() + 1000), 
				"Vehicle parts needs to be changed", 
				new BigDecimal(5000));
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(maintenance.getMaintenanceId());
		assertEquals("veh123", maintenance.getVehicleId());
		assertNotNull(maintenance.getStartDate());
		assertNotNull(maintenance.getEndDate());
		assertTrue(maintenance.getDescription().contains("changed"));
		assertEquals(new BigDecimal(5000), maintenance.getTotalBill());
	}
	
	@Test
	public void testGettersAndSetters() {
		maintenance.setMaintenanceId();
		maintenance.setVehicleId("veh456");
		maintenance.setStartDate(new Timestamp(System.currentTimeMillis()));
		maintenance.setEndDate(new Timestamp(System.currentTimeMillis() + 1000));
		maintenance.setDescription("Parts needs oiling");
		maintenance.setTotalBill(new BigDecimal(2000.0));
	
		assertNotNull(maintenance.getMaintenanceId());
		assertEquals("veh456", maintenance.getVehicleId());
		assertNotNull(maintenance.getStartDate());
		assertNotNull(maintenance.getEndDate());
		assertTrue(maintenance.getDescription().contains("oiling"));
		assertEquals(new BigDecimal(2000.0), maintenance.getTotalBill());
	}
	
	@Test
	public void testToString() {
		String maintenanceString = maintenance.toString();
		
		assertTrue(maintenanceString.contains("veh123"));
		assertTrue(maintenanceString.contains("Vehicle parts"));
	}
}
