package com.wg.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import com.wg.dao.ComplaintDAO;
import com.wg.model.Complaint;
import com.wg.model.enums.ComplaintStatus;

public class ComplaintServiceTest {

	@Mock
	private ComplaintDAO complaintDAO;
	
	@Mock
	private Complaint complaint;
	
	@InjectMocks
	private ComplaintService complaintService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	Complaint newComplaint = new Complaint("123", "Ishag123", new Timestamp(System.currentTimeMillis()), ComplaintStatus.FILED, "The employees are very rude.");
	
	@Test
	public void testRaiseComplaint() throws SQLException {
		complaintService.raiseComplaint(newComplaint);
		verify(complaintDAO, times(1)).add(newComplaint);
	}
	
	@Test
	public void testGetAllComplaints() throws SQLException {
		Complaint newComplaint2 = new Complaint("456", "Aman456", new Timestamp(System.currentTimeMillis()), ComplaintStatus.FILED, "The service is bad.");
		when(complaintDAO.getAll()).thenReturn(Arrays.asList(newComplaint, newComplaint2));
		
		List<Complaint> result = complaintDAO.getAll();
		
		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("Ishag123", result.get(0).getUserId());
		assertEquals("Aman456", result.get(1).getUserId());
	}
	
	@Test
	public void testGetComplaintById() throws SQLException {
		when(complaintDAO.getById("Ishag123")).thenReturn(Arrays.asList(newComplaint));
		
		List<Complaint> result = complaintDAO.getById("Ishag123");
		
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("123", result.get(0).getComplaintId());
	}
	
	@Test
	public void testChangeComplaintStatus_SameStatus() throws SQLException {
		String userId = "Pulkit12345";
		ComplaintStatus status = ComplaintStatus.RESOLVED;
		
		when(complaintDAO.getById(userId)).thenReturn(Collections.singletonList(complaint));
		when(complaint.getComplaintStatus()).thenReturn(status);
		
		complaintService.changeComplaintStatus(userId, status);
		
		verify(complaintDAO, never()).updateStatusQuery(userId, status);
		verify(complaint, times(1)).getComplaintStatus();
	}
	
	@Test
	public void testChangeComplaintStatus_DifferentStatus() throws SQLException {
		String userId = "Pulkit12345";
		ComplaintStatus oldStatus = ComplaintStatus.FILED;
		ComplaintStatus newStatus = ComplaintStatus.RESOLVED;
		
		when(complaintDAO.getById(userId)).thenReturn(Collections.singletonList(complaint));
		when(complaint.getComplaintStatus()).thenReturn(oldStatus);
		
		complaintService.changeComplaintStatus(userId, newStatus);
		
		verify(complaintDAO, times(1)).updateStatusQuery(userId, newStatus);
		verify(complaint, times(1)).getComplaintStatus();
	}
	
	@Test
	public void testDeleteComplaint() throws SQLException {
		complaintService.deleteComplaint(newComplaint.getComplaintId());
		verify(complaintDAO, times(1)).delete("123");
	}
}
