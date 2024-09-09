package com.wg.helper;

import java.util.List;

import com.wg.model.User;

public class UserPrinter {
	// Define the format for headers and rows
    private static final String HEADER_FORMAT = "%-6s | %-10s | %-10s | %-12s | %-20s | %-10s | %-10s | %-25s";
    private static final String ROW_FORMAT = "%-6s | %-10s | %-10s | %-12s | %-20s | %-10s | %-10s | %-25s";
    
    public static void printUsers(List<User> users) {
        // Print table title
        System.out.println(Printer.BOX_BORDER);
        System.out.println(Printer.centerTextInBox("USER DETAILS"));
        System.out.println(Printer.BOX_BORDER);
 
        // Print header
        System.out.printf(HEADER_FORMAT, "Sr.No.", "First Name", "Last Name", "Phone Number", "Email", "Gender", "User Role", "Created At");
        System.out.println();
        System.out.println(Printer.DIVIDER_LINE);
 
        int index = 1;
        // Print rows
        for (User user : users) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                		index++,
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    user.getUserEmail(),
                    user.getGender().name(),
                    user.getRole().name(),
                    user.getCreatedAt()
                );
                System.out.println();
                System.out.println(Printer.DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing account: " + user);
            }
        }
    }
 
    
}
