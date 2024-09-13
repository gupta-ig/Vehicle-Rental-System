package com.wg.service;

import java.sql.SQLException;
import java.util.List;

import com.wg.dao.ComplaintDAO;
import com.wg.helper.StringConstants;
import com.wg.model.Complaint;
import com.wg.model.enums.ComplaintStatus;

public class ComplaintService {
	
	private ComplaintDAO complaintDAO;

	public ComplaintService(ComplaintDAO complaintDAO) {
		super();
		this.complaintDAO = complaintDAO;
	}
	
	public void raiseComplaint(Complaint complaint) throws SQLException {
        complaintDAO.add(complaint);
    }

    public List<Complaint> getAllComplaints() throws SQLException {
        return complaintDAO.getAll();
    }
	
    public List<Complaint> getComplaintById(String userId) throws SQLException {
        return complaintDAO.getById(userId);
    }

	public void changeComplaintStatus(String complaintId, ComplaintStatus status) throws SQLException {
		List<Complaint> complaint = complaintDAO.getById(complaintId);
		if(complaint.get(0).getComplaintStatus().equals(status)) {
			System.out.println(StringConstants.THE_COMPLAINT_ALREADY_HAVE_SAME_STATUS);
		}
		else {
			complaintDAO.updateStatusQuery(complaintId, status);
		}	
	}
	
	public void deleteComplaint(String complaintId) throws SQLException {
        complaintDAO.delete(complaintId);
    }
}
