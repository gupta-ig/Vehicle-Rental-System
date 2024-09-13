package com.wg.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wg.dao.VehicleDAO;
import com.wg.model.Vehicle;
import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.VehicleType;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

public class VehicleServiceTest {

	@Mock
	private VehicleDAO vehicleDAO;
	
	@InjectMocks
	private VehicleService vehicleService;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	Vehicle vehicle1 = new Vehicle("1", "Tata", "Tiago", "UP80UH9876", 2001, new Timestamp(System.currentTimeMillis()), VehicleType.CAR, AvailabilityStatus.AVAILABLE);
	
	@Test
	public void testRegisterVehicleSuccess() throws SQLException {
		when(vehicleDAO.getById("1")).thenReturn(Collections.emptyList());

		vehicleService.registerVehicle(vehicle1);
		assertNotNull(vehicle1);
		verify(vehicleDAO, times(1)).add(vehicle1);
	}
	
	@Test
	public void testRegisteredVehicleExist() throws SQLException {
		when(vehicleDAO.getById("1")).thenReturn(Arrays.asList(vehicle1));
		
		Vehicle newVehicle = new Vehicle("1", "Honda", "City", "UP67TF2345", 2000, new Timestamp(System.currentTimeMillis()), VehicleType.CAR, AvailabilityStatus.AVAILABLE);
		
		assertThrows(IllegalArgumentException.class, () -> vehicleService.registerVehicle(newVehicle), "Vehicle already exists.");
		
		verify(vehicleDAO, never()).add(any(Vehicle.class));
	}
	
	@Test
	public void testGetVehicleById() throws SQLException {
		when(vehicleDAO.getById("1")).thenReturn(Arrays.asList(vehicle1));
		
		List<Vehicle> result = vehicleService.getVehicleById("1");
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("Tata", result.get(0).getManufacturer());
		
		verify(vehicleDAO, times(1)).getById("1");
	}
	
	// Test deleting a vehicle
    @Test
    public void testDeleteVehicle() throws SQLException {
        vehicleService.removeVehicle("1");

        verify(vehicleDAO, times(1)).delete("1");
    }

    // Test getting all vehicles
    @Test
    public void testGetAllVehicle() throws SQLException {
    	Vehicle vehicle2 = new Vehicle("2", "Honda", "City", "UP80OK3456", 2000, new Timestamp(System.currentTimeMillis()), VehicleType.CAR, AvailabilityStatus.AVAILABLE);
		
    	when(vehicleDAO.getAll()).thenReturn(Arrays.asList(vehicle1, vehicle2));

        List<Vehicle> result = vehicleService.getAllVehicles();

        assertNotNull(result);
        assertEquals(2, result.size());

        verify(vehicleDAO, times(1)).getAll();
    }
    
    @Test
    public void testGetAvailableVehicles() throws SQLException {
    	Timestamp startTime = new Timestamp(System.currentTimeMillis());
    	Timestamp endTime = new Timestamp(System.currentTimeMillis() + 100000);
    	when(vehicleDAO.getAvailableVehicles(startTime, endTime)).thenReturn(Arrays.asList(vehicle1));
    	
    	List<Vehicle> result = vehicleService.getAvailableVehicles(startTime, endTime);
    	
    	assertNotNull(result);
    	assertEquals(1, result.size());
    	assertEquals("1", result.get(0).getVehicleId());
    }
    
    @Test
    public void testGetAllMaintenanceVehicles() throws SQLException {
    	Vehicle vehicle = new Vehicle("1", "Tata", "Tiago", "UP80UH9876", 2001, new Timestamp(System.currentTimeMillis()), VehicleType.CAR, AvailabilityStatus.MAINTENANCE);
    	
    	when(vehicleDAO.getAllMaintenanceVehicles(AvailabilityStatus.MAINTENANCE)).thenReturn(Arrays.asList(vehicle));
    	
    	List<Vehicle> result = vehicleService.getAllMaintenanceVehicles(AvailabilityStatus.MAINTENANCE);
    	
    	assertNotNull(result);
    	assertEquals(1, result.size());
    	assertEquals("1", result.get(0).getVehicleId());
    }
    
    @Test
    public void testChangeVehicleStatus() throws SQLException {
    	String vehicleId = "1";
    	AvailabilityStatus status = AvailabilityStatus.BOOKED;
    	
    	vehicleService.changeVehicleStatus(vehicleId, status);
    	
    	verify(vehicleDAO, times(1)).updateStatusQuery(vehicleId, status);
    }
}
