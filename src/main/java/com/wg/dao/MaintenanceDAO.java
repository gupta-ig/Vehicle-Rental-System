package com.wg.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wg.model.Maintenance;

public class MaintenanceDAO extends GenericDAO<Maintenance, String>{

	@Override
	protected String getTableName() {
		return "MAINTENANCE";
	}

	@Override
	protected Maintenance mapResultSetToEntity(ResultSet result) throws SQLException {
		return new Maintenance(
				result.getString("maintenance_id"),
				result.getString("vehicle_id"),
				result.getTimestamp("start_date"),
				result.getTimestamp("end_date"),
				result.getString("maintenance_description"),
				result.getBigDecimal("total_bill")
				);
	}

	@Override
	protected void setPreparedStatementForEntity(PreparedStatement stmt, Maintenance maintenance) throws SQLException {
		stmt.setString(1, maintenance.getMaintenanceId());
		stmt.setString(2, maintenance.getVehicleId());
		stmt.setTimestamp(3, maintenance.getStartDate());
		stmt.setTimestamp(4, maintenance.getEndDate());
		stmt.setString(5, maintenance.getDescription());
		stmt.setBigDecimal(6, maintenance.getTotalBill());
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "maintenance_id";
	}

	@Override
	protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String maintenanceId) throws SQLException {
		stmt.setString(1, maintenanceId);
		
	}

	@Override
	protected String getPlaceholders() {
		return "?, ?, ?, ?, ?, ?";
	}

}
