package com.wg.helper;

import java.util.List;

import com.wg.model.Vehicle;

public class VehiclePrinter {
	// Define the format for headers and rows
    private static final String HEADER_FORMAT = "%-6s | %-15s | %-10s | %-20s | %-15s | %-25s | %-10s | %-25s";
    private static final String ROW_FORMAT = "%-6s | %-15s | %-10s | %-20s | %-16s | %-25s | %-12s | %-25s";
    
    public static void printVehicles(List<Vehicle> vehicles) {
        // Print table title
        System.out.println(Printer.BOX_BORDER);
        System.out.println(Printer.centerTextInBox("VEHICLE DETAILS"));
        System.out.println(Printer.BOX_BORDER);
 
        // Print header
        System.out.printf(HEADER_FORMAT, "Sr.No.", "Manufacturer", "Model", "Registration Number", "Manufacture Year", "Last Maintenance Date", "Vehicle Type", "Availability Status");
        System.out.println();
        System.out.println(Printer.DIVIDER_LINE);
 
        int index = 1;
        // Print rows
        for (Vehicle vehicle : vehicles) {
            try {
                // Print each account row
                System.out.printf(ROW_FORMAT,
                	index++,
                    vehicle.getManufacturer(),
                    vehicle.getModel(),
                    vehicle.getRegisterationNumber(),
                    vehicle.getManufactureYear(),
                    vehicle.getMaintenanceDate(),
                    vehicle.getType().name(),
                    vehicle.getAvailabilityStatus().name()
                );
                System.out.println();
                System.out.println(Printer.DIVIDER_LINE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred while printing account: " + vehicle);
            }
        }
    }
 
    
}
