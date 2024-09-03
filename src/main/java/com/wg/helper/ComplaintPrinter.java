package com.wg.helper;

import java.util.List;

import com.wg.model.Complaint;

public class ComplaintPrinter {
	// Define the format for headers and rows
    private static final String HEADER_FORMAT = "%-6s | %-22s | %-15s | %-60s";
    private static final String ROW_FORMAT = "%-6s | %-22s | %-15s | %-60s";
    
    public static void printComplaints(List<Complaint> complaints) {
        // Print table title
        System.out.println(Printer.BOX_BORDER);
        System.out.println(Printer.centerTextInBox("COMPLAINT DETAILS"));
        System.out.println(Printer.BOX_BORDER);
 
        // Print header
        System.out.printf(HEADER_FORMAT, "Sr.No.", "Complaint Date", "Complaint Status", "Complaint Description");
        System.out.println();
        System.out.println(Printer.DIVIDER_LINE);
 
        int index = 1;
        // Print rows
        for (Complaint complaint : complaints) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                	index++,
                	complaint.getComplaintDate(),
                	complaint.getComplaintStatus(),
                	complaint.getDescription()
                );
                System.out.println();
                System.out.println(Printer.DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing account: " + complaint);
            }
        }
    }
 
    
}
