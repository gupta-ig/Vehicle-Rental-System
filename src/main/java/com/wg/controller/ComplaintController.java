package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.wg.app.App;
import com.wg.helper.ComplaintPrinter;
import com.wg.model.Complaint;
import com.wg.model.User;
import com.wg.model.enums.ComplaintStatus;
import com.wg.service.ComplaintService;

public class ComplaintController {
	
	private ComplaintService complaintService;

	public ComplaintController(ComplaintService complaintService) {
		super();
		this.complaintService = complaintService;
	}
	
	public void raiseComplaint(User user) {
        try {
            System.out.print("Enter the complaint description: ");
            String description = App.scanner.nextLine();

            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
            
            Complaint newComplaint = new Complaint();
            
            newComplaint.setComplaintId();
            newComplaint.setUserId(user.getUserId());
            newComplaint.setComplaintDate(createdAt);
            newComplaint.setComplaintStatus(ComplaintStatus.FILED);
            newComplaint.setDescription(description);
            
            complaintService.raiseComplaint(newComplaint);
            
            System.out.println("Complaint submitted successfully!");
        } 
        catch (SQLException e) {
            System.out.println("Error occurred while submitting the complaint: " + e.getMessage());
        }
    }
	
	public void viewAllComplaints() {
        try {
            List<Complaint> complaints = complaintService.getAllComplaints();
            ComplaintPrinter.printComplaints(complaints);
        }
        catch (SQLException e) {
            System.out.println("Error occurred while retrieving complaints: " + e.getMessage());
        }
    }
	
	public void viewComplaintById(String userId) {
        try {
            List<Complaint> complaint = complaintService.getComplaintById(userId);
            if (complaint != null && complaint.size() > 0) {
                ComplaintPrinter.printComplaints(complaint);
            } else {
                System.out.println("No complaint found with the provided ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred while retrieving the complaint: " + e.getMessage());
        }
    }
}
