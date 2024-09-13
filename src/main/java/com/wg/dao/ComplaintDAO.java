package com.wg.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wg.model.Complaint;
import com.wg.model.enums.ComplaintStatus;

public class ComplaintDAO extends GenericDAO<Complaint, String>{

	@Override
	protected String getTableName() {
		return "COMPLAINT";
	}

	@Override
	protected Complaint mapResultSetToEntity(ResultSet result) throws SQLException {
		return new Complaint(
				result.getString("complaint_id"),
				result.getString("user_id"),
				result.getTimestamp("complaint_date"),
				ComplaintStatus.valueOf(result.getString("complaint_status")),
				result.getString("complaint_description"));
	}

	@Override
	protected void setPreparedStatementForEntity(PreparedStatement stmt, Complaint complaint) throws SQLException {
		stmt.setString(1, complaint.getComplaintId());
		stmt.setString(2, complaint.getUserId());
		stmt.setTimestamp(3, complaint.getComplaintDate());
		stmt.setString(4, complaint.getComplaintStatus().name());
		stmt.setString(5, complaint.getDescription());
	}

	@Override
	protected String getPrimaryKeyColumn() {
		return "complaint_id";
	}

	@Override
	protected void setPreparedStatementForPrimaryKey(PreparedStatement stmt, String complaintId) throws SQLException {
		stmt.setString(1, complaintId);
		
	}

	@Override
	protected String getPlaceholders() {
		return "?, ?, ?, ?, ?";
	}

	public void updateStatusQuery(String complaintId, ComplaintStatus status) throws SQLException {
		String UPDATE_QUERY = "UPDATE " + getTableName() + " SET complaint_status = ? WHERE " + getPrimaryKeyColumn() + " = ?";
		try (PreparedStatement stmt = connection.prepareStatement(UPDATE_QUERY)){
			stmt.setString(1, status.name());
			stmt.setString(2, complaintId);
			stmt.executeUpdate();
		}
	}

}
