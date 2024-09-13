package com.wg.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wg.model.enums.ComplaintStatus;

public class ComplaintModelTest {

	private Complaint complaint;
	
	@BeforeEach
	public void setup() {
		complaint = new Complaint(
				UUID.randomUUID().toString(),
				"Ishag123",
				new Timestamp(System.currentTimeMillis()),
				ComplaintStatus.FILED,
				"System is not working");
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(complaint.getComplaintId());
		assertEquals("Ishag123", complaint.getUserId());
		assertNotNull(complaint.getComplaintDate());
		assertEquals("FILED", complaint.getComplaintStatus().name());
		assertEquals("System is not working", complaint.getDescription());
	}
	
	@Test
	public void testGettersAndSetters() {
		complaint.setComplaintId();
		complaint.setUserId("Ishag123");
		complaint.setComplaintDate(new Timestamp(System.currentTimeMillis()));
		complaint.setComplaintStatus(ComplaintStatus.FILED);
		complaint.setDescription("Vehicle not working properly");
		
		assertNotNull(complaint.getComplaintId());
		assertEquals("Ishag123", complaint.getUserId());
		assertNotNull(complaint.getComplaintDate());
		assertEquals("FILED", complaint.getComplaintStatus().name());
		assertEquals("Vehicle not working properly", complaint.getDescription());
	}
	
	@Test
	public void testToString() {
		String complaintString = complaint.toString();
		
		assertTrue(complaintString.contains("Ishag123"));
		assertTrue(complaintString.contains(" not working"));
	}
}
