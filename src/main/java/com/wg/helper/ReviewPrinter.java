package com.wg.helper;

import java.util.List;

import com.wg.model.Review;

public class ReviewPrinter {
	// Define the format for headers and rows
    private static final String HEADER_FORMAT = "%-8s| %-22s | %-8s | %-50s";
    private static final String ROW_FORMAT = "%-8s | %-22s | %-8s | %-50s";
    
    public static void printReviews(List<Review> reviews) {
        // Print table title
        System.out.println(Printer.BOX_BORDER);
        System.out.println(Printer.centerTextInBox("REVIEW DETAILS"));
        System.out.println(Printer.BOX_BORDER);
 
        // Print header
        System.out.printf(HEADER_FORMAT, "Sr.No.", "Created Time", "Rating", "Description");
        System.out.println();
        System.out.println(Printer.DIVIDER_LINE);
 
        int index = 1;
        // Print rows
        for (Review review : reviews) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                		index++,
                    review.getCreationDate(),
                    review.getRating(),
                    review.getDesciption()
                );
                System.out.println();
                System.out.println(Printer.DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing account: " + review);
            }
        }
    }
 
    
}
