package com.wg.model;

import java.sql.Timestamp;
import java.util.UUID;

import com.wg.model.enums.ComplaintStatus;

public class Complaint {
	private String complaintId;
	private String userId;
	private Timestamp complaintDate;
	private ComplaintStatus complaintStatus;
	private String description;
	
	public Complaint(String complaintId, String userId, Timestamp complaintDate, ComplaintStatus complaintStatus,
			String description) {
		super();
		this.complaintId = (complaintId == null) ? UUID.randomUUID().toString() : complaintId;
		this.userId = userId;
		this.complaintDate = complaintDate;
		this.complaintStatus = complaintStatus;
		this.description = description;
	}
	

	public Complaint() {
		// TODO Auto-generated constructor stub
	}


	public String getComplaintId() {
		return complaintId;
	}
	public void setComplaintId() {
		this.complaintId = UUID.randomUUID().toString();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(Timestamp complaintDate) {
		this.complaintDate = complaintDate;
	}
	public ComplaintStatus getComplaintStatus() {
		return complaintStatus;
	}
	public void setComplaintStatus(ComplaintStatus complaintStatus) {
		this.complaintStatus = complaintStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + 
				", userId=" + userId + 
				", complaintDate=" + complaintDate + 
				", complaintStatus=" + complaintStatus + 
				", description=" + description + "]";
	}
	
}
