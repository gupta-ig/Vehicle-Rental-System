package com.wg.controller;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.wg.app.App;
import com.wg.helper.Choice;
import com.wg.helper.ComplaintPrinter;
import com.wg.helper.StringConstants;
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
			System.out.print(StringConstants.ENTER_THE_COMPLAINT_DESCRIPTION);
			String description = App.scanner.nextLine();

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
			ComplaintPrinter.printComplaints(complaints);
			return complaints;
		}
		catch (SQLException e) {
			System.out.println(StringConstants.ERROR_OCCURRED_WHILE_RETRIEVING_COMPLAINTS + e.getMessage());
		}
		return null;
	}

	public void viewComplaintById(String userId) {
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

			System.out.println(StringConstants.VIEW_ALL_COMPLAINTS);
			List<Complaint> complaints = viewAllComplaints();

			if(complaints == null) {
				System.out.println(StringConstants.NO_COMPLAINTS_REGISTERED);
			}
			int choice;
			System.out.print(StringConstants.ENTER_VEHICLE_SR_NO_TO_CHANGE_STATUS);

			choice = Choice.enterChoice();

			ComplaintStatus status = null;
			while(true) {
				System.out.print(StringConstants.ENTER_THE_COMPLAINT_STATUS_YOU_WANT_TO_CHANGE_YOUR_COMPLAINT);
				String input = App.scanner.next().toUpperCase();
				status = ComplaintStatus.valueOf(input);

				if(status == ComplaintStatus.UNDER_PROCESS || status == ComplaintStatus.RESOLVED || status == ComplaintStatus.DECLINED) {
					break;
				}
				else {
					System.out.println(StringConstants.PLEASE_ENTER_A_VALID_STATUS);
				}
			}

			complaintService.changeComplaintStatus(complaints.get(choice - 1).getComplaintId(), status);
			System.out.println(StringConstants.COMPLAINT_STATUS_CHANGED_SUCCESSFULLY);
		} catch (SQLException e) {
			System.err.println(StringConstants.ERROR_UPDATING_COMPLAINT_STATUS + e.getMessage());
		}
		catch (IllegalArgumentException e) {
			System.err.println(StringConstants.VALIDATION_ERROR + e.getMessage());
		}
	}
}


