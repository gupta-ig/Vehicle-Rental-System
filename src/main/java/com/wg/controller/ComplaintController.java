package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.wg.app.App;
import com.wg.helper.ComplaintPrinter;
import com.wg.helper.StringConstants;
import com.wg.model.Complaint;
import com.wg.model.User;
import com.wg.model.enums.ComplaintStatus;
import com.wg.service.ComplaintService;

public class ComplaintController {

	public static final String PLEASE_ENTER_VALID_INDEX = "Please enter valid index.";
	private ComplaintService complaintService;

	public ComplaintController(ComplaintService complaintService) {
		super();
		this.complaintService = complaintService;
	}

	public void raiseComplaint(User user) {
		System.out.println(StringConstants.RAISING_A_COMPLAINT);
    	
		try {
			String description;
			while(true) {
				System.out.print(StringConstants.ENTER_THE_COMPLAINT_DESCRIPTION);
				description = App.scanner.nextLine();
				
				if(description.isBlank() || description.isEmpty()) {
					System.out.println("Complaint cannot be blank or empty.");
					continue;
				}
				else {
					break;
				}
			}
			Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());

			Complaint newComplaint = new Complaint();

			newComplaint.setComplaintId();
			newComplaint.setUserId(user.getUserId());
			newComplaint.setComplaintDate(createdAt);
			newComplaint.setComplaintStatus(ComplaintStatus.FILED);
			newComplaint.setDescription(description);

			complaintService.raiseComplaint(newComplaint);

			System.out.println(StringConstants.COMPLAINT_SUBMITTED_SUCCESSFULLY);
		} 
		catch (SQLException e) {
			System.out.println(StringConstants.ERROR_OCCURRED_WHILE_SUBMITTING_THE_COMPLAINT + e.getMessage());
		}
	}

	public List<Complaint> viewAllComplaints() {
		try {
			List<Complaint> complaints = complaintService.getAllComplaints();
			
			if(complaints.isEmpty()) {
				System.out.println("No complaints registered.");
				return null;
			}
			else {
				System.out.println(StringConstants.VIEW_ALL_COMPLAINTS);
				ComplaintPrinter.printComplaints(complaints);
				return complaints;
			}
		}
		catch (SQLException e) {
			System.out.println(StringConstants.ERROR_OCCURRED_WHILE_RETRIEVING_COMPLAINTS + e.getMessage());
		}
		return null;
	}

	public void viewComplaintById(String userId) {
		System.out.println(StringConstants.VIEW_ALL_COMPLAINTS);
    	
		try {
			List<Complaint> complaint = complaintService.getComplaintById(userId);
			if (complaint != null && complaint.size() > 0) {
				ComplaintPrinter.printComplaints(complaint);
			} else {
				System.out.println(StringConstants.NO_COMPLAINT_FOUND_WITH_THE_PROVIDED_ID);
			}
		}
		catch (SQLException e) {
			System.out.println(StringConstants.ERROR_OCCURRED_WHILE_RETRIEVING_COMPLAINTS+ e.getMessage());
		}
	}

	public void updateComplaintStatus() {
		try {
			List<Complaint> complaints = viewAllComplaints();
			
			if(complaints == null) {
				return;
			}
			System.out.println(StringConstants.VIEW_ALL_COMPLAINTS);

			int index;
    		while(true) {
    			System.out.print(StringConstants.ENTER_VEHICLE_SR_NO_TO_CHANGE_STATUS);
    			 if (App.scanner.hasNextInt()) {
    				 index = App.scanner.nextInt();
    				 System.out.println(index);
    				 if(index > 0 && index <= complaints.size())
    				 {
    					 break;
    				 }
    			 }
    			 else {
    				 System.out.println("Invalid choice. Please enter a valid integer.");
    			     App.scanner.next();
    			     System.out.print(StringConstants.ENTER_YOUR_CHOICE);
    			    }
//    			index = App.scanner.nextInt();
//        		if(index > 0 && index <= complaints.size()) {
//        			break;
//        		}
//        		else {
//        			System.out.println("Please enter valid index.");
//        		}
    		}

			ComplaintStatus status = null;
			while(true) {
				System.out.print(StringConstants.ENTER_THE_COMPLAINT_STATUS_YOU_WANT_TO_CHANGE_YOUR_COMPLAINT);
				String input = App.scanner.next().toUpperCase().trim();
				status = ComplaintStatus.valueOf(input);

				if(status.equals(ComplaintStatus.UNDER_PROCESS) || status.equals(ComplaintStatus.RESOLVED) || status.equals(ComplaintStatus.DECLINED)) {
					break;
				}
				else {
					System.out.println(StringConstants.PLEASE_ENTER_A_VALID_STATUS);
				}
			}
			complaintService.changeComplaintStatus(complaints.get(index - 1).getComplaintId(), status);
			System.out.println("cvgbhjkl");
			
			System.out.println(StringConstants.COMPLAINT_STATUS_CHANGED_SUCCESSFULLY);
			
		} 
		catch (SQLException e) {
			System.err.println(StringConstants.ERROR_UPDATING_COMPLAINT_STATUS + e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
		}
	}
}


