package com.wg.helper;

import java.util.List;

import com.wg.model.Booking;

public class BookingPrinter {
	// Define the format for headers and rows
    private static final String HEADER_FORMAT = "%-6s | %-38s | %-38s | %-22s | %-22s | %-10s";
    private static final String ROW_FORMAT = "%-6s | %-38s | %-38s | %-22s | %-22s | %-10s";
    
    public static void printBookings(List<Booking> bookings) {
        // Print table title
        System.out.println(Printer.BOX_BORDER);
        System.out.println(Printer.centerTextInBox("BOOKING DETAILS"));
        System.out.println(Printer.BOX_BORDER);
 
        // Print header
        System.out.printf(HEADER_FORMAT, "Sr.No.", "Booking Id", "Vehicle Id", "Start Time", "End Time", "Created At");
        System.out.println();
        System.out.println(Printer.DIVIDER_LINE);
        
        int index = 1;
        // Print rows
        for (Booking booking : bookings) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                		index++,
                    booking.getBookingId(),
                    booking.getVehicleId(),
                    booking.getBookingStartTime(),
                    booking.getBookingEndTime(),
                    booking.getCreatedAt()
                );
                System.out.println();
                System.out.println(Printer.DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing account: " + booking);
            }
        }
    }
 
    
}
