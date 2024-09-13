package com.wg.dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wg.config.DatabaseConfig;
import com.wg.model.Vehicle;
import com.wg.model.enums.AvailabilityStatus;
import com.wg.model.enums.VehicleType;

public class VehicleDAO extends GenericDAO<Vehicle, String> {

	Connection connection;
	
	public VehicleDAO () {
		try {
			connection = DatabaseConfig.getConnection();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
	}
	
	@Override
	protected String getTableName() {
		return "VEHICLE";
	}

	@Override
	protected Vehicle mapResultSetToEntity(ResultSet result) throws SQLException {
		return new Vehicle(
				result.getString("vehicle_id"),
				result.getString("manufacturer"),
				result.getString("model"),
				result.getString("registration_number"),
				result.getInt("manufacture_year"),
				result.getTimestamp("maintenance_date"),
				VehicleType.valueOf(result.getString("vehicle_type")),
				AvailabilityStatus.valueOf(result.getString("availability_status"))
			);
	}

	@Override
	protected void setPreparedStatementForEntity(PreparedStatement stmt, Vehicle vehicle) throws SQLException {
		stmt.setString(1, vehicle.getVehicleId());
		stmt.setString(2, vehicle.getManufacturer());
		stmt.setString(3, vehicle.getModel());
		stmt.setString(4, vehicle.getRegisterationNumber());
		stmt.setInt(5, vehicle.getManufactureYear());
		stmt.setTimestamp(7, vehicle.getMaintenanceDate());
		stmt.setString(6, vehicle.getType().name());
		stmt.setString(8, vehicle.getAvailabilityStatus().name());
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "vehicle_id";
	}

	@Override
	protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String vehicleId) throws SQLException {
		stmt.setString(1, vehicleId);
		
	}

	@Override
	protected String getPlaceholders() {
		return "?, ?, ?, ?, ?, ?, ?, ?";
	}

	public List<Vehicle> getAllMaintenanceVehicles(AvailabilityStatus status) throws SQLException {
		String SELECT_QUERY = "SELECT * FROM " + getTableName() + " WHERE availability_status = ?";
		List<Vehicle> maintenanceVehicles = new ArrayList<>();
		try(PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY)) {
			stmt.setString(1, status.name());
			try(ResultSet result = stmt.executeQuery()) {
				while(result.next()) {
					maintenanceVehicles.add(mapResultSetToEntity(result));
				}
			}
		}
		return maintenanceVehicles;
	}

	public void updateStatusQuery(String vehicleId, AvailabilityStatus status) throws SQLException {
		String UPDATE_QUERY = "UPDATE " + getTableName() + " SET availability_status = ? WHERE " + getPrimaryKeyColumn() + " = ?";
		try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)){
			stmt.setString(1, status.name());
			stmt.setString(2, vehicleId);
			System.out.println(stmt);
			stmt.executeUpdate();
		}	 
	}

//	public List<Vehicle> getAllAvailableVehicles(AvailabilityStatus status) throws SQLException {
//		String SELECT_QUERY = "SELECT * FROM " + getTableName() + " WHERE availability_status = ?";
//		List<Vehicle> availableVehicles = new ArrayList<>();
//		try(Connection connection = DatabaseConfig.getConnection();
//				PreparedStatement stmt = connection.prepareStatement(SELECT_QUERY)) {
//			stmt.setString(1, status.name());
//			try(ResultSet result = stmt.executeQuery()) {
//				while(result.next()) {
//					availableVehicles.add(mapResultSetToEntity(result));
//				}
//			}
//		}
//		return availableVehicles;
//	}
	
	 public List<Vehicle> getAvailableVehicles(Timestamp startTime, Timestamp endTime) throws SQLException {
	        String query = "SELECT v.* FROM vehicle v WHERE v.availability_status = 'AVAILABLE' AND NOT EXISTS " +
	                       "(SELECT 1 FROM booking b WHERE b.vehicle_id = v.vehicle_id " +
	                       "AND NOT (b.booking_end_time <= ? OR b.booking_start_time >= ?))";
	        
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setTimestamp(1, startTime);
	            preparedStatement.setTimestamp(2, endTime);
	            
	            ResultSet resultSet = preparedStatement.executeQuery();
	            List<Vehicle> availableVehicles = new ArrayList<>();
	            
	            while (resultSet.next()) {
	                Vehicle vehicle = mapResultSetToEntity(resultSet);
	                
	                availableVehicles.add(vehicle);
	            }
	            return availableVehicles;
	        }
	    }
}
